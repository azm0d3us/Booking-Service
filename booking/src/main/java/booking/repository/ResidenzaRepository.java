package booking.repository;

import com.training.booking.entities.Residenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ResidenzaRepository extends JpaRepository<Residenza, Long> {

    boolean existsResidenzaByNomeOrIndirizzo(String nome, String indirizzo);

    Optional<Residenza> findByNomeEqualsIgnoreCase(String nome);

    List<Residenza> findByNomeContainingIgnoreCase(String nome);

    List<Residenza> findByIndirizzoContainingIgnoreCase(String indirizzo);

    @Transactional
    Residenza save(Residenza obj);

    @Transactional
    void delete(Residenza obj);

}
