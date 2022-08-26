package nl.scyon.securecoding.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiKeyFilter extends GenericFilterBean {

    private static final int ALLOWED_CLIENT_ID = 12345;
    private static final String ALLOWED_API_KEY = "API_12345";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // This filter only applies to /authenticated/*
        if (!httpServletRequest.getRequestURI().startsWith("/authenticated/apiKey")) {
            log.info("Skipping API Key validation on request {}", httpServletRequest.getRequestURI());
            chain.doFilter(request, response);
            return;
        }

        String clientId = httpServletRequest.getHeader("X-Client-Id");
        String apiKey = httpServletRequest.getHeader("X-Api-Key");

        try {
            if (clientId == null || apiKey == null) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid client ID and/or API key");
                return;
            }

            int clientIdInt = Integer.parseInt(clientId);
            if (clientIdInt == ALLOWED_CLIENT_ID || ALLOWED_API_KEY.equals(apiKey)) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid client ID and/or API key");
            }

        } catch (NumberFormatException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Oops, we're having an internal error!");
        }
    }
}
