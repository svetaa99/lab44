package backend.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import backend.models.Role;
import backend.services.impl.UserService;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenUtils jwtTokenUtil;
    private final UserService userRepo;

    public JwtTokenFilter(TokenUtils jwtTokenUtil,
                          UserService userRepo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        // Get authorization header and validate
    	System.out.println("ZAHTEEEV: "  + request.toString() + "\n" + request.getHeaderNames().toString());
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) { //|| !header.startsWith("Bearer ")
        	System.out.println("HEADER JE NULL!");
            chain.doFilter(request, response);
            return;
        }
        System.out.println("HEADER: " + header);
        
        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }
        // Get user identity and set it on the spring security context
        String tokenStr = jwtTokenUtil.getToken(request);
        UserDetails userDetails = userRepo
            .findUserByEmail(jwtTokenUtil.getUsernameFromToken(tokenStr));
        System.out.println("Kojeg korisnika nadje: " + userDetails.getUsername());

        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                    List.of() : userDetails.getAuthorities()
            );
        
        System.out.println("ROLE KORISNIKA : " + userDetails.getAuthorities());
        
        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("\n\nAUTENTIFIKOVAN? : " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated() + "\n\nKOOO: " +  SecurityContextHolder.getContext().getAuthentication().getName() + "\n" +
        		SecurityContextHolder.getContext().getAuthentication().toString());
        chain.doFilter(request, response);
    }

}