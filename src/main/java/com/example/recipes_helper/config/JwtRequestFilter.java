package com.example.recipes_helper.config;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter { //фильтр выполняется один раз на каждый HTTP-запрос
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain chain
    )
    throws ServletException, IOException {

        System.out.println("JwtRequestFilter: " + request.getRequestURI());
        final String authorizationHeader = request.getHeader("Authorization"); //извлекаем заголовок авторизации из запроса (там JWT токен)

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); //строка из хедера авторизации, с обрезанным словом Bearer,т.е. остался только JWT токен
            System.out.println("JWT токен: " + jwt);  // ADDED
            username = jwtTokenUtil.extractUsername(jwt); //извлекаем юзернейм
            System.out.println("Имя пользователя из токена: " + username);  // ADDED
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

        chain.doFilter(request, response); //прокидываем запрос дальше по цепочке фильтров (в нашем случае он пойдет в контроллер)
    }
}
