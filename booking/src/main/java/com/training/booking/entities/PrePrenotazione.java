package com.training.booking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pre_prenotazioni")
public class PrePrenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pre_prenotazione")
    private Long idPrePrenotazione;

    @Column(name = "num_adulti")
    private Integer numAdulti;

    @Column(name = "num_bambini")
    private Integer numBambini;

    @Column(name = "num_persone")
    private Integer numPersone;

    @Column(name = "totale")
    private Double totale;

    @Column(name = "check_in")
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private Utente utentePrenotante;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_camera")
    private Camera cameraPrenotata;
}
