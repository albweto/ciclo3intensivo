package com.samuel.ciclo3intensivo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "username",unique = true)
    @NotEmpty
    private String username;

    @Column(name ="password")
    @NotEmpty
    private String password;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;


    //@Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id_rol"))

    private Collection<Role> roles;



    public Usuario(){

    }


    public Usuario( String username, String password, String nombre, String apellido, String email, String telefono, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.roles = roles;
    }
}
