package com.manjiri.smart_utility_management;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;



public class AdminDAO {

    // Method to save Admin (if you want to add admins)
    public void saveAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Method to authenticate Admin
    public boolean authenticateAdmin(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> query = session.createQuery("FROM Admin WHERE username = :username AND password = :password", Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            Admin admin = query.uniqueResult();
            
            return admin != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
