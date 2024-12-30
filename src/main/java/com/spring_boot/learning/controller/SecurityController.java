package com.spring_boot.learning.controller;

import com.spring_boot.learning.model.Journal;
import com.spring_boot.learning.request.AddJournalRequest;
import com.spring_boot.learning.response.ApiResponse;
import com.spring_boot.learning.services.journal.JournalServiceI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("${api.prefix}/security")
public class SecurityController {
    final JournalServiceI journalService;

    @GetMapping("/")
    public String Hello(HttpServletRequest httpServletRequest) {

        return httpServletRequest.getSession().getId();

    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }


}
