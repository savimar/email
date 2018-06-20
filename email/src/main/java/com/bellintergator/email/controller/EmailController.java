package com.bellintergator.email.controller;

import com.bellintergator.email.service.EmailService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;


@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/")
    public ModelAndView home(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal) {

        ModelAndView modelAndView = new ModelAndView("index" /*"external"*/);

        AccessToken token = principal.getKeycloakSecurityContext().getToken();

        String idToken = token.getId();
        String firstName = token.getGivenName();
        String lastName = token.getFamilyName();
        String bellOneUserId = token.getSubject();
        String login = token.getPreferredUsername();
        String email = token.getEmail();
        Set realmRoles = token.getRealmAccess().getRoles();
        Map clientRoles = token.getResourceAccess();
        long tokenTime = token.getAuthTime();
        long tokenExpireTime = token.getExpiration();/*?*/


        return modelAndView;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ModelAndView sendEmail(@RequestParam String recipient, @RequestParam String subject, @RequestParam String message) {
        ModelAndView modelAndView = new ModelAndView();

        boolean isSend = emailService.sendMail(recipient, subject, message);

        if (isSend) {
            modelAndView.setViewName("success");
        } else {
            modelAndView.setViewName("errors");
        }


        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal/*@RequestParam String login,  @RequestParam String password*/) {
        AccessToken token = principal.getKeycloakSecurityContext().getToken();

        String id = token.getId();
        String firstName = token.getGivenName();
        String lastName = token.getFamilyName();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");


        return modelAndView;
    }


}
