package com.manjiri.smart_utility_management;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.manjiri.smart_utility_management.HibernateUtil;

public class UserDAO {

    // Save a new User to the database
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get a list of all Users
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get a specific User by ID
    public User getUserById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing User
    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete a User by ID
    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("User is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Check if a user exists by email
    public boolean userExists(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select count(*) from User where email = :email";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("email", email);
            long count = query.uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate a user based on email and contact number
    public boolean authenticateUser(String email, String contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select count(*) from User where email = :email and contact = :contact";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("email", email);
            query.setParameter("contact", contact);
            long count = query.uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
