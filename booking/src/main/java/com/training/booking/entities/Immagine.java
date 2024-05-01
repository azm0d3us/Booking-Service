package com.training.booking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "immagini")
public class Immagine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_immagine")
    private Long idImmagine;

    @Column(name = "url")
    private String url;

    @JsonBackReference
    @OneToOne(mappedBy = "immagineUtente")
    private Utente utente;

    @JsonBackReference
    @OneToOne(mappedBy = "immagineResidenza")
    private Residenza residenza;

    @JsonBackReference
    @ManyToMany(mappedBy = "immaginiCamera")
    private List<Camera> camere;
}
