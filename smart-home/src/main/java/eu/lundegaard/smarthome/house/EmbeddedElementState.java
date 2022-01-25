package eu.lundegaard.smarthome.house;

/**
 * @author Ilias Abdykarov
 */
public abstract class EmbeddedElementState {

    HouseEmbeddedElement houseEmbeddedElement;

    public EmbeddedElementState(HouseEmbeddedElement houseEmbeddedElement) {
        this.houseEmbeddedElement = houseEmbeddedElement;
    }

    public abstract void onDisable();
    public abstract void onEnable();
}
