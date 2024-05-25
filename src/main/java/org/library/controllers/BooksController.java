package org.library.controllers;

import jakarta.validation.Valid;
import org.library.dao.BookDAO;
import org.library.dao.PersonDAO;
import org.library.models.Book;
import org.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.bookList());
        return "books/index";
    }

    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int book_id, @ModelAttribute("person") Person person, Model model) {
        model.addAttribute("book", bookDAO.showBook(book_id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(book_id);

        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.peopleList());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{book_id}/edit")
    public String update(@PathVariable("book_id") int book_id, Model model) {
        model.addAttribute("book", bookDAO.showBook(book_id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String edit(@PathVariable("book_id") int book_id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/edit";
        bookDAO.updateBook(book_id, book);
        return "redirect:/books";
    }

    @PatchMapping("/assign/{book_id}")
    public String assign(@PathVariable("book_id") int book_id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assignBook(book_id, selectedPerson);
        return "redirect:/books/" + book_id;
    }

    @PatchMapping("/release/{book_id}")
    public String release(@PathVariable("book_id") int book_id) {
        bookDAO.release(book_id);
        return "redirect:/books/" + book_id;
    }

    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id) {
        bookDAO.deleteBook(book_id);
        return "redirect:/books";
    }
}
