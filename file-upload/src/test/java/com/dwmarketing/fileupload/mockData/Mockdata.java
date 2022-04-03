package com.dwmarketing.fileupload.mockData;

import com.dwmarketing.fileupload.document.Document;
import com.dwmarketing.fileupload.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mockdata {

    public User getUser(){
        return new User(
                1L,"Luigi"
        );
    }

    public List<User> getDbWithOneUser(){
        List<User> db = new ArrayList<>();
        db.add(getUser());
        return db;
    }

    public User getUserNotinDb(){
        return new User(
                100L,"Mario"
        );
    }

    public byte[] getByte(){
        return new byte[0];
    }

    public Document getDocument(){
        return new Document(
                1L,"docName.tst","docType",null,getByte(),1L
        );
    }

    public MockMultipartFile getFile(){
        return new MockMultipartFile();

    }
}
