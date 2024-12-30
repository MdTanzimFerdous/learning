package com.spring_boot.learning.services.journal;

import com.spring_boot.learning.exceptions.JournalNotFoundException;
import com.spring_boot.learning.model.Journal;
import com.spring_boot.learning.repository.JournalRepository;
import com.spring_boot.learning.request.AddJournalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalServiceI {

    final JournalRepository journalRepository;

    @Override
    public Journal getJournalById(Long Id) {
        return journalRepository.findById(Id).orElseThrow(()-> new JournalNotFoundException("Not Found!!!"));
    }

    @Override
    public List<Journal> getAllJournal() {
        return journalRepository.findAll();
    }

    @Override
    public Journal createJournal(AddJournalRequest addJournalRequest) {
        if(addJournalRequest.createJournal().getTitle() == null) {
            throw new RuntimeException("You haven't added anything!!");
        }
        Journal savedJournal = addJournalRequest.createJournal();
        return journalRepository.save(savedJournal);
    }

    @Override
    public List<Journal> getJournalByFolderName(String folder_name) {
        return journalRepository.findAllByFolderName(folder_name);
    }
}
