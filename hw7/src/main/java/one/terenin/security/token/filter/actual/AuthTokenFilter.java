package one.terenin.security.token.filter.actual;

import io.jsonwebtoken.JwtException;
import one.terenin.security.details.UserDetailsServiceBase;
import one.terenin.security.token.filter.common.SecurityConstants;
import one.terenin.security.token.filter.common.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils utils;
    @Autowired
    private UserDetailsServiceBase userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            String token = parseJwt(request);
            if (Objects.nonNull(token) && utils.checkToken(token)){
                String login = utils.getLoginFromToken(token);
                UserDetails details = userDetailsService.loadUserByUsername(login);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (JwtException e){
            throw e;
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        if (Objects.nonNull(token)
                && StringUtils.hasText(token)
                && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            return token.substring(SecurityConstants.TOKEN_PREFIX.length());
        }
        return null;
    }

}
