package py.com.universitaria.controlentradasweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.universitaria.controlentradasweb.models.Entrada;
import py.com.universitaria.controlentradasweb.repository.EntradasRepository;

@Service
public class EntradasService {

    final EntradasRepository repository;

    public EntradasService(EntradasRepository repository) {
        this.repository = repository;
    }

    public Entrada obtenerEntrada(String valor, String tipoBusqueda) {
        if(tipoBusqueda.equalsIgnoreCase("socio")){
            return repository.obtenerEntradaPorFiltro(Integer.valueOf(valor), null);
        }else{
            return repository.obtenerEntradaPorFiltro(null, valor);
        }
    }
}
