package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.UserRegisterDto;
import bg.softuni.regalcinema.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    private UserRegisterDto userRegisterDto(){
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String viewRegister(){

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(UserRegisterDto registerDto){

        if (!this.userService.register(registerDto)) {
            return "oops";
        }

        //{
        //    "username": "mario9502",
        //    "password": "123123",
        //    "confirmPassword": "123123",
        //    "email": "mario@mario",
        //    "firstName": "Mario",
        //    "lastName": "Penkov"
        //}

        return "hello-world";
    }
}
