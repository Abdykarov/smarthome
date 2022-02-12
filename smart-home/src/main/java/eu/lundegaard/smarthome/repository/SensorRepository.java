package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 3:59 PM
 */
@Repository
public interface SensorRepository extends CrudRepository<Sensor, UUID> {


    List<Sensor> findByRoom(String room);

    @Query("SELECT u.connectedDevices FROM Sensor u WHERE u.id = ?1")
    List<Device> getConnectedDevices(UUID sensorId);

    @Query(nativeQuery = true, value = "SELECT * FROM SENSOR s WHERE s.sensor_state=:#{#sensorState.name()}")
    List<Sensor> getAllByState(SensorState sensorState);

}
