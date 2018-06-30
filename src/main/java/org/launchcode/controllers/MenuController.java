package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenuForm(Model model) {
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenuForm(@ModelAttribute @Valid Menu newMenu,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);
        return "redirect:view/" + newMenu.getId();
    }

    @RequestMapping(value = "viewMenu/{id}", method = RequestMethod.GET)
    public String ViewMenu (@PathVariable("id") int id, Model model) {
        return "menu/view/" + id;
    }

    @RequestMapping(value = "addItem", method = RequestMethod.GET)
    public String displayAddItemForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("title", "Add Item");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    }

