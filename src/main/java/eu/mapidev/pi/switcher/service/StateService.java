package eu.mapidev.pi.switcher.service;

import eu.mapidev.pi.switcher.domain.State;

public interface StateService {

    State setState(State state);

    public State getState();
    
}
