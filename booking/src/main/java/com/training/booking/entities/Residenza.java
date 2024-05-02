package com.training.booking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "residenze")
public class Residenza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_residenza")
    private Long idResidenza;

    @Column(name = "nome")
    private String nome;

    @Column(name = "indirizzo")
    private String indirizzo;

    @JsonBackReference
    @OneToMany(mappedBy = "residenza")
    private Set<Camera> camere;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_img_residenza", referencedColumnName = "id_img_residenza")
    private Immagine imgResidenza;
}
