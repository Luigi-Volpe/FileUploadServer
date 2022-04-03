package com.dwmarketing.fileupload.document;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(path = "api/documents")
public class DocumentController {

    @Autowired
    private final DocumentService documentService;

    @GetMapping(path = "/{userId}")
    public List<Document>getAllDocumentsOfUser(@PathVariable Long userId) {
        return documentService.getAllFilesOfUser(userId);
    }

    @GetMapping(path = "/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
        return documentService.downloadFile(fileId);
    }

    @PostMapping(path = "/{userId}")
    public void uploadFiles(MultipartFile[] files, @PathVariable Long userId){
        for (MultipartFile file: files){
            documentService.saveFile(file, userId);
        }
    }

    @PutMapping(path = "/update/{fileId}")
    public void updateFile(
            @PathVariable Long fileId,
            @RequestParam(required = false) String docName,
            @RequestParam(required = false) MultipartFile file
    ){
        documentService.updateFile(fileId, docName, file);
    }

    @DeleteMapping(path = "/{fileId}")
    public void deleteFile(@PathVariable Long fileId){
        documentService.deleteFile(fileId);
    }

}
