package bg.softuni.regalcinema.web;

import bg.softuni.regalcinema.service.exception.IllegalArgException;
import bg.softuni.regalcinema.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException onfe){

        ModelAndView mnv = new ModelAndView("object-not-found");
        mnv.addObject("type", onfe.getType());

        return mnv;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgException.class)
    public ModelAndView handleIllegalArgException(IllegalArgException exception){
        ModelAndView mnv = new ModelAndView("bad-request");
        mnv.addObject("type", exception.getType().toLowerCase());

        return mnv;
    }
}
