package com.zelexon.recruitiq.security;

import com.zelexon.recruitiq.dao.User;

public interface AppAuthenticationProvider {
    User authenticate(String email, String password);
}
