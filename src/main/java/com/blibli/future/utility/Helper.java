package com.blibli.future.utility;

import com.blibli.future.model.Customer;
import com.blibli.future.model.User;
import com.blibli.future.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by dhika on 16/12/2016.
 */
@Component
public class Helper {
    @Autowired
    UserRepository userRepository;

    public boolean isLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken) ;
    }

    public String getEmail() {
        return getCurrentCustomer().getEmail();
    }

//    public boolean isLoggedInAsCustomer

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isLoggedIn()) {
            return userRepository.findByUsername(auth.getName());
        }
        return null;
    }

    public Customer getCurrentCustomer() {
        return (Customer) getCurrentUser();
    }
}
