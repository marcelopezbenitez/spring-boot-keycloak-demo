package py.com.universitaria.controlentradasweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusquedaReq {
    private String tipoBusqueda;
    private String valor;
}
