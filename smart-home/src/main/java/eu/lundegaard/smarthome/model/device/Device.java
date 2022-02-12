package eu.lundegaard.smarthome.model.device;

import eu.lundegaard.smarthome.model.AbsractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ilias Abdykarov
 */
@Entity
@Data
@Accessors(chain = true)
public class Device extends AbsractEntity {
    private int consumedPower;
    private int functionalityPercentage;
    private String deviceName;
    private DeviceState state;
    private String room;
}
