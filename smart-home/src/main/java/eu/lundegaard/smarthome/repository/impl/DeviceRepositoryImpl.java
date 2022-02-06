package eu.lundegaard.smarthome.repository.impl;

import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DeviceRepositoryImpl implements DeviceRepository {

    private final List<Device> devices = new ArrayList<>();
    private static Long globalId = 0L;

    private Long getNextId() {
        return ++globalId;
    }

    @Override
    public List<Device> findAll() {
        return devices;
    }

    @Override
    public Device findById(Long deviceId) {
        List<Device> collect = devices.stream()
                .filter(device -> device.getId() == deviceId)
                .collect(Collectors.toList());
        if (collect.size() != 1) {
            throw new IllegalStateException();
        }

        return collect.get(0);
    }

    @Override
    public Device save(Device device) {
        if (device.getId() == null){
            device.setId(getNextId());
            devices.add(device);
            return device;
        }
        Device byId = this.findById(device.getId());
        byId.setState(device.getState())
                .setDeviceName(device.getDeviceName())
                .setRoom(device.getRoom())
                .setConsumedPower(device.getConsumedPower())
                .setFunctionalityPercentage(device.getFunctionalityPercentage());
        return byId;
    }

    @Override
    public Device update(Device device) {
        if (device.getId() == null){
            throw new DeviceNotFoundException(HttpStatus.NOT_FOUND);
        }
        Device byId = this.findById(device.getId());
        byId.setState(device.getState())
                .setDeviceName(device.getDeviceName())
                .setRoom(device.getRoom())
                .setConsumedPower(device.getConsumedPower())
                .setFunctionalityPercentage(device.getFunctionalityPercentage());
        return byId;
    }

    @Override
    public void deleteById(Long deviceId) {
        List<Device> collect = devices.stream()
                .filter(device -> device.getId() == deviceId)
                .collect(Collectors.toList());
        if (collect.size() != 1) {
            throw new IllegalStateException();
        }
        Device device = collect.get(0);
        devices.remove(device);
    }

    @Override
    public boolean existsById(Long deviceId) {
        List<Device> collect = devices.stream()
                .filter(device -> device.getId() == deviceId)
                .collect(Collectors.toList());
        if (collect.size() != 1) {
            return false;
        }
        return true;
    }
}
