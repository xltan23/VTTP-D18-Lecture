package sg.edu.nus.iss.D18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cart")
public class CartController {
    
    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String,String> form, Model model) {
        String name = form.getFirst("name");
        if ((null == name)||(name.trim().length() <= 0)) {
            name = "anonymous";
        }
        model.addAttribute("name", name.toUpperCase());
        return "cart";
    }
}
