package dao;


import java.util.ArrayList;
import java.util.List;

import entity.Content;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Data Access Object for Content Entity. 
 * <p>
 Wrapper Class for CRUD operations on Content.
 */

public class ContentDao {
    private SessionFactory sessionFactory;

    public void addContent(Content content) {
        Transaction trns = null;
        Session session =sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.save(content);
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

    public void deleteContent(int contentid) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Content content = (Content) session.load(Content.class, new Integer(contentid));
            session.delete(content);
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

    public void updateContent(Content content) {
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.update(content);
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

    public List<Content> getAllContents() {
        List<Content> contents = new ArrayList<Content>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();

            contents = session.createQuery("from Content").list();
//            session.update(contents);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return contents;
    }

    public Content getContentById(int contentid) {
        Content content = null;
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Content where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", contentid);
            content = (Content) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return content;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}