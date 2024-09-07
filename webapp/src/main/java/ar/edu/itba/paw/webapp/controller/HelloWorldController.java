package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.webapp.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView create(@Valid @ModelAttribute("userForm") final UserForm userForm, final BindingResult errors) {
        if (errors.hasErrors()) {
            return createForm(userForm);
        }

        final User user = userService.create(userForm.getUsername());
        return new ModelAndView("redirect:/" + user.getId());
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public ModelAndView createForm(@ModelAttribute("userForm") final UserForm userForm) {
        /* ModelAttribute agrega al `mav`: <K: "userForm",V: userForm > */
        return new ModelAndView("helloworld/create");
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/helloworld/login");
    }

}
