package org.library.controllers;

import jakarta.validation.Valid;
import org.library.dao.PersonDAO;
import org.library.models.Person;
import org.library.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.peopleList());
        return "people/index";
    }

    @GetMapping("/{person_id}")
    public String show(@PathVariable("person_id") int person_id, Model model) {
        model.addAttribute("person", personDAO.showPerson(person_id));
        model.addAttribute("books", personDAO.getBooksByPersonId(person_id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")  Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "people/new";
        personDAO.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{person_id}/edit")
    public String update(@PathVariable("person_id") int person_id, Model model) {
        model.addAttribute("person", personDAO.showPerson(person_id));
        return "people/edit";
    }

    @PatchMapping("/{person_id}")
    public String edit(@ModelAttribute("person") @Valid Person updatedPerson, BindingResult bindingResult, @PathVariable("person_id") int person_id) {
        if(bindingResult.hasErrors())
            return "people/edit";
        personDAO.updatePerson(person_id, updatedPerson);
        return "redirect:/people";
    }

    @DeleteMapping("/{person_id}")
    public String delete(@PathVariable("person_id") int person_id) {
        personDAO.deletePerson(person_id);
        return "redirect:/people";
    }






}
