package py.com.universitaria.controlentradasweb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import py.com.universitaria.controlentradasweb.models.BusquedaReq;
import py.com.universitaria.controlentradasweb.models.Entrada;
import py.com.universitaria.controlentradasweb.models.Respuesta;
import py.com.universitaria.controlentradasweb.service.EntradasService;

import java.security.Principal;

@Controller
@RequestMapping("/entradas")
public class EntradasController {

    final
    EntradasService entradasService;

    public EntradasController(EntradasService entradasService) {
        this.entradasService = entradasService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("busquedaReq", new BusquedaReq("cedula",null));
        return "busqueda";
    }

    @PostMapping(path = "/detalles")
    public String customers(
            @ModelAttribute BusquedaReq busquedaReq,
            Principal principal, Model model) {
        Entrada entrada = entradasService.obtenerEntrada(busquedaReq.getValor(), busquedaReq.getTipoBusqueda());
        model.addAttribute("evento", "Dia de la Madre");
        model.addAttribute("username", principal.getName());
        if(entrada==null){
            model.addAttribute("error", "no se encontraron resultados");
            return "busqueda";
        }else{
            model.addAttribute("entrada", entrada);

        }

        return "detalles";
    }

    @PostMapping("/canje")
    @ResponseBody
    public ResponseEntity<Respuesta> usarEntrdas(
            @RequestParam("cantidad") Integer cant,
            @RequestParam("socio") String nroSocio
    ){
        Respuesta respuesta = entradasService.usarEntradas(cant, nroSocio);
        return ResponseEntity.ok(respuesta);
    }

}
