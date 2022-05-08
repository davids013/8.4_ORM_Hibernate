package ru.netology.orm_hibernate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/methods")
public class MethodSecurityController {

    @GetMapping("/read")
    @Secured("ROLE_READ")
    public String read() {
        final String response = "You can READ";
        System.out.println(response);
        return response;
    }

    @GetMapping("/write")
    @RolesAllowed("ROLE_WRITE")
    public String write() {
        final String response = "You can WRITE";
        System.out.println(response);
        return response;
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_DELETE', 'ROLE_WRITE')")
    public String delete() {
        final String response = "You can DELETE";
        System.out.println(response);
        return response;
    }

    @GetMapping("/check")
    @PostAuthorize("#username == authentication.principal.username " +
            "and returnObject.contains(authentication.principal.username)")
    public String check(@RequestParam String username) {
        final String response = "Correct user " + username;
        System.out.println(response);
        return response;
    }
}
