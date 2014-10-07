/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.repository;

import br.ufjf.pgcc.plscience.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author vitorfs
 * @param <T>
 */
public abstract class RepositoryBase<T> {
    
    protected Session session;
    
    public void save(T obj) {
        try {
            session = HibernateUtil.getSession().getSessionFactory().openSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();   
        } catch (HibernateException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                
            }
        }
    }
    
    public void delete(T obj) {
        try {
            session = HibernateUtil.getSession().getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();   
        } catch (HibernateException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                
            }
        }        
    }
    
    public T find(Class _class, Long id) {
        try {
            session = HibernateUtil.getSession().getSessionFactory().openSession();
            T obj =  (T) session.get(_class, id);
            return obj;
        } catch (HibernateException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                
            }
        }
    }
    
    protected List<T> findAll(Class _class) {
        try {
            session = HibernateUtil.getSession().getSessionFactory().openSession();
            List<T> objs = session.createCriteria(_class).list();
            return objs;
        } catch (HibernateException e) {
            throw e;
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                
            }
        }        
    }
    
}
