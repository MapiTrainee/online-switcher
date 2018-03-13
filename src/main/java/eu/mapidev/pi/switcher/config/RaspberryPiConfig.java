package eu.mapidev.pi.switcher.config;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.system.SystemInfo;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import eu.mapidev.pi.switcher.util.PinStateUtil;

@Configuration
public class RaspberryPiConfig {

    @Value("${pi.pin}")
    private int pin;

    @Value("${pi.pin.start.state}")
    private int start;

    @Value("${switch.control.state}")
    private int control;
    
    @PostConstruct
    void init(){
	PinStateUtil.setControl(control);
	PinStateUtil.setStart(start);
    }

    @Bean
    public GpioController getGPIO() {
	return GpioFactory.getInstance();
	//gpio.shutdown();
    }

    @Bean
    public GpioPinDigitalOutput getPin(GpioController gpio) {
	GpioPinDigitalOutput output = getGPIO().provisionDigitalOutputPin(RaspiPin.allPins(SystemInfo.BoardType.RaspberryPi_3B)[pin], "MyLED", PinStateUtil.getStartPinState());
	output.setShutdownOptions(true, PinStateUtil.getStartPinState());
	return output;
    }
}
