package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.model.ConfigDto;
import eu.lundegaard.smarthome.model.HomeDto;
import eu.lundegaard.smarthome.model.RoomsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ilias Abdykarov
 */
@RequestMapping("api/home")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class HomeResource {

    private HomeDto instance;

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public void initHome() {
        if (this.instance == null) {
            this.instance = new HomeDto();
        }

        log.warn("Home already created");
    }

    @PutMapping("/{floorNumber}/rooms")
    @ResponseStatus(code = HttpStatus.OK)
    public void createRooms(@RequestBody RoomsDto roomsDto, @PathVariable Integer floorNumber) {
        List<String> rooms = roomsDto.getRooms();
        for (String room : rooms) {
            if(floorNumber > this.instance.getFloorsCount()){
               throw new RuntimeException("Invalid floor number");
            }
            this.instance.getRooms().put(floorNumber,room);
        }
        log.info("Rooms are placed in the first floor");
    }

    @PatchMapping("{floorNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public void setFloorsCount(@PathVariable int floorNumber){
        this.instance.setFloorsCount(floorNumber);
        log.info("Floors count changed");
    }

    @PostMapping()
    public void createHouseByConfig(ConfigDto configDto){

    }

}
