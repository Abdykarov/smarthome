package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.sensor.Sensor;

import java.util.List;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 3:59 PM
 */
public interface SensorRepository {

    List<Sensor> findAll();

    Sensor findById(Long sensorId);

    Sensor save(Sensor sensor);

    Sensor update(Sensor sensor);

    void deleteById(Long sensorId);
}
