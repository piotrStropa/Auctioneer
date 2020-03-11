package com.alledrogo.models.dao;

import com.alledrogo.models.business.Address;
import com.alledrogo.models.business.Auction;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sample.HibernateUtil;
import sample.Main;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class AddressDAO {
    static public List<Address> getAddresses(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM getAddresses(" + id + ")";
            NativeQuery<Address> query = session.createNativeQuery(s, Address.class);
            return query.list();
        }
        catch(Exception e){}
        return new LinkedList<>();
    }

    public static void createAddress(Address address){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = String.format("EXEC addAddress %d, '%s', '%s', '%s', '%s'",Main.control.getMainUser().getUserID(), address.getAddressFirstLane(),
                        address.getAddressSecondLane(), address.getZipCode(), address.getCity());
            Query query = session.createSQLQuery(q);
            query.list();
        }
        catch(Exception e){

        }
    }

    public static void deleteAddress(int addressID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = "DELETE FROM AddressBook WHERE addressID = " + addressID;
            Query query = session.createSQLQuery(q);
            query.list();
        }
        catch(Exception e){

        }
    }

}
