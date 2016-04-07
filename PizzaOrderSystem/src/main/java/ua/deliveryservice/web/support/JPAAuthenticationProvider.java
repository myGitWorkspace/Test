package ua.deliveryservice.web.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ua.deliveryservice.domain.Role;
import ua.deliveryservice.domain.User;
import ua.deliveryservice.service.UserService;

@Component("JPAAuthProvider")
public class JPAAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	private final Logger logger = LoggerFactory.getLogger(JPAAuthenticationProvider.class);
	
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String password = auth.getCredentials().toString();

        User user = null;
        try {
        	user = userService.findByLogin(login);
        } catch(NoResultException e) {
        	throw new BadCredentialsException("Bad Credentials");
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (user != null && encoder.matches(password, user.getPassword()) ) {
           
            List<GrantedAuthority> authorities = new ArrayList<>();
            for(Role role : user.getRoles()) {
            	authorities.add(new SimpleGrantedAuthority(role.getName()));	
            }
            userService.updateLastVisitDate(user.getId(), new Date());
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
    	
    	throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}