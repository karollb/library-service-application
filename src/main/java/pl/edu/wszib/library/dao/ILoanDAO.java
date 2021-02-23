package pl.edu.wszib.library.dao;


import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.Loan;

import java.util.List;

public interface ILoanDAO {
    Loan getLoanById(int id);

    List<Loan> getAllLoans();

    List<Loan> getLoansByCustomerId(int id);

    void addLoan(Loan borrow);

    void removeLoan(Loan loan);
}
