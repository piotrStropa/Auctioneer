package com.alledrogo.models.dao;

import com.alledrogo.models.business.Auction;
import com.alledrogo.models.business.Category;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import sample.HibernateUtil;

import java.util.List;

public class CategoryDAO {

    static public List<Category> getCategories(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM Categories";
            NativeQuery<Category> query = session.createNativeQuery(s, Category.class);
            return query.list();
        }
        catch(Exception e){}
        return null;
    }

}
