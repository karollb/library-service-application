package pl.edu.wszib.library.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.dao.IUserDAO;
import pl.edu.wszib.library.model.User;

import javax.persistence.NoResultException;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.library.model.User WHERE login = :login");
        query.setParameter("login", login);

        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {

        }
        session.close();
        return result;
    }

    @Override
    public boolean addNewUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
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
