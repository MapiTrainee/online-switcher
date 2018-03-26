package eu.mapidev.pi.switcher.service;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eu.mapidev.pi.switcher.domain.State;
import eu.mapidev.pi.switcher.util.PinStateUtil;
import java.io.IOException;

@Service
public class RPiService implements StateService, Exit {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static State state = new State(PinStateUtil.isSwitchOnAtStartup());

    @Autowired
    private GpioPinDigitalOutput pin;

    @Override
    public State setState(State state) {
	pin.setState((state.isSwitchOn()) ? PinStateUtil.getSwitchOnPinState() : PinStateUtil.getSwitchOffPinState());
	return RPiService.state = state;
    }

    @Override
    public State getState() {
	return state;
    }

    @Override
    public void shutdown() {
	try {
	    Runtime.getRuntime().exec("sudo shutdown -h now");
	} catch (IOException ex) {
	    logger.error("Shutdown command dosen't work!", ex);
	}
    }

}
