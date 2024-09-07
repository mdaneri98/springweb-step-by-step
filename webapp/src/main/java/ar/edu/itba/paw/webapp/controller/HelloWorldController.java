package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.services.UserService;
import ar.edu.itba.paw.webapp.auth.PawUserDetails;
import ar.edu.itba.paw.webapp.form.UserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authenticationManager;


    public HelloWorldController(UserService userService, final AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
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
        final User user = userService.create(userForm.getUsername(), userForm.getPassword());

        // "Generar una sesión" (así no redirije a /login)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword(), null);
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authenticationToken));

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

    /*
     * Inserta el usuario logueado en todos los ModelAndViews que se retornan.
     * El 'binding' indica si el valor debe estar vinculado al modelo de forma que
     * pueda ser utilizado para la vinculación de datos en formularios.
     * En este caso, 'binding = false' indica que el usuario no será utilizado
     * para la vinculación de formularios, solo se añadirá al modelo para que esté
     * disponible en las vistas.
     *
     * Basicamente el 'binding' hace que el modelo sea solo lectura.
     */
    @ModelAttribute(value = "loggedUser", binding = false)
    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof PawUserDetails pud) {
            return pud.getUser();
        }
        return null;
    }

}
