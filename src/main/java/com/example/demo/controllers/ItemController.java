package com.example.demo.controllers;

import com.example.demo.entity.Item;
import com.example.demo.entity.Manufacturer;
import com.example.demo.model.Users;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ManufacturerRepository;
import com.example.demo.service.ItemService;
import com.example.demo.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ManufacturerService manufacturerService;


    @GetMapping("/items")
    @PreAuthorize("isAuthenticated()")
    public String items(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        List<Manufacturer> manufacturers = manufacturerService.findAllManufacturers();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("items", itemService.findAllItems());
        return "items";
    }


    @GetMapping("/items/new")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addItemForm(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        //List<Manufacturer> manufacturers = manufacturerService.findAllManufacturers();
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        Item item = new Item();
        model.addAttribute("item", item);
        return "additem";
    }


    @PostMapping("/itemsn")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String saveItem(@ModelAttribute("item") Item item,
                           @RequestParam(name = "manufacturer_id") Long manufacturerId, Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        Manufacturer manufacturer = manufacturerService.findOneById(manufacturerId);
        if (manufacturer != null) {
            item.setManufacturer(manufacturer);
        }
        itemService.saveItem(item);
        return "redirect:/items";
    }


    @GetMapping("/details/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        Item item = itemService.getItem(id);
        model.addAttribute("item", item);
        return "details";
    }


//    @GetMapping("items/update")
//    public String updateItemForm(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("item", itemService.findOneById(id));
//        return "details";
//    }
//
//    @PostMapping("/items/update/{id}")
//    public String updateStudent(@PathVariable("id") Long id,
//                                @ModelAttribute("item") Item item){
//        Item existingItem = itemService.findOneById(id);
//        existingItem.setId(id);
//        existingItem.setName(item.getName());
//        existingItem.setPrice(item.getPrice());
//        existingItem.setAmount(item.getAmount());
//    //    existingItem.setManufacturer(item.getManufacturer());
//        itemService.saveItem(existingItem);
//        return "redirect:/items";
//    }


    @GetMapping("items/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String updateItemForm(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        Item item = new Item();
        model.addAttribute("item", item);
        return "details";
    }

    @PostMapping("/item")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String updateStudent(@ModelAttribute("item") Item item,
                                @RequestParam(name = "manufacturer_id") Long manufacturerId, Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        Manufacturer manufacturer = manufacturerService.findOneById(manufacturerId);
        if (manufacturer != null) {
            item.setManufacturer(manufacturer);
        }
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String delete(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        itemService.deleteItem(id);

        return "redirect:/items";
    }


    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String login() {
        return "/login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "login";
    }


    @GetMapping("/403")
    public String accessDeniedPage(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        return "403";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        return "profile";
    }

    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users currentUser = (Users) authentication.getPrincipal();
            return  currentUser;
        }
        return null;
    }
}


