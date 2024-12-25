package com.spring_boot.learning.repository;

import com.spring_boot.learning.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    List<Journal> findAllByFolderName(String folder_name);
}
