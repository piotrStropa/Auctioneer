package com.alledrogo.models.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @Column(name = "messageID")
    int messageOD;

    @Column(name = "topicName")
    String topicName;

    @Column(name = "text")
    String text;

    @Column(name = "senderID")
    int senderID;

    @Column(name = "recipentID")
    int recipentID;

    @Column(name = "auctionID")
    int auctionID;

    @Column(name = "msgDate")
    Date msgDate;

    public int getMessageOD() {
        return messageOD;
    }

    public void setMessageOD(int messageOD) {
        this.messageOD = messageOD;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getRecipentID() {
        return recipentID;
    }

    public void setRecipentID(int recipentID) {
        this.recipentID = recipentID;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }
}
