package eu.lundegaard.smarthome.model.sensor;

import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.model.AbsractEntity;
import eu.lundegaard.smarthome.model.device.Device;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@Data
@Entity
@Table(name = "SENSOR")
@Accessors(chain = true)
public class Sensor extends AbsractEntity {
    private SensorType sensorType;
    private SensorState sensorState;
    @OneToMany
    private List<Device> connectedDevices;
    private String room;
}
