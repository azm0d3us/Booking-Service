package booking.entities;

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
@Table(name = "lista_prezzi")
public class ListaPrezzi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_listino")
    private Long idListino;

    @Column(name = "prezzo")
    private Double prezzo;

    @Column(name = "validita_inizio")
    private Date validitaInizio;

    @Column(name = "validita_fine")
    private Date validitaFine;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_camera")
    private Camera camera;
}
