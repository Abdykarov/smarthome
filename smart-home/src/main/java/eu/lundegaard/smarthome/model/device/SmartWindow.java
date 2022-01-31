package eu.lundegaard.smarthome.model.device;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 5:47 AM
 */
@Data
@AllArgsConstructor
public class SmartWindow extends Device{
    private boolean closed;
    private boolean cleaned;
}
