package br.com.kimae.multitenacydemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String list() {
        return "loginPage";
    }

}
