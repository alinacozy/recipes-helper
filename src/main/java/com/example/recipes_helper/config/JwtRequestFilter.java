package com.example.recipes_helper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.recipes_helper.services.TokenBlacklistService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter { //фильтр выполняется один раз на каждый HTTP-запрос

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain chain
    )
    throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization"); //извлекаем заголовок авторизации из запроса (там JWT токен)

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); //строка из хедера авторизации, с обрезанным словом Bearer,т.е. остался только JWT токен
            // Проверяем, что токен не в blacklist
            if (tokenBlacklistService.isBlacklisted(jwt)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Token is revoked\"}");
                return;
            }
            username = jwtTokenUtil.extractUsername(jwt); //извлекаем юзернейм
        }

        // Проверяем, что из токена удалось извлечь имя пользователя
        // и что в текущем контексте безопасности ещё нет аутентификации
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // Загружаем детали пользователя из базы по username
            System.out.println("Детали пользователя: " + userDetails);  // ADDED
            System.out.println("Токен валиден: " + jwtTokenUtil.validateToken(jwt, userDetails));  // ADDED
            
            if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication); //устанавливаем аутентификацию в контекст
            }
        }

        chain.doFilter(request, response); //прокидываем запрос дальше по цепочке фильтров
    }
}
