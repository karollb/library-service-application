package pl.edu.wszib.library.dao;

import pl.edu.wszib.library.model.Loan;

import java.util.List;

public interface ILoanDAO {
    Loan getLoanById(int id);

    List<Loan> getAllLoans();

    boolean addLoan(Loan loan);

    boolean removeLoan(Loan loan);
}
