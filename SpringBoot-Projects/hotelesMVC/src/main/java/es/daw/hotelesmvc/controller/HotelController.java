package es.daw.hotelesmvc.controller;


import es.daw.hotelesmvc.dto.HotelDTO;
import es.daw.hotelesmvc.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/hoteles")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public String buscarHoteles(Model model){
        List<HotelDTO> hoteles= hotelService.listarHoteles();
        model.addAttribute("hoteles",hoteles);

        return "buscarHoteles";

    }
}
