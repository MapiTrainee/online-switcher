package eu.mapidev.pi.switcher.domain;

public class State {

    private boolean switchOn;

    public boolean isSwitchOn() {
	return switchOn;
    }

    public void setSwitchOn(boolean switchOn) {
	this.switchOn = switchOn;
    }

    public State() {
    }

    public State(boolean switchOn) {
	this.switchOn = switchOn;
    }

}
