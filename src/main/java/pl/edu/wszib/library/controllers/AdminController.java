package pl.edu.wszib.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.User;
import pl.edu.wszib.library.model.view.RegistrationModel;
import pl.edu.wszib.library.services.IBookService;
import pl.edu.wszib.library.services.IUserService;
import pl.edu.wszib.library.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AdminController {

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);

        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegistrationModel registrationModel) {
        Pattern regexp = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher loginMatcher = regexp.matcher(registrationModel.getLogin());
        Matcher passMatcher = regexp.matcher(registrationModel.getPass());
        Matcher pass2Matcher = regexp.matcher(registrationModel.getPass2());

        if (!loginMatcher.matches()
                || !passMatcher.matches() || !pass2Matcher.matches()
                || !registrationModel.getPass().equals(registrationModel.getPass2())) {
            this.sessionObject.setInfo("Validation error !!");
            return "redirect:/register";

        }

        if (this.userService.addNewUser(registrationModel)) {
            return "redirect:/main";
        } else {
            this.sessionObject.setInfo("login zajęty !!");
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/addNewBook", method = RequestMethod.GET)
    public String addNewBookForm(Model model) {
        if (!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        model.addAttribute("book", new Book());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("info", this.sessionObject.getInfo());

        return "addNewBook";
    }

    @RequestMapping(value = "/addNewBook", method = RequestMethod.POST)
    public String addNewBook(@ModelAttribute Book book) {
        if (!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getIsbn().isEmpty()) {
            this.sessionObject.setInfo("Musisz wypełnić wszystkie pola formularza !!!");
            return "redirect:/addNewProduct";
        }

        this.bookService.addNewBook(book);
        return "redirect:/main";

    }


}
