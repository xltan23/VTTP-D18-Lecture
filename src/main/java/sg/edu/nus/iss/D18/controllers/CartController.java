package sg.edu.nus.iss.D18.controllers;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.D18.services.CartService;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private CartService cartSvc;
    
    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String,String> form, Model model) {
        String name = form.getFirst("name");
        if (isNull(name)) {
            name = "anonymous";
        }

        String contents = form.getFirst("contents");
        List<String> cart = new LinkedList<>();
        if (!isNull(contents)) {
            cart = cartSvc.deserialize(contents);
        }

        String item = form.getFirst("item");
        if (!isNull(item)) {
            cart.add(item);
        }
        
        model.addAttribute("displayName", name.toUpperCase());
        model.addAttribute("contents", cartSvc.serialize(cart));
        model.addAttribute("cart", cart);
        model.addAttribute("empty", cart.isEmpty());
        model.addAttribute("name", name);
        return "cart";
    }


    public boolean isNull(String s) {
        return ((null == s) || (s.trim().length() <= 0));
    }
}
