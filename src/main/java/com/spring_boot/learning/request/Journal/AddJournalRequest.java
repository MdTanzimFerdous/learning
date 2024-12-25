package com.spring_boot.learning.request.Journal;

import com.spring_boot.learning.model.Journal;
import lombok.Data;
import lombok.Getter;

@Data
public class AddJournalRequest {
    private String title;
    private String content;
    private String folderName;

    public Journal createJournal() {
        return Journal.builder()
                .title(this.getTitle())
                .content(this.getContent())
                .folderName(this.getFolderName())
                .build();
    }
}
