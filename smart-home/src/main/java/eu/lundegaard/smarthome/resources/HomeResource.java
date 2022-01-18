package eu.lundegaard.smarthome.resources;

import eu.lundegaard.smarthome.model.HomeDto;
import eu.lundegaard.smarthome.model.RoomsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@RequestMapping("api/home")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class HomeResource {

    private HomeDto instance;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
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

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public HomeDto getHomeConfiguration(){
        if(this.instance == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "home not found"
            );
        }
        log.info("House configuration returned");
        return this.instance;
    }

    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteHouse(){
        this.instance = null;
        log.info("House configuration deleted");
    }

}
