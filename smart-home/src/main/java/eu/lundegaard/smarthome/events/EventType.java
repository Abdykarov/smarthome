package eu.lundegaard.smarthome.events;

/**
 * @author Ilias Abdykarov
 */
public enum EventType implements EventStringOperation {

    STRONG_WIND {
        private WindEvent instance;

        @Override
        public WindEvent returnEvent(){
            if(instance == null){
                this.instance = new WindEvent();
                return this.instance;
            }
            return this.instance;
        }
    }

}
