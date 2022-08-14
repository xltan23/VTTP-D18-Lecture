package sg.edu.nus.iss.D18.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cart")
public class CartController {


    @GetMapping
    public String getCart(Model model, HttpSession sess) {
        String name = (String)sess.getAttribute("name");
        List<String> cart = (List<String>)sess.getAttribute("cart");

        // If there is no name in session, return to index page
        if (isNull(name)) {
            return "redirect:/index";
        }

        model.addAttribute("name", name.toUpperCase());
        model.addAttribute("cart", cart);

        return "cart";
    }
    
    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String,String> form, Model model, HttpSession sess) {

        List<String> cart = null;

        // Retrieve name from form 
        String name = form.getFirst("name");
        if (!isNull(name)) {
            System.out.println("Name not in session");
            // Instantiate name for this session
            sess.setAttribute("name", name);
            // Instantiate an empty cart for this session
            cart = new LinkedList<>();
            sess.setAttribute("cart", cart);
        }

        // Obtain name and cart from the session
        name = (String)sess.getAttribute("name");
        cart = (List<String>)sess.getAttribute("cart");
        String item = form.getFirst("item");
        if (!isNull(item)) {
            cart.add(item);
        }

        model.addAttribute("name", name.toUpperCase());
        model.addAttribute("cart", cart);

        return "cart";
    }

    private boolean isNull(String s) {
        return ((null == s) || (s.trim().length() <= 0));
    }
}
