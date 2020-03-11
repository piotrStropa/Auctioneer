package com.alledrogo.models.dao;

import com.alledrogo.models.business.Category;
import com.alledrogo.models.business.Message;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import sample.HibernateUtil;

import java.util.List;

public class MessageDAO {

    public static void send(Message message){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = String.format("EXEC sendMessage '%s', '%s', %d, %d, %d", message.getTopicName(), message.getText(), message.getSenderID(),
                        message.getRecipentID(), message.getAuctionID());
            NativeQuery<Category> query = session.createNativeQuery(s, Category.class);
            query.list();
        }
        catch(Exception e){}
    }

    public static List<Message> getMessages(int userID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM newestMessages(" + userID + ")";
            NativeQuery<Message> query = session.createNativeQuery(s, Message.class);
            return query.list();
        }
        catch(Exception e){}
        return null;
    }
}
