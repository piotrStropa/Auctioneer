package com.alledrogo.models.dao;

import com.alledrogo.models.business.Auction;
import com.alledrogo.models.business.Bid;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import sample.HibernateUtil;

import java.util.List;

public class BidDAO {
    static public List<Bid> getBidHistory(int auctionID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String s = "SELECT * FROM getPriceHistory (" + auctionID + ")";
            NativeQuery<Bid> query = session.createNativeQuery(s, Bid.class);
            return query.list();
        }
        catch(Exception e){}
        return null;
    }
}
