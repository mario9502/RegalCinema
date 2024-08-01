package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.AddMarathonDto;
import bg.softuni.regalcinema.service.impl.MarathonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/marathons")
public class MarathonController {

    private final MarathonServiceImpl marathonService;

    public MarathonController(MarathonServiceImpl marathonService) {
        this.marathonService = marathonService;
    }

    @ModelAttribute("marathonData")
    private AddMarathonDto marathonDto(){
        return new AddMarathonDto();
    }

    @GetMapping("/add")
    public String viewAdd(){

        return "marathon-add";
    }


    @PostMapping("/add")
    public String doAdd(AddMarathonDto marathonDto){

        if (!this.marathonService.add(marathonDto)) {
            return "oops";
        }

        return "hello-world";
    }
}
