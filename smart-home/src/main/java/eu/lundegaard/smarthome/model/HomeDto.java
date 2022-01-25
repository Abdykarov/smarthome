package eu.lundegaard.smarthome.model;

import eu.lundegaard.smarthome.house.ElectricalOutlet;
import eu.lundegaard.smarthome.house.HouseEmbeddedElement;
import eu.lundegaard.smarthome.iterator.ElectricalOutletIterator;
import eu.lundegaard.smarthome.iterator.EmbeddedElementIterator;
import eu.lundegaard.smarthome.iterator.HouseIterator;
import eu.lundegaard.smarthome.observer.ElectricalOutletListener;
import eu.lundegaard.smarthome.observer.ElectricalPanelListener;
import eu.lundegaard.smarthome.observer.HouseListener;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ilias Abdykarov
 */
@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeDto implements HouseListener, HouseIterator {
    int floorsCount;
    Map<Integer, String> rooms = new HashMap<Integer, String >();
    List<SensorDto> sensors;
    List<ElectricalOutletListener> electricalOutlets;
    ElectricalPanelListener electricalPanel;

    @Override
    public EmbeddedElementIterator createElectricalOutletIterator() {
        return new ElectricalOutletIterator(electricalOutlets);
    }
}
