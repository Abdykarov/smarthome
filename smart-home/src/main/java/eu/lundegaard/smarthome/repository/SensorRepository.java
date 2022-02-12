package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 3:59 PM
 */
@Repository
public interface SensorRepository extends CrudRepository<Sensor, UUID> {

    // complex
    List<Sensor> findAllByRoom(String room);
}
