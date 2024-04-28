package thymeleaf.ui.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/active/account")
    public String activeAccount(){
        return "activeAccount";
    }

    @GetMapping("/update")
    public String update(){
        return "update";
    }
}
