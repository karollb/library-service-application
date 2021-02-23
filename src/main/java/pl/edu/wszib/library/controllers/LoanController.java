package pl.edu.wszib.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.services.IBookService;
import pl.edu.wszib.library.services.ICustomerService;
import pl.edu.wszib.library.services.ILoanService;
import pl.edu.wszib.library.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LoanController {

    @Autowired
    ILoanService loanService;

    @Autowired
    IBookService bookService;

    @Autowired
    ICustomerService customerService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/customerLoans/{id}", method = RequestMethod.GET)
    public String loans(@PathVariable int id, Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("customer", this.customerService.getCustomerById(id));
        model.addAttribute("customerLoans", this.loanService.getLoansByCustomerId(id));
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "customerLoans";
    }

    @RequestMapping(value = "/returnBook/{id}", method = RequestMethod.GET)
    public String returnBook(@PathVariable int id) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        int customerId = this.loanService.getLoanById(id).getCustomer().getId();
        this.loanService.removeLoan(id);


        return "redirect:/customerLoans/" + customerId;
    }

    @RequestMapping(value = "/addToLoanList/{id}", method = RequestMethod.GET)
    public String addToLoanList(@PathVariable int id) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:login";
        }

        this.loanService.addBookByIdToLoanList(id);

        return "redirect:/main";
    }

    @RequestMapping(value = "/loanList", method = RequestMethod.GET)
    public String loanList(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("books", this.sessionObject.getLoanList());

        return "loanList";
    }

    @RequestMapping(value = "/chooseCustomer", method = RequestMethod.GET)
    public String chooseCustomer(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("customers", this.customerService.getAllCustomers());

        return "chooseCustomer";

    }

    @RequestMapping(value = "/addLoan/{id}", method = RequestMethod.GET)
    public String addLoan(@PathVariable int id) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        List<Book> books = this.sessionObject.getLoanList();

        this.loanService.addNewLoan(this.customerService.getCustomerById(id), books);


        return "redirect:/main";
    }
}
