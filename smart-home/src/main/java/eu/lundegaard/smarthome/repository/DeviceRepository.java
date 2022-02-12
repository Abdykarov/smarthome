package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.model.device.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 3:59 PM
 * Crudrepository because we dont have pagination and sorting problems
 */
public interface DeviceRepository extends CrudRepository<Device, UUID> {

}
