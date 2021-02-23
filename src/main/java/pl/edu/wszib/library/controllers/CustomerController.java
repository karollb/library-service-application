package pl.edu.wszib.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.model.Customer;
import pl.edu.wszib.library.services.ICustomerService;
import pl.edu.wszib.library.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;


    @Resource
    SessionObject sessionObject;



    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customer(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("customers", this.customerService.getAllCustomers());
        model.addAttribute("isLogged", this.sessionObject.isLogged());


        return "customers";

    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String addCustomerForm(Model model) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("customer", new Customer());
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/addCustomer";
    }

    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute Customer customer) {
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        Pattern regexp = Pattern.compile("[0-9]{11}");
        Matcher peselMatcher = regexp.matcher(customer.getPeselNumber());

        if (!peselMatcher.matches()) {
            this.sessionObject.setInfo("Zły format numeru PESEL");
            return "redirect:/addCustomer";
        }

        if (customer.getFirstName().isEmpty() || customer.getSurname().isEmpty()) {
            this.sessionObject.setInfo("Musisz wypełnić wszystkie pola formularza !!!");
            return "redirect:/addCustomer";
        }


        if (!this.customerService.addNewCustomer(customer)) {
            this.sessionObject.setInfo("Podany numer PESEL jest już w bazie danych");
            return "redirect:/addCustomer";
        }

        return "redirect:/customers";

    }
}
