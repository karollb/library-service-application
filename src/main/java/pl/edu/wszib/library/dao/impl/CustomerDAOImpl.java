package pl.edu.wszib.library.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.model.Customer;


import java.util.List;

@Repository
public class CustomerDAOImpl implements ICustomerDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Customer> getAllCustomers() {
        Session session = this.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.edu.wszib.library.model.Customer");
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }


    @Override
    public Customer getCustomerByPeselNumber(int peselNumber) {
        Session session = this.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.edu.wszib.library.model.Customer WHERE peselNumber = :peselNumber");
        query.setParameter("peselNumber", peselNumber);

        Customer result = null;
        try {
            result = query.getSingleResult();
        } catch (Exception e) {

        }
        session.close();
        return result;
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(customer);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }
}
