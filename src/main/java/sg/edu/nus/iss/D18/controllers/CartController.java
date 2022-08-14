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

        // Retrieve name from the form 
        String name = form.getFirst("name");
        if (isNull(name)) {
            name = "anonymous";
        }

        // Retrieve contents from hidden forms
        String contents = form.getFirst("contents");
        List<String> cart = new LinkedList<>();
        if (!isNull(contents)) {
            // Breakdown into individual items and sort them into a list
            cart = cartSvc.deserialize(contents);
        }

        // Retrieve item to be added to cart, add it to cart
        String item = form.getFirst("item");
        if (!isNull(item)) {
            cart.add(item);
        }
        
        // name to be displayed on webpage
        model.addAttribute("displayName", name.toUpperCase());
        // contents in hidden form fields
        model.addAttribute("contents", cartSvc.serialize(cart));
        // Hold list of items in cart, to be displayed on webpage
        model.addAttribute("cart", cart);
        // Boolean to check if cart is empty (Conditional to show specific contents)
        model.addAttribute("empty", cart.isEmpty());
        // name to be placed in hidden form fields
        model.addAttribute("name", name);
        return "cart";
    }


    public boolean isNull(String s) {
        return ((null == s) || (s.trim().length() <= 0));
    }
}
