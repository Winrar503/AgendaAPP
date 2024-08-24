package org.darwin.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Relación con Notas
    @OneToMany(mappedBy = "contacto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas = new ArrayList<>();

    private boolean eliminado = false; //para pepelera

    // Método para agregar una nota
    public void addNota(Nota nota) {
        notas.add(nota);
        nota.setContacto(this);
    }

    // Método para remover una nota
    public void removeNota(Nota nota) {
        notas.remove(nota);
        nota.setContacto(null);
    }
}