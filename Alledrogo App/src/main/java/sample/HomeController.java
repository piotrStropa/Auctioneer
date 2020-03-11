package sample;

import com.alledrogo.models.business.*;
import com.alledrogo.models.dao.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HomeController {
    private Pane[] panes;
    private AuctionPane[] auctionPanes;
    private List<Auction> auctions;
    private List<Bid> bidHistory;
    private List<Category> categories;
    private List<Address> userAddresses;
    private List<Auction> userAuctions;
    private List<Message> userMessages;
    private int currentPage;
    private int maxPages;
    private Message lastMessage;
    private Auction currentAuction;
    @FXML public Pane auct1, auct2, auct3, auct4, auct5, auct6;
    @FXML public Button forward, back, search, create, bid, ask, decline, save, cancel, send, backToMenu, deleteAuction, deleteAddress, back1, createAddress, reply, back2;
    @FXML public Label auctionPrice, description, title, page, welcome, lastBid, contact, topicReceive, auctionReceive, descriptionReceive;
    @FXML public ImageView left, right, picture, messages, settings, addPhoto;
    @FXML public TextField searchText, bidValue, messageTopic, productName, startingPrice, expirationDate, firstLane, secondLane, zipCode, city;
    @FXML public TextArea productDescription, messageContent;
    @FXML public ListView<String> categoryList, addressList, auctionList, messageList;
    @FXML public AnchorPane auctionView, messageView, createView, receivedView, settingsView;
    @FXML public ChoiceBox<String> addressSet, categorySet;




    void assignPanes(){
        panes[0] = auct1;
        panes[1] = auct2;
        panes[2] = auct3;
        panes[3] = auct4;
        panes[4] = auct5;
        panes[5] = auct6;

        for (int i = 0; i < 6; i++) {
            auctionPanes[i] = decompose(panes[i]);
        }
    }

    AuctionPane decompose(Pane auction){
        AuctionPane ap = new AuctionPane();
        ObservableList<Node> list = auction.getChildren();
        for(Node node : list){
            if(node instanceof ImageView){
                ap.picture = (ImageView)node;
            }
            else if(node instanceof Label){
                Label label = (Label)node;
                if(label.getText().equals("PRICE PLN")) ap.price = label;
                else ap.name = label;
            }
        }
        return ap;
    }

    void resultVisibility(boolean visible){
        for (int i = 0; i < 6; i++) {
            panes[i].setVisible(visible);
        }
        forward.setVisible(visible);
        back.setVisible(visible);
        page.setVisible(visible);
    }

    void assignAuction(AuctionPane auctionPane, Auction auction){
        if(auction != null){
            auctionPane.price.setVisible(true);
            auctionPane.name.setVisible(true);
            auctionPane.picture.setVisible(true);
            auctionPane.price.setText(auction.getCurrentPrice() + " zł");
            auctionPane.name.setText(auction.getName());
        }
        else{
            auctionPane.price.setVisible(false);
            auctionPane.name.setVisible(false);
            auctionPane.picture.setVisible(false);
        }
        auctionPane.auction = auction;
    }

    void updateAuctions(){
        maxPages = auctions.size()/6 + 1;
        for(int i = (currentPage-1)*6; i < currentPage * 6; i++){
            if(i < auctions.size()){
                assignAuction(auctionPanes[i%6], auctions.get(i));
            }
            else{
                assignAuction(auctionPanes[i%6], null);
            }
        }
        updatePages();
    }

    void currentAuctionUpdate(){
        description.setText(currentAuction.getDescription());
        title.setText(currentAuction.getName());
        auctionPrice.setText(currentAuction.getCurrentPrice() + " PLN");
        User owner = UserDAO.getUserByID(currentAuction.getUserID());
        bidHistory = BidDAO.getBidHistory(currentAuction.getId());
        if(bidHistory != null && bidHistory.size() != 0){
            User winner = UserDAO.getUserByID(bidHistory.get(0).getUserID());
            if(winner.getLogin().equals(Main.control.getMainUser().getLogin())){
                lastBid.setText("Ostatni przebijający: TY");
            }
            else{
                lastBid.setText("Ostatni przebijający: " + winner.getName() + " " + winner.getSurname().charAt(0) + ".");
            }
        }
        else{
            lastBid.setText("Brak historii licytacji");
        }
        List<Address> addresses = AddressDAO.getAddresses(owner.getUserID());
        final Address[] address = new Address[1];
        addresses.forEach((e)->{
            if(e.getAddressID() == currentAuction.getAddressID()){
                address[0] = e;
            }
        });
        contact.setText("Kontakt:\n" + owner.getName() + " " + owner.getSurname().charAt(0) + ".\n" + address[0].getAddressFirstLane() + "\n" + address[0].getCity());
    }

    void clearViews(){
        resultVisibility(false);
        auctionView.setVisible(false);
        messageView.setVisible(false);
        receivedView.setVisible(false);
        settingsView.setVisible(false);
        createView.setVisible(false);
        searchText.clear();
        bidValue.clear();
        messageTopic.clear();
        productName.clear();
        startingPrice.clear();
        expirationDate.clear();
        productDescription.clear();
        messageContent.clear();
    }


    void updatePages(){
        page.setText("Strona " + currentPage + " z " + maxPages);
    }

    void setPanesListener(){
        for(int i = 0; i < 6; i++){
            int a = i;
            panes[a].setOnMouseClicked(e -> {
                if(auctionPanes[a].auction != null){
                    currentAuction = auctionPanes[a].auction;
                    lastMessage = new Message();
                    lastMessage.setSenderID(currentAuction.getUserID());
                    clearViews();
                    currentAuctionUpdate();
                    auctionView.setVisible(true);
                }
            });
        }
    }

    void updateAddresses(){
        userAddresses = AddressDAO.getAddresses(Main.control.getMainUser().getUserID());
        addressSet.getItems().clear();
        for (int i = 0; i < userAddresses.size(); i++) {
            Address ad = userAddresses.get(i);
            addressSet.getItems().add(ad.getAddressFirstLane() + " " + ad.getCity());
        }
        addressList.getItems().clear();
        userAddresses.forEach(address -> {
            addressList.getItems().add(address.getAddressFirstLane() + " " + address.getAddressSecondLane() +
                    " " + address.getCity());
        });
    }

    void updateAuctionsToRemove(){
        userAuctions = AuctionDAO.currentAuctions(Main.control.getMainUser().getUserID());
        if(userAuctions == null) return;
        auctionList.getItems().clear();
        userAuctions.forEach(a -> {
            auctionList.getItems().add(a.getName());
        });
    }

    @FXML
    void initialize(){
        panes = new Pane[6];
        auctionPanes = new AuctionPane[6];
        welcome.setText("Witaj, "+ Main.control.getMainUser().getName() + " " + Main.control.getMainUser().getSurname() + "!");
        assignPanes();
        auctions = AuctionDAO.getNewest();
        maxPages = 1;
        currentPage = 1;
        updateAuctions();
        clearViews();
        resultVisibility(true);
        setPanesListener();
        updateAddresses();
        updateAuctionsToRemove();
        categories = CategoryDAO.getCategories();
        if(categories != null){
            categories.forEach(e -> {
                categoryList.getItems().add(e.getCategoryName());
                categorySet.getItems().add(e.getCategoryName());
            });
        }


        backToMenu.setOnMouseClicked(e -> {
            clearViews();
            resultVisibility(true);
        });

        search.setOnMouseClicked((e) -> {
            auctions = AuctionDAO.getBySearch(searchText.getText());
            currentPage = 1;
            updateAuctions();
            clearViews();
            resultVisibility(true);
        });

        forward.setOnMouseClicked((e) -> {
            if(currentPage < maxPages){
                currentPage++;
                updateAuctions();
            }
        });


        back.setOnMouseClicked((e) -> {
            if(currentPage > 1){
                currentPage--;
                updateAuctions();
            }
        });

        create.setOnMouseClicked((e) -> {
            clearViews();
            createView.setVisible(true);
        });

        decline.setOnMouseClicked(e -> {
            clearViews();
            resultVisibility(true);
        });





        save.setOnMouseClicked(e -> {
            Auction auction = new Auction();
            auction.setUserID(Main.control.getMainUser().getUserID());
            auction.setName(productName.getText());
            auction.setDescription(productDescription.getText());
            try{
                int price = Integer.parseInt(startingPrice.getText());
                auction.setCurrentPrice(price);
                if(auction.getCurrentPrice() < 0) throw new NumberFormatException();
                auction.setExpirationDate(new SimpleDateFormat("dd/MM/yyyy").parse(expirationDate.getText()));
            } catch(NumberFormatException ex){
                startingPrice.setText("TYLKO LICZBY");
                return;
            } catch (ParseException ex) {
                expirationDate.setText("ZLY FORMAT DATY");
                return;
            }
            if(addressSet.getValue() == null) return;
            if(categorySet.getValue() == null) return;
            int addNum = addressSet.getSelectionModel().getSelectedIndex();
            auction.setAddressID(userAddresses.get(addNum).getAddressID());
            for(Category c:categories){
                if(c.getCategoryName().equals(categorySet.getValue())){
                    auction.setCategoryID(c.getCategoryID());
                }
            }
            AuctionDAO.createNewAuction(auction, null);
            clearViews();
            resultVisibility(true);
            updateAuctionsToRemove();
            updateAuctions();
        });

        ask.setOnMouseClicked(e -> {
            clearViews();
            messageTopic.setText("Pytanie o: " + currentAuction.getName());
            messageContent.clear();
            messageView.setVisible(true);
        });

        bid.setOnMouseClicked(e -> {
            int bidvalue = -1;
            try{
                bidvalue = Integer.parseInt(bidValue.getText());
            }
            catch(Exception ex){ }
            if(bidvalue == -1) return;
           AuctionDAO.makeBid(bidvalue, currentAuction.getId());
           currentAuction = AuctionDAO.getBySearch(currentAuction.getName()).get(0);
           currentAuctionUpdate();
        });

        cancel.setOnMouseClicked(e -> {
            clearViews();
            auctionView.setVisible(true);
        });

        send.setOnMouseClicked(e -> {
            Message message = new Message();
            message.setAuctionID(currentAuction.getId());
            message.setSenderID(Main.control.getMainUser().getUserID());
            message.setRecipentID(lastMessage.getSenderID());
            message.setTopicName(messageTopic.getText());
            message.setText(messageContent.getText());
            MessageDAO.send(message);
            clearViews();
            resultVisibility(true);
        });

        settings.setOnMouseClicked(e -> {
            clearViews();
            settingsView.setVisible(true);
        });

        deleteAuction.setOnMouseClicked(e -> {
            int index = auctionList.getSelectionModel().getSelectedIndex();
            if(index == -1) return;
            if(userAuctions == null) return;
            int auctionID = userAuctions.get(index).getId();
            AuctionDAO.cancelAuction(auctionID);
            updateAuctionsToRemove();
            auctions = AuctionDAO.getBySearch("");
            currentPage = 1;
            updateAuctions();
            clearViews();
            settingsView.setVisible(true);
        });

        deleteAddress.setOnMouseClicked(e -> {
            int address = addressList.getSelectionModel().getSelectedIndex();
            int addressID = userAddresses.get(address).getAddressID();
            AddressDAO.deleteAddress(addressID);
            clearViews();
            updateAddresses();
            resultVisibility(true);
        });

        messages.setOnMouseClicked(e -> {
            clearViews();
            userMessages = MessageDAO.getMessages(Main.control.getMainUser().getUserID());
            messageList.getItems().clear();
            if(userMessages == null) return;
            userMessages.forEach(m -> {
                messageList.getItems().add(m.getTopicName() );
            });
            receivedView.setVisible(true);
        });

        back1.setOnMouseClicked(e -> {
            clearViews();
            resultVisibility(true);
        });

        back2.setOnMouseClicked(e -> {
            clearViews();
            resultVisibility(true);
        });

        createAddress.setOnMouseClicked(e -> {
            Address address = new Address();
            address.setAddressFirstLane(firstLane.getText());
            address.setAddressSecondLane(secondLane.getText());
            if(zipCode.getText().length() > 5 || zipCode.getText().length() == 0 || firstLane.getText().length()==0 || city.getText().length() == 0) {
                return;
            }
            address.setZipCode(zipCode.getText());
            address.setCity(city.getText());
            address.setUserID(Main.control.getMainUser().getUserID());
            AddressDAO.createAddress(address);
            updateAddresses();
            clearViews();
            resultVisibility(true);
        });

        messageList.setOnMouseClicked(e ->{
            int index = messageList.getSelectionModel().getSelectedIndex();
            if(userMessages == null || userMessages.size() == 0 || index == -1) return;
            Message message = userMessages.get(index);
            topicReceive.setText(message.getTopicName());
            descriptionReceive.setText(message.getText());
            currentAuction = AuctionDAO.getAuctionByID(message.getAuctionID());
            auctionReceive.setText(currentAuction.getName());
            lastMessage = message;
        });

        reply.setOnMouseClicked(e -> {
            clearViews();
            messageTopic.setText("RE: " + lastMessage.getTopicName());
            messageView.setVisible(true);
        });

        categoryList.setOnMouseClicked(e -> {
            int index = categoryList.getSelectionModel().getSelectedIndex();
            Category category = categories.get(index);
            auctions = AuctionDAO.auctionsByCategory(category.getCategoryID());
            updateAuctions();
            clearViews();
            resultVisibility(true);
        });
    }
}
