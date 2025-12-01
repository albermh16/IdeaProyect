package es.daw.hotelesmvc.controller;

import es.daw.hotelesmvc.dto.HabitacionDTO;
import es.daw.hotelesmvc.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;



    @GetMapping("{codigoHotel}/buscar")
    public String buscarHabitaciones(Model model,
                                     @PathVariable String codigoHotel,
                                     @RequestParam(required = false) Integer tamanoMin,
                                     @RequestParam(required = false) BigDecimal precioMin,
                                     @RequestParam(required = false) BigDecimal precioMax) {
        List<HabitacionDTO> habitaciones = habitacionService
                .buscarHabitacion(codigoHotel, tamanoMin, precioMin, precioMax);

        model.addAttribute("habitaciones", habitaciones);
        model.addAttribute("codigoHotel", codigoHotel);

        return "verHabitaciones";
    }

    @PostMapping("/{codigoHabitacion}/eliminar")
    public String eliminarHabitacion(@PathVariable String codigoHabitacion, @RequestParam String codigoHotel) {

        habitacionService.eliminarHabitacion(codigoHabitacion);


        return  "redirect:/habitaciones/" + codigoHotel + "/buscar";
    }

    @PostMapping("/{codigoHotel}")
    public String crearHabitacion(HabitacionDTO habitacion, @PathVariable String codigoHotel, Model model) {
        habitacionService.crearHabitacion(habitacion, codigoHotel);

        return "redirect:/habitaciones/" + codigoHotel +"/buscar";
    }
}
