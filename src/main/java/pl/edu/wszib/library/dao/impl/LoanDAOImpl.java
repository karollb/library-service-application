package pl.edu.wszib.library.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.dao.ILoanDAO;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.Loan;


import java.util.List;

@Repository
public class LoanDAOImpl implements ILoanDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Loan getLoanById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery("FROM pl.edu.wszib.library.model.Loan WHERE id = :id");
        query.setParameter("id", id);

        Loan loan = null;
        try {
            loan = query.getSingleResult();
        } catch (Exception e) {

        }
        session.close();
        return loan;
    }

    @Override
    public List<Loan> getAllLoans() {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery("FROM pl.edu.wszib.library.model.Loan");
        List<Loan> loans = query.getResultList();
        session.close();
        return loans;
    }

    @Override
    public List<Loan> getLoansByCustomerId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Loan> query = session.createQuery("FROM pl.edu.wszib.library.model.Loan WHERE customer.id = :id");
        query.setParameter("id", id);
        List<Loan> customerLoans = query.getResultList();
        session.close();
        return customerLoans;

    }


    @Override
    public void addLoan(Loan loan) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(loan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }


    @Override
    public void removeLoan(Loan loan) {
        Session session = this.sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(loan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
