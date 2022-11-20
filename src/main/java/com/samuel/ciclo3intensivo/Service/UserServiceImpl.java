package com.samuel.ciclo3intensivo.Service;

import com.samuel.ciclo3intensivo.Dao.UsuarioDao;
import com.samuel.ciclo3intensivo.Dto.UserRegistrationDto;
import com.samuel.ciclo3intensivo.models.Role;
import com.samuel.ciclo3intensivo.models.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.samuel.ciclo3intensivo.util.EncriptarPassword.encriptarPassword;

@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    private final UsuarioDao usuarioDao;

    public UserServiceImpl(UsuarioDao usuarioDao){
        super();
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Usuario save(UserRegistrationDto registrationDto) {
        /*var username = registrationDto.getUsername();
        var nombre = registrationDto.getNombre();
        var apellido = registrationDto.getApellido();
        var email = registrationDto.getEmail();
        var telefono = registrationDto.getTelefono();
        var password = encriptarPassword(registrationDto.getPassword());*/
        Usuario usuario = new Usuario(registrationDto.getUsername(),encriptarPassword(registrationDto.getPassword()),
                registrationDto.getNombre(),registrationDto.getApellido(),registrationDto.getEmail(),registrationDto.getTelefono(),
                Arrays.asList(new Role("ROLE_USER")));
        System.out.println(usuario);
        log.info("resultado"+usuario);
        return usuarioDao.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        var roles = new ArrayList<GrantedAuthority>();

        for(Role rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
        //return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), mapRolesToAuthorities(usuario.getRoles()));
    }

    //private Collection << ? extends GrantedAuthority >  mapRolesToAuthorities(Collection <Role> roles) {
      //  return roles.stream().map(role - > new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    //}
}
