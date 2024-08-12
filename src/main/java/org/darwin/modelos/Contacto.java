package org.darwin.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contactos")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El numero es obligatorio")
    private String numero;

    @Email(message = "El email es invalido xd")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El numero es obligatorio") String getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank(message = "El numero es obligatorio") String numero) {
        this.numero = numero;
    }

    public @Email(message = "El email es invalido xd") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "El email es invalido xd") String email) {
        this.email = email;
    }
}