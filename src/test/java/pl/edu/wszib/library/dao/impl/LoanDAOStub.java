package pl.edu.wszib.library.dao.impl;

import pl.edu.wszib.library.dao.ILoanDAO;
import pl.edu.wszib.library.model.Loan;

import java.util.List;

public class LoanDAOStub implements ILoanDAO {
    @Override
    public Loan getLoanById(int id) {
        return null;
    }

    @Override
    public List<Loan> getAllLoans() {
        return null;
    }

    @Override
    public List<Loan> getLoansByCustomerId(int id) {
        return null;
    }

    @Override
    public void addLoan(Loan borrow) {

    }

    @Override
    public void removeLoan(Loan loan) {

    }
}
