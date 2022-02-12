package eu.lundegaard.smarthome.model.sensor;

import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.model.AbsractEntity;
import eu.lundegaard.smarthome.model.device.Device;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
    @Column(name = "sensor_state")
    @Enumerated(EnumType.STRING)
    private SensorState sensorState;
    @OneToMany
    private List<Device> connectedDevices;
    private String room;
}
