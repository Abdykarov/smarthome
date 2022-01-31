package eu.lundegaard.smarthome.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 7:49 AM
 */
@Getter
@Setter
public class SensorOrRoomNotFoundException extends AppRequestException{
    private final String message;
    private final HttpStatus httpStatus;

    public SensorOrRoomNotFoundException(HttpStatus httpStatus) {
        super("Sensor or room not found", httpStatus);
        this.message = "Device not found";
        this.httpStatus = httpStatus;
    }
}
