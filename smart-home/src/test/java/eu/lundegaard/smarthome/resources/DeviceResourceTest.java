package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.json.JSONParser;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ilias Abdykarov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DeviceResource.class)
class DeviceResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllAPI() throws Exception {

        this.mockMvc.perform( MockMvcRequestBuilders
                .get("/api/devices")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void changeState() throws Exception{
        this.mockMvc.perform( MockMvcRequestBuilders
                        .patch("/api/devices/1/BROKEN")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void changeState_StateNotExists() throws Exception{
        this.mockMvc.perform( MockMvcRequestBuilders
                        .patch("/api/devices/1/OLD")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void updateDevice() throws Exception{
        this.mockMvc.perform( MockMvcRequestBuilders
                        .patch("/api/devices/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString)
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}