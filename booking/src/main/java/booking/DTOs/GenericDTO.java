package booking.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericDTO {
    private Long id;
    private String testo;
    private Integer num;
    private Double prezzoMin;
    private Double prezzoMax;
}
