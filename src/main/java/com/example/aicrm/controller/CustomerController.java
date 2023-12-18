package com.example.aicrm.controller;

import com.example.aicrm.model.Customer;
import com.example.aicrm.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/main")
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "redirect:/error";
    }

    @GetMapping("/customers")
    public String getAllCustomersHtml(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/register")
    public String registerHtml(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "register";
    }

    @GetMapping("/customers/edit/{customerId}")
    public String editCustomerHtml(@PathVariable Long customerId, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(customerId));
        return "update";
    }

    @PostMapping("/customer/{customerId}")
    public String edit_customer(@PathVariable Long customerId, @ModelAttribute("customer") Customer customer) {
        Customer excitingCustomer = customerService.getCustomerById(customerId);

        excitingCustomer.setCustomerId(customer.getCustomerId());
        excitingCustomer.setEmail(customer.getEmail());
        excitingCustomer.setUsername(customer.getUsername());
        excitingCustomer.setPassword(customer.getPassword());

        customerService.updateCustomer(excitingCustomer);

        return "redirect:/customers";
    }

    @PostMapping("/customers")
    public String createCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{customerId}")
    public String deleteCustomers(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customers";
    }
}