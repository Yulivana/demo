package com.example.demo.authentication;

import com.example.demo.fasads.UserFasad;
import com.example.demo.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class MyDBAuthenticationService implements UserDetailsService {

    @Autowired
    UserFasad userFasad;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserForm user = userFasad.getByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User Name "+username +"Not Found");
        }
        return new User(user.getUsername(), encoder.encode(user.getPassword()), getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(UserForm user){

        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        if(user.getRole().equalsIgnoreCase("admin")){
            grantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthority;
    }

}
