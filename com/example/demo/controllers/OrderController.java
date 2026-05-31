package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Orders;
import com.example.demo.entities.User;
import com.example.demo.services.OrderServices;

@Controller
public class OrderController {
 @Autowired
    private OrderServices orderServices;

    // User ke orders dikhane ke liye
    @GetMapping("/myOrders")
    public String myOrders(Model model, HttpSession session) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        List<Orders> orders =
                orderServices.getOrdersForUser(loggedInUser);

        model.addAttribute("orders", orders);

        return "Orders";
    }
}
