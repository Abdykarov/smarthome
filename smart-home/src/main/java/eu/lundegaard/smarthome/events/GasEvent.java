package eu.lundegaard.smarthome.events;

import eu.lundegaard.smarthome.house.HouseEmbeddedElement;
import eu.lundegaard.smarthome.iterator.ElectricalOutletIterator;
import eu.lundegaard.smarthome.iterator.EmbeddedElementIterator;
import eu.lundegaard.smarthome.model.HomeDto;
import eu.lundegaard.smarthome.observer.GasEventPublisher;
import eu.lundegaard.smarthome.observer.HouseListener;
import eu.lundegaard.smarthome.resources.HomeResource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@AllArgsConstructor
@Slf4j
public class GasEvent extends EventDto implements GasEventPublisher {

    private HomeResource homeResource;
    private final List<HouseListener> houseListeners = List.of(homeResource.getHomeConfiguration());

    public GasEvent(String eventMessage) {
        super(EventType.GAS.returnEvent());
    }

    @Override
    public void notifyHouseElectricalPanel() {

    }

    @Override
    public void notifyHouseElectricalOutlets() {
        HomeDto houseListener = (HomeDto) houseListeners.get(1);
        EmbeddedElementIterator electricalOutletIterator = houseListener.createElectricalOutletIterator();
        while (electricalOutletIterator.hasNext()){
            HouseEmbeddedElement next = electricalOutletIterator.getNext();

        }
    }
}
