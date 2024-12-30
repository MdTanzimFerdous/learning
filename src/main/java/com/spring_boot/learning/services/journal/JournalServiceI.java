package com.spring_boot.learning.services.journal;

import com.spring_boot.learning.model.Journal;
import com.spring_boot.learning.request.AddJournalRequest;

import java.util.List;

public interface JournalServiceI {
    Journal getJournalById(Long Id);
    List<Journal> getAllJournal();
    Journal createJournal(AddJournalRequest addJournalRequest);
    List<Journal> getJournalByFolderName(String folder_name);
}
