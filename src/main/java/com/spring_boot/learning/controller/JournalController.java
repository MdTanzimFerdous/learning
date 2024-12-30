package com.spring_boot.learning.controller;

import com.spring_boot.learning.model.Journal;
import com.spring_boot.learning.request.AddJournalRequest;
import com.spring_boot.learning.response.ApiResponse;
import com.spring_boot.learning.services.journal.JournalServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("${api.prefix}/journal")
public class JournalController {
    final JournalServiceI journalService;

    @GetMapping("/all")
//    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponse> Hello() {
        try {
            return  ResponseEntity.ok(new ApiResponse("All-Journal", journalService.getAllJournal()));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Journal Not Found", e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody AddJournalRequest addJournalRequest) {
        try {
            Journal journal = journalService.createJournal(addJournalRequest);
            return ResponseEntity.ok(new ApiResponse("new journal added", journal));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("server-error", e.getMessage()));
        }
    }
}
