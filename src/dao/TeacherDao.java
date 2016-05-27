package dao;


import java.util.ArrayList;
import java.util.List;

import entity.Teacher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Data Access Object for Teacher Entity. 
 * <p>
 Wrapper Class for CRUD operations on Teacher.
 */
public class TeacherDao {
    private SessionFactory sessionFactory;

    public void addTeacher(Teacher teacher) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.save(teacher);
            trns.commit();
//            session.getTransaction().commit();
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

    public void deleteTeacher(int teacherid) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Teacher teacher = (Teacher) session.load(Teacher.class, new Integer(teacherid));
            session.delete(teacher);
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

    public void updateTeacher(Teacher teacher) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.update(teacher);
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

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<Teacher>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            teachers = session.createQuery("from Teacher").list();
//            session.update(teachers);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return teachers;
    }

    public Teacher getTeacherById(int teacherid) {
        Teacher teacher = null;
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Teacher where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", teacherid);
            teacher = (Teacher) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return teacher;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}