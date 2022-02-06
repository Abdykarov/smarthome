package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.model.device.Device;

import java.util.List;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 3:59 PM
 */
public interface DeviceRepository {

    List<Device> findAll();

    Device findById(Long deviceId);

    Device save(Device device);

    Device update(Device device);

    void deleteById(Long deviceId);

    boolean existsById(Long deviceId);
}
