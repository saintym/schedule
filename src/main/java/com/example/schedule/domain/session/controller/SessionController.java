package com.example.schedule.domain.session.controller;

import com.example.schedule.domain.session.SessionService;
import com.example.schedule.domain.session.dto.LoginRequestDto;
import com.example.schedule.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var user = userRepository.findByEmailAndPassword(dto.email(), dto.password());

        if (user != null) {
            HttpSession session = request.getSession();
            String sessionId = sessionService.createSession(user.getId());

            session.setAttribute("sessionId", sessionId);

            return "Login successful.";
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Invalid email or password.";
        }
    }

    @PostMapping("/logout")
    public String logout(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        sessionService.removeSession(session.getAttribute("sessionId").toString());

        session.invalidate();

        return "Logged out successfully.";
    }
}