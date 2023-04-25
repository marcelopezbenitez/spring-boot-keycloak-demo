package py.com.universitaria.controlentradasweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import py.com.universitaria.controlentradasweb.models.BusquedaReq;
import py.com.universitaria.controlentradasweb.service.EntradasService;

import java.security.Principal;

@Controller
public class EntradasController {

    final
    EntradasService entradasService;

    public EntradasController(EntradasService entradasService) {
        this.entradasService = entradasService;
    }

    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("busquedaReq", new BusquedaReq("cedula",null));
        return "busqueda";
    }

    @PostMapping(path = "/detalles")
    public String customers(
            @ModelAttribute BusquedaReq busquedaReq,
            Principal principal, Model model) {
        model.addAttribute("evento", "Dia de la Madre");
        model.addAttribute("username", principal.getName());
        model.addAttribute("entrada", entradasService.obtenerEntrada(busquedaReq.getValor(), busquedaReq.getTipoBusqueda()));
        return "detalles";
    }

}
