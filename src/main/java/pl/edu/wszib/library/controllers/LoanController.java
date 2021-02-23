package pl.edu.wszib.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.services.IBookService;
import pl.edu.wszib.library.services.ICustomerService;
import pl.edu.wszib.library.services.ILoanService;
import pl.edu.wszib.library.session.SessionObject;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/loans/{id}", method = RequestMethod.GET)
    public String loans(@PathVariable int id, Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("customer", this.customerService.getCustomerById(id));
        model.addAttribute("customerLoans", this.loanService.getLoansByCustomerId(id));
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "loans";
    }

    @RequestMapping(value = "/returnBook/{id}", method = RequestMethod.GET)
    public String returnBook(@PathVariable int id) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        int customerId = this.loanService.getLoanById(id).getCustomer().getId();
        this.loanService.removeLoan(id);



        return "redirect:/loans/"+customerId;
    }


}
