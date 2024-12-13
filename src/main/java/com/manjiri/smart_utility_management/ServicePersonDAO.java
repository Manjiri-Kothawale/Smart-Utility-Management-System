package com.manjiri.smart_utility_management;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.manjiri.smart_utility_management.HibernateUtil;

import java.util.List;

public class ServicePersonDAO {

    // Save a new ServicePerson
    public void saveServicePerson(ServicePerson servicePerson) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(servicePerson);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get a ServicePerson by ID
    public ServicePerson getServicePersonById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ServicePerson.class, id);
        }
    }

    // Get all ServicePersons
    @SuppressWarnings("unchecked")
    public List<ServicePerson> getAllServicePersons() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ServicePerson> query = session.createQuery("from ServicePerson");
            return query.list();
        }
    }

    // Get ServicePersons by Service Type
    @SuppressWarnings("unchecked")
    public List<ServicePerson> getServicePersonsByType(String serviceType) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ServicePerson WHERE serviceType = :serviceType";
            Query<ServicePerson> query = session.createQuery(hql, ServicePerson.class);
            query.setParameter("serviceType", serviceType);
            return query.list();
        }
    }

    // Update a ServicePerson
    public void updateServicePerson(ServicePerson servicePerson) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(servicePerson);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete a ServicePerson by ID
    public void deleteServicePerson(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ServicePerson servicePerson = session.get(ServicePerson.class, id);
            if (servicePerson != null) {
                session.delete(servicePerson);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Authenticate Service Person
    public boolean authenticateServicePerson(String email, String contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ServicePerson WHERE email = :email AND contact = :contact";
            Query<ServicePerson> query = session.createQuery(hql, ServicePerson.class);
            query.setParameter("email", email);
            query.setParameter("contact", contact);
            ServicePerson servicePerson = query.uniqueResult();
            return servicePerson != null;
        }
    }
}
