package com.lms.application.configuration.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/",
            "/dashboard",
            "/users",
            "/roles",
            "/languages",
            "/configurationProperties",
            "/materialTypes",
            "/resources",
            "/clients",
            "/schools",
            "/categories"})
    public String index() {
        return "forward:/index.html";
    }
}
