package eu.lundegaard.smarthome.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Ilias Abdykarov
 */
@Getter
@Setter
public class DifferentRoomsException extends AppRequestException{
    private final String message;
    private final HttpStatus httpStatus;

    public DifferentRoomsException(HttpStatus httpStatus) {
        super("Sensor or room not found", httpStatus);
        this.message = "Device not found";
        this.httpStatus = httpStatus;
    }
}
