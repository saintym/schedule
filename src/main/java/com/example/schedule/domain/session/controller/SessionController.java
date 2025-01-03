package com.example.schedule.domain.session.controller;

import com.example.schedule.domain.session.SessionService;
import com.example.schedule.domain.session.dto.LoginRequestDto;
import com.example.schedule.domain.session.dto.SignUpRequestDto;
import com.example.schedule.domain.user.entity.User;
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
@RequestMapping("/auth")
public class SessionController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/signUp")
    public String signUp(
            @RequestBody SignUpRequestDto dto
            ) {
        if(!isValidateEmail(dto.getEmail())){
            throw new IllegalArgumentException("Invalid email format. Please check email format or already registered email.");
        }

        if(!isValidatePassword(dto.getPassword())){
            throw new IllegalArgumentException("Invalid password. Please check password format.");
        }

        User user = new User(dto.getName(), dto.getEmail(), dto.getPassword());
        userRepository.save(user);

        return "Sign up successful.";
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

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


    private boolean isValidatePassword(String password) {
        return password.length() >= 8
                && password.matches(".*\\d.*")
                && password.matches(".*[A-Z].*");
    }

    private boolean isValidateEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
                && userRepository.findByEmail(email).isEmpty();
    }
}