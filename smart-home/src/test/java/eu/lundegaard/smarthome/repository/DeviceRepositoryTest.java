package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ilias Abdykarov 12.02.2022
 */
@RunWith(SpringRunner.class)
@DataJpaTest
class DeviceRepositoryTest implements WithAssertions {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(deviceRepository).isNotNull();
        assertThat(entityManager).isNotNull();
    }

    @Test
    void findDevice() {
        UUID id = entityManager.persistAndFlush(getDevice()).getId();

        Optional<Device> device = deviceRepository.findById(id);
        Device device1 = device.get();

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(device1.getDeviceName()).isEqualTo("Smart doors");
            softAssertions.assertThat(device1.getId()).isEqualTo(id);
        });
    }

    @Test
    void createDevice() {
        Device device = getDevice();

        Device save = deviceRepository.save(device);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(save).isNotNull();
            softAssertions.assertThat(save.getId()).isNotNull();
        });
    }

    @Test
    void updateDevice() {
        UUID id = entityManager.persistAndFlush(getDevice()).getId();

        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(HttpStatus.NOT_FOUND));

        device.setDeviceName("Smart windows");

        Device save = deviceRepository.save(device);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(save).isNotNull();
            softAssertions.assertThat(device.getDeviceName()).isEqualTo("Smart windows");
        });
    }

    @Test
    void deleteDevice() {
        UUID id = entityManager.persistAndFlush(getDevice()).getId();

        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(HttpStatus.NOT_FOUND));

        deviceRepository.deleteById(id);

        assertThat(deviceRepository.findById(id).isEmpty()).isTrue();
    }

    private Device getDevice() {
        return new Device()
                .setRoom("Hall")
                .setState(DeviceState.ACTIVE)
                .setDeviceName("Smart doors")
                .setConsumedPower(0)
                .setFunctionalityPercentage(100);
    }

}