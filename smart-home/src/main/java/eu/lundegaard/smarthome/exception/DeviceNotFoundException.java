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

    public DeviceNotFoundException(HttpStatus httpStatus) {
        super("Device not found", httpStatus);
        this.message = "Device not found";
        this.httpStatus = httpStatus;
    }
}
