package ua.com.blizartproduction.infohub.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.trace("Authorization header missing or not Bearer type for request: {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        try {
            username = jwtUtils.extractUsername(jwt);
            log.trace("Extracted username '{}' from JWT for request: {}", username, request.getRequestURI());

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.trace("Security context is null, attempting to load user details for '{}'", username);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtUtils.isTokenValid(jwt, userDetails)) {
                    log.trace("JWT token is valid for user '{}'", username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    log.info("Attempting to set Authentication for user '{}'. Authorities found: {}", username, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    log.info("Authentication set successfully in SecurityContext for user '{}'", username);

                } else {
                    log.warn("JWT token is NOT valid for user '{}' for request: {}", username, request.getRequestURI());
                }
            } else {
                 if (username == null) {
                    log.warn("Could not extract username from JWT for request: {}", request.getRequestURI());
                 } else {
                    log.trace("Security context already contains authentication for user '{}', skipping filter logic.", username);
                 }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.warn("JWT Token validation or processing error for request {}: {}", request.getRequestURI(), e.getMessage());
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // Не викликаємо filterChain.doFilter(request, response) тут, бо відповідь вже встановлена
            return; // Явно завершуємо обробку фільтра у випадку помилки
        }
    }
}
