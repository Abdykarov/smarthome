package eu.lundegaard.smarthome.model;

/**
 * @author Ilias Abdykarov
 */
public enum EventType implements EventStringOperation{

    WIND {
        @Override
        public String returnEvent(){
            return "Wind started";
        }
    },
    FIRE {
        @Override
        public String returnEvent(){
            return "Fire started in house";
        }
    },
    SNOW {
        @Override
        public String returnEvent(){
            return "Snow started";
        }
    };

}
