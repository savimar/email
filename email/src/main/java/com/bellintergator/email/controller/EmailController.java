package com.bellintergator.email.controller;

import com.bellintergator.email.service.EmailService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

   /* @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/resources/templates").setViewName("index");
    }*/

  /*  @RequestMapping(value = "/", method = RequestMethod.GET)*/
  @GetMapping(value="/"/*, method=RequestMethod.GET*/)
    public ModelAndView home(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal) {
      /*index*/
       ModelAndView modelAndView = new ModelAndView("index");
      //  modelAndView.setViewName();
   //   Map<String, String> model = new HashMap<>();
     // model.put("name", "Alexey");
  //    new ModelAndView("index", model);
//      AccessToken token = principal.getKeycloakSecurityContext().getToken();

     /* String id = token.getId();
      String firstName = token.getGivenName();
      String lastName = token.getFamilyName();
      */
      return /*"index"*//*"index"*/ modelAndView;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ModelAndView sendEmail(@RequestParam String recipient,  @RequestParam String subject, @RequestParam String message) {
        ModelAndView modelAndView = new ModelAndView();

      boolean isSend =  emailService.sendMail(recipient, subject, message);

if(isSend) {
    modelAndView.setViewName("success");
}
else {
    modelAndView.setViewName("errors");
}




        //имя представления, куда нужно будет перейти
        //  modelAndView.setViewName("secondPage");

        //записываем в атрибут userJSP (используется на странице *.jsp объект user
        // modelAndView.addObject("email", email);

        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }

    @GetMapping(value = "/login")
    public ModelAndView login(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal/*@RequestParam String login,  @RequestParam String password*/) {
        AccessToken token = principal.getKeycloakSecurityContext().getToken();

        String id = token.getId();
        String firstName = token.getGivenName();
        String lastName = token.getFamilyName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
       /* modelAndView.addObject("sity", sity);
        modelAndView.setViewName("weather");*/

int a = 0;

        //имя представления, куда нужно будет перейти
        //  modelAndView.setViewName("secondPage");

        //записываем в атрибут userJSP (используется на странице *.jsp объект user
        // modelAndView.addObject("email", email);

        return modelAndView; /*"index"*//*;//после уйдем на представление, указанное чуть выше, если оно будет найдено.*/
    }


}
