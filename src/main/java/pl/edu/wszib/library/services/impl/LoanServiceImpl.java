package pl.edu.wszib.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.dao.ILoanDAO;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.Customer;
import pl.edu.wszib.library.model.Loan;
import pl.edu.wszib.library.services.ILoanService;
import pl.edu.wszib.library.session.SessionObject;


import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired
    ILoanDAO loanDAO;

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    ICustomerDAO customerDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public List<Loan> getAllLoans() {
        return this.loanDAO.getAllLoans();
    }

    @Override
    public Loan getLoanById(int id) {
        return this.loanDAO.getLoanById(id);

    }

    @Override
    public List<Loan> getLoansByCustomerId(int id) {
        return this.loanDAO.getLoansByCustomerId(id);

    }

    @Override
    public List<Customer> getCustomersWithLoans() {
        List<Loan> loans = this.loanDAO.getAllLoans();

        List<Customer> customersWithLoans = new ArrayList<>();

        for(Loan loan : loans) {
            if(!customersWithLoans.contains(loan.getCustomer())) {
                customersWithLoans.add(loan.getCustomer());
            }
        }

        return customersWithLoans;
    }

    @Override
    public boolean addNewLoan(Customer customer, List<Book> books) {
        if (books.isEmpty()) {
            return false;
        }
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        for (Book book : books) {
            Loan loan = new Loan();
            loan.setCustomer(customer);
            loan.setBook(book);
            loan.setDate(date);
            this.loanDAO.addLoan(loan);

            book.setStatus(Book.Status.INACCESSIBLE);
            this.bookDAO.updateBook(book);

        }

        this.sessionObject.clearLoanList();

        return true;

    }

    @Override
    public void addBookByIdToLoanList(int id) {
        Book book = this.bookDAO.getBookById(id);

        for (Book bookFromLoanList : this.sessionObject.getLoanList()) {
            if (bookFromLoanList.getId() == book.getId()) {
                return;

            }
        }
        this.sessionObject.getLoanList().add(book);

    }

    @Override
    public void removeLoan(int id) {
        Book book = this.loanDAO.getLoanById(id).getBook();
        this.loanDAO.removeLoan(this.loanDAO.getLoanById(id));
        book.setStatus(Book.Status.AVAILABLE);
        this.bookDAO.updateBook(book);

    }

}
