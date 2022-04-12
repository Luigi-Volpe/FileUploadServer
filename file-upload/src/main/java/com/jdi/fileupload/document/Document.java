package com.dwmarketing.fileupload.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Documents")
public class Document {



    @SequenceGenerator(name = "documentSequenceGenerator", sequenceName = "documentSequenceGenerator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documentSequenceGenerator")
    @Id
    private long id;
    private String docName;
    private String docType;
    private Date createdAt;

    @Lob
    private byte [] data;

    //Relations
    private long userId;


    public Document(String docName, String docType, Date createdAt, byte[] data, long userId) {
        this.docName = docName;
        this.docType = docType;
        this.createdAt = createdAt;
        this.data = data;
        this.userId = userId;

    }

}
