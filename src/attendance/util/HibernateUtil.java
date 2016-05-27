/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author oBii
 */
public class HibernateUtil {

//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            // Create the SessionFactory from standard (attendance.cfg.xml)
//            // config file.
//            Configuration cfg1 = new AnnotationConfiguration();
//            cfg1.configure("attendance.cfg.xml");
//            sessionFactory = cfg1.buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log the exception.
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
    private static SessionFactory sessionFactory;


    public  void setSessionFactory(SessionFactory sessionFactory2) {
        sessionFactory = sessionFactory2;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
