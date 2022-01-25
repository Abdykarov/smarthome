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
@RestController
@RequestMapping("api/home")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class HomeResource {

    private HomeDto instance;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void initHome() {
    }

    @PutMapping("/{floorNumber}/rooms")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createRooms(@PathVariable Integer floorNumber, @RequestBody RoomsDto roomsDto) {
        // zkratka created
    }

    @PatchMapping("{floorNumber}")
    public void setFloorsCount(@PathVariable int floorNumber){

    }

    @GetMapping
    public HomeDto getHomeConfiguration(){
       return this.instance;
    }

    @DeleteMapping
    public void deleteHouse(){

    }

}
