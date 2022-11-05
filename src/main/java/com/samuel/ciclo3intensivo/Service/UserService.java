package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.Dto.UserRegistrationDto;
import com.samuel.ciclo3intensivo.models.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Usuario save(UserRegistrationDto registrationDto);
}
