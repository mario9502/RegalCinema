package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.UserRegisterDto;
import bg.softuni.regalcinema.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    private UserRegisterDto registerDto(){
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public ModelAndView viewRegister(){

        ModelAndView mnv = new ModelAndView("hello-world");

        return mnv;
    }

    @PostMapping("/register")
    public String doRegister(@RequestBody @Valid UserRegisterDto registerDto){

        this.userService.register(registerDto);

        return "hello-world";
    }

    //{
    //    "username": "test",
    //    "password": "test",
    //    "confirmPassword": "test",
    //    "email": "test",
    //    "firstName": "test",
    //    "lastName": "test"
    //}
}
