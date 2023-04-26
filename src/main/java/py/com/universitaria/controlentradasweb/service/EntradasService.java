package py.com.universitaria.controlentradasweb.service;

import org.springframework.stereotype.Service;
import py.com.universitaria.controlentradasweb.models.Entrada;
import py.com.universitaria.controlentradasweb.models.Respuesta;
import py.com.universitaria.controlentradasweb.repository.EntradasRepository;

@Service
public class EntradasService {

    public static final String SOCIO_TYPE = "socio";
    final EntradasRepository repository;

    public EntradasService(EntradasRepository repository) {
        this.repository = repository;
    }

    public Entrada obtenerEntrada(String valor, String tipoBusqueda) {
        if(tipoBusqueda.equalsIgnoreCase(SOCIO_TYPE)){
            return repository.obtenerEntradaPorFiltro(Integer.valueOf(valor), null);
        }else{
            return repository.obtenerEntradaPorFiltro(null, valor);
        }
    }

    public Respuesta usarEntradas(int cantidad, String nroSocio) {
        Entrada entrada = obtenerEntrada(nroSocio, SOCIO_TYPE);
        int cantdispo = entrada.getCatidadComprada() - entrada.getCantidadUsada();
        if(cantdispo<cantidad){
            return new Respuesta("La cantidad seleccionada no esta disponible","99");
        }

        boolean res = repository.usarEntrdas(nroSocio, cantidad+entrada.getCantidadUsada());
        if(res){
            return new Respuesta("Entradas canjeadas con exito", "0");
        }else {
            return new Respuesta("Ocurrio un error al canjear las entradas", "666");
        }


    }
}
