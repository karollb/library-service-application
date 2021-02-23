package pl.edu.wszib.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.dao.ILoanDAO;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.Customer;
import pl.edu.wszib.library.model.Loan;
import pl.edu.wszib.library.services.ILoanService;


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

        return true;

    }


    @Override
    public void removeLoan(int id) {
        Book book = this.loanDAO.getLoanById(id).getBook();
        this.loanDAO.removeLoan(this.loanDAO.getLoanById(id));
        book.setStatus(Book.Status.AVAILABLE);
        this.bookDAO.updateBook(book);

    }
}
