package eu.lundegaard.smarthome.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Ilias Abdykarov
 */
@Getter
@Setter
public class DeviceNotFoundException extends AppRequestException{

    private final String message;
    private final HttpStatus httpStatus;

    public DeviceNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
