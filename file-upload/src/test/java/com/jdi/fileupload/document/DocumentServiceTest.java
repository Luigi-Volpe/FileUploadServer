package com.dwmarketing.fileupload.document;

import com.dwmarketing.fileupload.mockData.MockMultipartFile;
import com.dwmarketing.fileupload.mockData.Mockdata;
import com.dwmarketing.fileupload.user.User;
import com.dwmarketing.fileupload.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock private DocumentRepository documentRepository;
    @Mock private UserService userService;

    private DocumentService documentService;
    private Mockdata mockdata;
    private Document document;
    private User user;
    private User notInDb;
    private MockMultipartFile mockMultipartFile;

    @BeforeEach
    void setUp(){
        documentService = new DocumentService(documentRepository,userService);
        mockdata = new Mockdata();
        document = mockdata.getDocument();
        user = mockdata.getUser();
        notInDb = mockdata.getUserNotinDb();
        mockMultipartFile = mockdata.getFile();
    }

    @Test
    void saveFileShouldRunGetUser() {
        documentService.saveFile(mockMultipartFile,user.getId());
        verify(userService).getUser(user.getId());
    }
    @Test
    void saveFileShouldRunFindDocumentByDocNameAndUserId() {
        documentService.saveFile(mockMultipartFile,document.getUserId());
        verify(documentRepository).findDocumentByDocNameAndUserId(document.getDocName(),user.getId());
    }

    @Test
    void saveFileShouldThrowIllegalStateEx() {
        when(documentRepository.findDocumentByDocNameAndUserId(document.getDocName(),user.getId()))
                .thenReturn(Optional.of(document));
        assertThrows(IllegalStateException.class,()->{
            documentService.saveFile(mockMultipartFile,user.getId());
        });
    }


    @Test
    void updateFileShouldThrowIllegalStateException() {
        when(documentRepository.findById(document.getId()))
                .thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class,()->{
            documentService.updateFile(1L,"newName",mockMultipartFile);
        });
    }


    @Test
    void getAllFilesOfUser() {
        documentService.getAllFilesOfUser(1L);
        verify(documentRepository).findAllDocumentsByUserId(1L);

    }

}