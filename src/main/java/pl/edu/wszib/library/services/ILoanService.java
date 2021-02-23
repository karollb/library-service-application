package pl.edu.wszib.library.services;

import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.Customer;
import pl.edu.wszib.library.model.Loan;

import java.util.List;

public interface ILoanService {
    List<Loan> getAllLoans();

    List<Loan> getLoansByCustomerId(int id);

    Loan getLoanById(int id);

    boolean addNewLoan(Customer customer, List<Book> books);

    void addBookByIdToLoanList(int id);

    void removeLoan(int id);
}
