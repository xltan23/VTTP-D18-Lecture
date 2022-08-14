package sg.edu.nus.iss.D18.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    public List<String> deserialize(String s) {
        // Split a string into respective items and add individual items into list
        String[] items = s.split(",");
        List<String> contents = new LinkedList<>();
        for (String i : items) {
            contents.add(i);
        }
        return contents;
    }

    // Join items in the list with a "," using String.join() method
    public String serialize(List<String> c) {
        return String.join(",", c);
    }
}
