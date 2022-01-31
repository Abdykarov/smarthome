package eu.lundegaard.smarthome.resources;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Ilias Abdykarov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DeviceResource.class)
class DeviceResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll_ReturnsAll() throws Exception {

    }

}