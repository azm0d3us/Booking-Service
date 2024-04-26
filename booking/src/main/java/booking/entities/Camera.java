package booking.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "camere")
public class  Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camera")
    private Long idCamera;

    @Column(name = "posti_letto")
    private Integer postiLetto;

    @Column(name = "disponibile")
    private boolean disponibile;

    @Column(name = "prezzo_base")
    private Double prezzoBase;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "numero")
    private String numero;

    @Column(name = "info_check_out")
    private String infoCheckOut;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_residenza")
    private Residenza residenza;

    @JsonManagedReference
    @OneToMany(mappedBy = "camera")
    private Set<ListaPrezzi> listini;

    @JsonManagedReference
    @OneToMany(mappedBy = "cameraPrenotata")
    private List<Prenotazione> prenotazioni;
}
