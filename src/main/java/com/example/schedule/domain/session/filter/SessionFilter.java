package com.example.schedule.domain.session.filter;

import com.example.schedule.domain.session.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@RequiredArgsConstructor
public class SessionFilter implements Filter {
    public SessionFilter(SessionService sessionService) {
        this.service = sessionService;
    }

    private SessionService service;
    private static final String[] WHITE_LIST = {"/session/login", "/user/create"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        HttpSession session = httpRequest.getSession(false);


        if (isWhiteList(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        if (session != null) {
            String sessionId = session.getAttribute("sessionId").toString();
            if (sessionId != null) {
                Integer userId = service.getUserId(sessionId);
                if (userId != null) {
                    httpRequest.setAttribute("userId", userId);
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("Unauthorized");
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
