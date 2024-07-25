package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddMarathonDto;
import bg.softuni.regalcinema.service.impl.MarathonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marathons")
public class MarathonController {

    private final MarathonServiceImpl marathonService;

    public MarathonController(MarathonServiceImpl marathonService) {
        this.marathonService = marathonService;
    }

    @PostMapping("/add")
    public String doAdd(@RequestBody AddMarathonDto marathonDto){

        this.marathonService.add(marathonDto);

        //{
        //    "name": "2024 Summer Mega Marathon",
        //    "location": "Ski Pista Bansko",
        //    "start": "25-07-2024 14:00",
        //    "end": "10-08-2024 23:00",
        //    "movies": [ "Harry Potter", "Dune", "Home Alone"],
        //    "description": "Great experience where you can enjoy one of the biggest titles in the cinema world. Pack your stuff, call your friends!"
        //}

        return "hello-world";
    }
}
