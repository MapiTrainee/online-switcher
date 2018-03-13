package eu.mapidev.pi.switcher.util;

import com.pi4j.io.gpio.PinState;

public class PinStateUtil {

    private static int control;
    private static int start;

    private PinStateUtil() {
    }

    public static PinState getStartPinState() {
	return start > 0 ? getSwitchOnPinState() : getSwitchOffPinState();
    }

    public static PinState getSwitchOnPinState() {
	return (control > 0) ? PinState.HIGH : PinState.LOW;
    }

    public static PinState getSwitchOffPinState() {
	return (control > 0) ? PinState.LOW : PinState.HIGH;
    }

    public static void setControl(int control) {
	PinStateUtil.control = control;
    }

    public static void setStart(int start) {
	PinStateUtil.start = start;
    }

    public static boolean isSwitchOnAtStartup() {
	return start > 0;
    }

}
