package pl.edu.wszib.library.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.view.RegistrationModel;
import pl.edu.wszib.library.services.IBookService;
import pl.edu.wszib.library.services.IUserService;

@RestController
public class AdminRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;



    @RequestMapping(value = "/completeTheDatabase", method = RequestMethod.GET)
    public String completeTheDatabase() {
        this.userService.addNewAdmin(new RegistrationModel("admin", "admin", "admin"));
        this.userService.addNewUser(new RegistrationModel("karol", "karol", "karol"));

        this.bookService.addNewBook(newBook("Czysty kod podrecznik dobrego programisty", "Robert C. Martin", "978-83-283-0134-1"));
        this.bookService.addNewBook(newBook("Java. Kompendium programisty", "Herbert Schildt", "978-83-283-5882-9"));
        this.bookService.addNewBook(newBook("Android. Programowanie aplikacji. Rusz glowa!", "Dawn Griffiths, David Griffiths", "978-83-283-4080-0"));
        this.bookService.addNewBook(newBook("Jezyk C++. Szkola programowania.", "Stephen Prata", "978-83-246-4336-3"));
        this.bookService.addNewBook(newBook("JavaScript. Interaktywne aplikacje webowe", "Tomasz Sochacki", "978-83-283-5638-2"));

        return "redirect:/main";
    }

    private Book newBook(String title, String author, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);

        return book;
    }

}
