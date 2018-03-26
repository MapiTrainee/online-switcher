package eu.mapidev.pi.switcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import eu.mapidev.pi.switcher.domain.State;
import eu.mapidev.pi.switcher.service.StateService;
import eu.mapidev.pi.switcher.service.Exit;

@Controller
public class MainController {

    @Autowired
    private StateService stateService;

    @RequestMapping("/")
    public String getIndex() {
	return "redirect:/index.html";
    }

    @RequestMapping("/off")
    public String getOff() {
	return "redirect:/shutdown.html";
    }

    @RequestMapping("/exit")
    public String shutdown() {
	((Exit) stateService).shutdown();
	return "redirect:/shutdown.html";
    }

    @ResponseBody
    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public State getState() {
	return stateService.getState();
    }

    @ResponseBody
    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public State postState(@RequestBody State state) {
	return stateService.setState(state);
    }

}
