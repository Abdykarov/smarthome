package eu.lundegaard.smarthome.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

/**
 * @author Ilias Abdykarov
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppRequestException extends RuntimeException {
    final String message;
    final HttpStatus httpStatus;
}
