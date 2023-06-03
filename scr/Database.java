package scr;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;

import java.util.Iterator;

public class Database {
    MongoClient client = MongoClients.create(
            "mongodb+srv://henk:wRobRl3fQE4O1DZl@kbs.teqpcjy.mongodb.net/?retryWrites=true&w=majority");
    MongoDatabase db = client.getDatabase("Users");
    MongoCollection<Document> bezorgerCol = db.getCollection("bezorgers");
    MongoCollection<Document> managerCol = db.getCollection("managers");
    MongoCollection<Document> bestellingenCol = db.getCollection("Bestellingen");
    BasicDBObject retrievable = new BasicDBObject();
    FindIterable<Document> iterDoc;
    Iterator<Document> it;
    MongoCursor<Document> bCursor;
    MongoCursor<Document> mCursor;
    MongoCursor<Document> oCursor;

    Database() {
        // eh
    }

    public Boolean createAccount(String name, String password) {
        retrievable.put("Name", name);
        bCursor = bezorgerCol.find(retrievable).iterator();

        if (bCursor.hasNext()) {
            return false;
        } else {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
            String randomPassword = RandomStringUtils.random(15, characters);
            String hashedPass = BCrypt.hashpw(randomPassword, BCrypt.gensalt(10));
            Document document = new Document();
            document.append("Name", name);
            document.append("Password", hashedPass);
            document.append("Bestellingen", new ArrayList<Bestelling>());
            bezorgerCol.insertOne(document);
            //System.out.println(randomPassword);
            return true;
        }
    }

    public int checkUserData(String name, String password) {
        System.out.println(password);
        int returnVal = 0;
        retrievable.put("Name", name);
        bCursor = bezorgerCol.find(retrievable).iterator();
        mCursor = managerCol.find(retrievable).iterator();
        if (bCursor.hasNext()) {
            Document user = bCursor.next();
            //System.out.println();
            if (/*BCrypt.checkpw(password, user.get("Password").toString())*/ password.equals(user.get("Password"))) {
                returnVal = 1;
            }
        }
        if (mCursor.hasNext()) {
            Document user = mCursor.next();
            //System.out.println();
            if (/*BCrypt.checkpw(password, user.get("Password").toString())*/password.equals(user.get("Password"))) {
                returnVal = 2;
            }
        }
        //System.out.println(returnVal);
        return returnVal;
    }

    // public void saveNotes(String notes, String name) {
    // bezorgerCol.updateOne(Filters.eq("Name", name), Updates.set("Notes", notes));
    // }

    public Document getBezorger(String name) {
        retrievable.put("Name", name);
        bCursor = bezorgerCol.find(retrievable).iterator();
        Document user = bCursor.next();

        return user;
    }

    public ArrayList<Adress> selectAdress() {
        Orders orders = new Orders();

        oCursor = bestellingenCol.find(retrievable).iterator();
        while (oCursor.hasNext()) {
            Document bestelling = oCursor.next();
            //System.out.println(bestelling);
            if (bestelling.get("Status").toString().equals("0")) {
                orders.addAdress(new Adress(bestelling.get("Plaats").toString(), bestelling.get("Straatnaam").toString(), bestelling.get("Huisnummer").toString(), bestelling.get("Postcode").toString()));
            }
        }
        orders.sortAddressesByPostcode();
        return orders.getAdresses();
    }

    public void assignOrders(int bezorgerId){

    }
    public ArrayList<Bezorger> getBezorgers(){
        ArrayList<Bezorger> bezorgers = new ArrayList<Bezorger>();
        bCursor = bezorgerCol.find(retrievable).iterator();
        while (bCursor.hasNext()) {
            Document user = bCursor.next();
            Bezorger bezorger = new Bezorger(user.get("_id").toString(), user.get("Name").toString(), user.get("Email").toString(), (int) user.get("Status"));
            bezorgers.add(bezorger);
        }
        //System.out.println(bezorgers.get(0).get("Name"));
        return bezorgers;
    }
    public Boolean createManagerAccount(String name, String password) {
        retrievable.put("Name", name);
        mCursor = managerCol.find(retrievable).iterator();

        if (mCursor.hasNext()) {
            return false;
        } else {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
            String randomPassword = RandomStringUtils.random(15, characters);
            String hashedPass = BCrypt.hashpw(randomPassword, BCrypt.gensalt(10));
            Document document = new Document();
            document.append("Name", name);
            document.append("Password", hashedPass);
            managerCol.insertOne(document);
            //System.out.println(randomPassword);
            return true;
        }
    }
    public Object[][] getBezorgerDataTable() {
        int numBezorgers = getBezorgers().size();
        Object[][] bezorgerData = new Object[numBezorgers][5];
        bCursor = bezorgerCol.find(retrievable).iterator();
        for (int i = 0; i < numBezorgers; i++) {
            Document user = bCursor.next();

            String naam = user.get("Name").toString();
            String email = user.get("Email").toString();
            int status = (int) user.get("Status");

            bezorgerData[i][0] = naam;
            bezorgerData[i][1] = email;
            if(status==0){
                bezorgerData[i][2] = "Beschikbaar";
            }else if(status==1){
                bezorgerData[i][2] = "Niet beschikbaar";
            }
        }
        return bezorgerData;
    }

    //getbestellingenatbezorger?
    public ArrayList<Bestelling> getBestellingen(){
        ArrayList<Bestelling> bestellingen = new ArrayList<Bestelling>();
        oCursor = bestellingenCol.find(retrievable).iterator();
        while (oCursor.hasNext()) {
            Document bestelling = oCursor.next();
            //System.out.println(bestelling);
//            String plaats = bestelling.get("Plaats").toString();
//            String straatnaam = bestelling.get("Straatnaam").toString();
//            String huisnummer = bestelling.get("Huisnummer").toString();
//            String postcode = bestelling.get("Postcode").toString();
//            int status = (int) bestelling.get("Status");
            bestellingen.add(new Bestelling(new Adress(bestelling.get("Plaats").toString(), bestelling.get("Straatnaam").toString(), bestelling.get("Huisnummer").toString(), bestelling.get("Postcode").toString()), (int) bestelling.get("Status")));
        }
        return bestellingen;
    }
    public void getBestellingenFromBezorger(String bezorgerId){
        ArrayList<Bestelling> bestellingen = new ArrayList<Bestelling>();
//        retrievable.put("_id", new ObjectId(bezorgerId));
//        System.out.println(new ObjectId(bezorgerId) + bezorgerId);
        BasicDBObject retrievable = new BasicDBObject();
        retrievable.put("_id", new ObjectId(bezorgerId));
        bCursor = bezorgerCol.find(retrievable).iterator();//bezorgerCol.find(retrievable);
        while(bCursor.hasNext()){
            System.out.println("balls");
            Document bezorger = bCursor.next();
            //System.out.println(bezorger.get("_id" + " " + bezorgerId));
            if(bezorger.get("_id").toString().equals(bezorgerId)){
                System.out.println(bezorger.get("Bestellingen"));
            }
        }

        //bezorger.get("Bestellingen");
        //bestellingen = bezorger.get("Bestellingen");
        //return bestellingen;
    }

    //gebruik bezorger id ipv naam?
    public Object[][] getBestellingenDataTableManager(String bezorgerId){
        int bestellingenSize = getBestellingen().size();
        Object[][] bestellingenData = new Object[bestellingenSize][5];
//        BasicDBObject retrievable = new BasicDBObject();
//        retrievable.put("")
        oCursor = bestellingenCol.find(retrievable).iterator();
        for (int i = 0; i < bestellingenSize; i++) {
            Document bestelling = oCursor.next();

            String plaats = bestelling.get("Plaats").toString();
            String straatnaam = bestelling.get("Straatnaam").toString();
            String huisnummer = bestelling.get("Huisnummer").toString();
            String postcode = bestelling.get("Postcode").toString();
            int status = (int) bestelling.get("Status");

            bestellingenData[i][0] = plaats;
            bestellingenData[i][1] = straatnaam;
            bestellingenData[i][2] = huisnummer;
            bestellingenData[i][3] = postcode;
        }
        return bestellingenData;
    }
    //le method for updating bezorger status
}
