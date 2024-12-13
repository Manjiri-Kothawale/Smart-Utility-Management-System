package com.manjiri.smart_utility_management;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    // Static block to initialize the SessionFactory
    static {
        try {
            // Load the Hibernate configuration file (hibernate.cfg.xml)
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception to trace if initialization fails
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Method to retrieve the SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Method to shut down the SessionFactory
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
