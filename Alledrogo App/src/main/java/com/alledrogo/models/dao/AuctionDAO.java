package com.alledrogo.models.dao;

import com.alledrogo.models.business.Auction;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sample.HibernateUtil;
import sample.Main;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class AuctionDAO {
    static public List<Auction> getNewest(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM getNewest()";
            NativeQuery<Auction> query = session.createNativeQuery(s, Auction.class);
            return query.list();
        }
        catch(Exception e){}
        return null;
    }



    static public List<Auction> getBySearch(String search){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM auctionMatch('" + search + "')";
            NativeQuery<Auction> query = session.createNativeQuery(s, Auction.class);
            return query.list();
        }
        catch(Exception e){}
        return null;
    }

    static public void cancelAuction(int auctionID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = "EXEC cancelAuction " + auctionID;
            Query query = session.createSQLQuery(q);
            query.list();
        }
        catch(Exception e){

        }
    }


    static public List<Auction> currentAuctions(int userID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM currentAuctions(" + userID + ")";
            NativeQuery<Auction> query = session.createNativeQuery(s, Auction.class);
            return query.list();
        }
        catch(Exception e){}
        return null;
    }

    public static void addPhotos(List<String> photos, int auctionID){
        photos.forEach(img -> {
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                String s = "EXEC addPicture " + auctionID + " '" + img + "'";
                NativeQuery<Auction> query = session.createNativeQuery(s, Auction.class);
                query.list();
            }
            catch(Exception e){}
        });
    }

    static public List<Auction> auctionsByCategory(int categoryID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM auctionByCategory(" + categoryID + ")";
            NativeQuery<Auction> query = session.createNativeQuery(s, Auction.class);
            return query.list();
        }
        catch(Exception e){}
        return null;
    }

    public static void makeBid(int money, int auctionID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = "EXEC makeBid " + money + ", " + Main.control.getMainUser().getUserID() + ", " + auctionID;
            Query query = session.createSQLQuery(q);
            query.list();
        }
        catch(Exception e){

        }
    }

    static public Auction getAuctionByID(int auctionID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM auctions WHERE auctionID = " + auctionID;
            NativeQuery<Auction> query = session.createNativeQuery(s, Auction.class);
            return query.list().get(0);
        }
        catch(Exception e){}
        return null;
    }

    static public void createNewAuction(Auction auction, List<byte[]> pictures){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(auction.getExpirationDate());
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String q = String.format("EXEC createAuctions %d, %d, '%s', '%s', %d, '%s', %d",auction.getCurrentPrice(), Main.control.getMainUser().getUserID(),
                    auction.getName(), auction.getDescription(), auction.getCategoryID(), date, auction.getAddressID());
            Query query = session.createSQLQuery(q);
            query.list();
        }
        catch(Exception e){

        }
    }
}

