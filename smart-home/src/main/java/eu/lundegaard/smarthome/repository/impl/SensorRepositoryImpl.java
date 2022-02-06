package eu.lundegaard.smarthome.repository.impl;

import eu.lundegaard.smarthome.exception.SensorOrRoomNotFoundException;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import eu.lundegaard.smarthome.repository.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SensorRepositoryImpl implements SensorRepository {

    private final List<Sensor> sensors = new ArrayList<>();
    private static Long globalId = 0L;

    private Long getNextId() {
        return ++globalId;
    }

    @Override
    public List<Sensor> findAll() {
        return sensors;
    }

    @Override
    public Sensor findById(Long sensorId) {
        List<Sensor> collect = sensors.stream()
                .filter(sensor -> sensor.getId() == sensorId)
                .collect(Collectors.toList());
        if (collect.size() != 1) {
            throw new IllegalStateException();
        }

        return collect.get(0);
    }

    @Override
    public Sensor save(Sensor sensor) {
        if (sensor.getId() == null){
            sensor.setId(getNextId());
            sensors.add(sensor);
            return sensor;
        }
        Sensor byId = this.findById(sensor.getId());
        byId.setSensorState(sensor.getSensorState())
                .setSensorType(sensor.getSensorType())
                .setRoom(sensor.getRoom())
                .setConnectedDevices(sensor.getConnectedDevices());
        return byId;
    }

    @Override
    public Sensor update(Sensor sensor) {
        if (sensor.getId() == null){
            throw new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND);
        }
        Sensor byId = this.findById(sensor.getId());
        byId.setSensorState(sensor.getSensorState())
                .setSensorType(sensor.getSensorType())
                .setRoom(sensor.getRoom())
                .setConnectedDevices(sensor.getConnectedDevices());
        return byId;
    }

    @Override
    public void deleteById(Long sensorId) {
        List<Sensor> collect = sensors.stream()
                .filter(sensor -> sensor.getId() == sensorId)
                .collect(Collectors.toList());
        if (collect.size() != 1) {
            throw new IllegalStateException();
        }
        Sensor sensor = collect.get(0);
        sensors.remove(sensor);
    }

    @Override
    public List<Sensor> findAllByRoom(String hall) {
        return sensors.stream().filter(sensor -> sensor.getRoom().equals(hall))
                .collect(Collectors.toList());
    }
}
