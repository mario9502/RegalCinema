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

        if (!this.marathonService.add(marathonDto)) {
            return "oops";
        }

        return "hello-world";
    }
}
