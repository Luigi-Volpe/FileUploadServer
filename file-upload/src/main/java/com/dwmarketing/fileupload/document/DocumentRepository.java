package com.dwmarketing.fileupload.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document>findDocumentByDocNameAndUserId(String docName, Long userId);
    List<Document>findAllDocumentsByUserId(Long userId);
}
