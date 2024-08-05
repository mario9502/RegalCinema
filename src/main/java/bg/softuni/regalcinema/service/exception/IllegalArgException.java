package bg.softuni.regalcinema.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgException extends RuntimeException{

    private final String type;

    public IllegalArgException(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
