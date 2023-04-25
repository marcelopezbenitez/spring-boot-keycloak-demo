package py.com.universitaria.controlentradasweb.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Entrada {
    private String nroSocio;
    private String nroCi;
    private String nombre;
    private LocalDate fechaCompra;
    private int catidadComprada;
    private int cantidadUsada;
}
