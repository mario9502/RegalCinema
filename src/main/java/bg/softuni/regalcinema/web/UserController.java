package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.model.dtos.importDtos.UserRegisterDto;
import bg.softuni.regalcinema.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    private UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String viewRegister() {

        return "register";
    }

    @PostMapping("/register")
    public String doRegister
            (@Valid UserRegisterDto registerDto, BindingResult bindingResult, RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("registerData", registerDto);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/users/register";
        }

        if (!this.userService.register(registerDto)) {
            rAtt.addFlashAttribute("registerDto", registerDto);

            return "redirect:/users/register";
        }

        //{
        //    "username": "mario9502",
        //    "password": "123123",
        //    "confirmPassword": "123123",
        //    "email": "mario@mario",
        //    "firstName": "Mario",
        //    "lastName": "Penkov"
        //}

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String viewLogin() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String doLogin(@Valid UserLoginDto loginDto, BindingResult bindingResult, RedirectAttributes rAtt) {
//
//        if (bindingResult.hasErrors()) {
//            rAtt.addFlashAttribute("loginData", loginDto);
//            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);
//
//            return "redirect:/users/login";
//        }
//
//        return "index";
//    }
}
