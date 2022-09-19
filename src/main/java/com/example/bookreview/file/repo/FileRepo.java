package com.example.bookreview.file.repo;

import com.example.bookreview.file.model.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<AttachedFile, Long> {
    Optional<AttachedFile> findAttachedFileByPostPostId(Long postId);
}
