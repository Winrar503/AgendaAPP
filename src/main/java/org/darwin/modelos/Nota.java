package org.darwin.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String contenido;

    @ManyToOne
    @JoinColumn(name = "contacto_id", nullable = false)
    private Contacto contacto;

    private boolean eliminado = false; //mandar a papelera

}
