package dao;


import java.util.ArrayList;
import java.util.List;

import entity.Grade;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;


/**
 * Data Access Object for Grade Entity. 
 * <p>
 Wrapper Class for CRUD operations on Grade.
 */

public class GradeDao {
    private SessionFactory sessionFactory;

    public void addGrade(Grade grade) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.save(grade);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void deleteGrade(int gradeid) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Grade grade = (Grade) session.load(Grade.class, new Integer(gradeid));
            session.delete(grade);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void updateGrade(Grade grade) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.update(grade);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<Grade>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            grades = session.createQuery("from Grade").list();
//            session.update(grades);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return grades;
    }

    public Grade getGradeById(int gradeid) {
        Grade grade = null;
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Grade where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", gradeid);
            grade = (Grade) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return grade;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}