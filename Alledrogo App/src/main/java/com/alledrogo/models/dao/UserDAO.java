package com.alledrogo.models.dao;

import com.alledrogo.models.business.User;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sample.HibernateUtil;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserDAO {
    static public String createMD5(String plaintext){
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    static public void createCommonUser(User user){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = "EXEC createUser '" + user.getLogin() + "','" + createMD5(user.getPassword()) + "','" + user.getName() + "','" + user.getSurname()
                    + "','C', NULL, NULL";
            Query query = session.createSQLQuery(q);
            query.list();
        }
        catch(Exception e){

        }
    }

    static public boolean dataMatch(String login, String passwd){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = "SELECT dbo.dataMatch('" + login + "','" + createMD5(passwd) + "')";
            Query query = session.createSQLQuery(q);
            List<Boolean> list = query.list();
            return list.get(0);
        }
        catch(Exception e){

        }
        return false;
    }

    static public User getUserByID(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<User> query = session.createNativeQuery("SELECT * FROM getUserByID(" + id + ")", User.class);
            return query.list().get(0);
        }
    }

    static public void createEmployee(User user, String permissions, int bossID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            MessageDigest mg = MessageDigest.getInstance("MD5");

            String q = "EXEC createUser '" + user.getLogin() + "','" + createMD5(user.getPassword()) + "','" + user.getName() + "','" + user.getSurname()
                    + "','E','" + permissions + "'," + bossID;
            Query query = session.createSQLQuery(q);
            query.list();

        }
        catch(Exception e){

        }
    }

    static public User getUser(String login){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<User> query = session.createNativeQuery("SELECT * FROM getUserByLogin('" + login + "')", User.class);
            List<User> result = query.list();
            return result.get(0);
        }
    }

    static public boolean exists(User user){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = "SELECT dbo.doesExists('" + user.getLogin() + "')";
            Query query = session.createSQLQuery(q);
            List<Boolean> list = query.list();
            return list.get(0);
        }
        catch(Exception e){

        }
        return false;
    }
}
