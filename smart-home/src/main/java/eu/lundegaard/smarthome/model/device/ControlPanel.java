package eu.lundegaard.smarthome.model.device;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 5:51 AM
 */
@Data
@AllArgsConstructor
public class ControlPanel extends Device{
    private boolean policeCalled;
    private boolean firefightersCalled;
    private boolean houseElectricityIsWorking;
    private boolean floodDetected;

}
