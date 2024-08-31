package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
    No es un servlet.
 */

@Controller
public class HelloWorldController {

    private final UserService userService;

    public HelloWorldController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(name = "userId", defaultValue = "1") long userId) {
        final ModelAndView mav = new ModelAndView("helloworld/index");
        mav.addObject("userId", userId);
        mav.addObject("username", userService.findById(userId).get().getUsername());
        return mav;
    }

    @RequestMapping("/{userId:\\d+}")
    public ModelAndView profile(@PathVariable(name = "userId") long userId) {
        final ModelAndView mav = new ModelAndView("helloworld/profile");
        mav.addObject("userId", userId);
        mav.addObject("username", userService.findById(userId).get().getUsername());
        return mav;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("username") String username) {
        final User user = userService.create(username);
        return new ModelAndView("redirect:/" + user.getId());
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public ModelAndView createForm() {
        return new ModelAndView("helloworld/create");
    }

}
