package com.manjiri.smart_utility_management;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.manjiri.smart_utility_management.HibernateUtil;

public class ServiceLogDAO {

    // Save a new ServiceLog to the database
    public void saveServiceLog(ServiceLog serviceLog) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(serviceLog);
            transaction.commit();
            System.out.println("ServiceLog saved successfully: " + serviceLog);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Error saving ServiceLog: " + e.getMessage());
        }
    }

    // Get a list of all ServiceLogs
    public List<ServiceLog> getAllServiceLogs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ServiceLog", ServiceLog.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error retrieving ServiceLogs: " + e.getMessage());
            return null;
        }
    }

    // Get a specific ServiceLog by ID
    public ServiceLog getServiceLogById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ServiceLog.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error retrieving ServiceLog by ID: " + e.getMessage());
            return null;
        }
    }

    // Update an existing ServiceLog
    public void updateServiceLog(ServiceLog serviceLog) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(serviceLog);
            transaction.commit();
            System.out.println("ServiceLog updated successfully: " + serviceLog);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Error updating ServiceLog: " + e.getMessage());
        }
    }

    // Delete a ServiceLog by ID
    public void deleteServiceLog(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ServiceLog serviceLog = session.get(ServiceLog.class, id);
            if (serviceLog != null) {
                session.delete(serviceLog);
                System.out.println("ServiceLog deleted successfully: " + serviceLog);
            } else {
                System.err.println("ServiceLog with ID " + id + " not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Error deleting ServiceLog: " + e.getMessage());
        }
    }
}
