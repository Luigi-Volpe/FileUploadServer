package com.dwmarketing.fileupload.document;

import com.dwmarketing.fileupload.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DocumentService {

    @Autowired
    private final DocumentRepository documentRepository;
    private final UserService userService;


    public void saveFile(MultipartFile file, Long userId){
        String fileName = file.getOriginalFilename();
        userService.getUser(userId);
        Optional<Document>documentWithTheSameName =
        documentRepository.findDocumentByDocNameAndUserId(fileName, userId);


        if (documentWithTheSameName.isPresent() && documentWithTheSameName.get().getUserId() == userId){
            throw new IllegalStateException("A document with the name " + fileName + " already exists");
        }

        try {
            Document document = new Document(
                    fileName,
                    file.getContentType(),
                    null,
                    file.getBytes(),
                    userId
            );
            documentRepository.save(document);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Transactional
    public void updateFile(Long id, String newName, MultipartFile file){
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Cant find document with id " + id
                ));

        String currentName = document.getDocName();
        String extension = getDocExtension(currentName);

        long userId =document.getUserId();
        Optional<Document>documentWithTheSameName =
                documentRepository.findDocumentByDocNameAndUserId(newName, userId);
        if (documentWithTheSameName.isPresent() && documentWithTheSameName.get().getUserId() == userId){
            throw new IllegalStateException("A document with the name " + newName + " already exists");
        }

        if (newName != null &&
                newName.length() > 0 &&
                !Objects.equals(currentName, newName)){
            document.setDocName(newName + extension);
        }

        if (file != null && file.getOriginalFilename() != null){

            String fileExtension = getDocExtension(file.getOriginalFilename());

            if (!Objects.equals(extension, fileExtension)){
                throw new IllegalStateException("File has to be of the same type");
            }
            try {
                document.setData(file.getBytes());
                document.setDocType(file.getContentType());
            }
             catch (Exception e) {
                e.printStackTrace();
            }
            if (newName == (null)){
                document.setDocName(file.getOriginalFilename());
            }

        }
    }



    public Optional<Document> getFile(Long fileId){
        return documentRepository.findById(fileId);
    }



    public List<Document> getAllFilesOfUser(Long userId){
        userService.getUser(userId);
        return documentRepository.findAllDocumentsByUserId(userId);
    }



    public ResponseEntity<ByteArrayResource> downloadFile(Long fileId){
        Optional<Document> optionalDocument = getFile(fileId);

        if (optionalDocument.isEmpty()){
            throw new IllegalStateException("Document Not Found");
        }

        Document document = optionalDocument.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + document.getDocName()+ "\"")
                .body(new ByteArrayResource(document.getData()));
    }



    public void deleteFile(Long id){
        if (!documentRepository.existsById(id)){
            throw new IllegalStateException("No Document found with id " + id);
        }
        documentRepository.deleteById(id);
    }

    private String getDocExtension(String document){
        return document.substring(document.lastIndexOf("."), document.length());
    }

}
