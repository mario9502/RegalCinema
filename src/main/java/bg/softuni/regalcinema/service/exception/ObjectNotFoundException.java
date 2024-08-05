package bg.softuni.regalcinema.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

    private final String type;

    public ObjectNotFoundException(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
