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
    }

    @PutMapping("/{floorNumber}/rooms")
    @ResponseStatus(code = HttpStatus.OK)
    public void createRooms(@RequestBody RoomsDto roomsDto, @PathVariable Integer floorNumber) {
    }

    @PatchMapping("{floorNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public void setFloorsCount(@PathVariable int floorNumber){

    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public HomeDto getHomeConfiguration(){
       return this.instance;
    }

    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteHouse(){

    }

}
