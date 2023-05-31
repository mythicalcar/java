package scr;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
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
            document.append("Bestellingen", "");
            bezorgerCol.insertOne(document);
            System.out.println(randomPassword);
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
            System.out.println();
            if (/*BCrypt.checkpw(password, user.get("Password").toString())*/ password.equals(user.get("Password"))) {
                returnVal = 1;
            }
        }
        if (mCursor.hasNext()) {
            Document user = mCursor.next();
            System.out.println();
            if (/*BCrypt.checkpw(password, user.get("Password").toString())*/password.equals(user.get("Password"))) {
                returnVal = 2;
            }
        }
        System.out.println(returnVal);
        return returnVal;
    }

    // public void saveNotes(String notes, String name) {
    // bezorgerCol.updateOne(Filters.eq("Name", name), Updates.set("Notes", notes));
    // }

    public Document getUser(String name) {
        retrievable.put("Name", name);
        bCursor = bezorgerCol.find(retrievable).iterator();
        Document user = bCursor.next();

        return user;
    }

    public ArrayList<Adress> selectAdress() {
        Orders e = new Orders();

        oCursor = bestellingenCol.find(retrievable).iterator();
        while (oCursor.hasNext()) {
            Document bestelling = oCursor.next();
            //System.out.println(bestelling);
            if (bestelling.get("Status").toString().equals("0")) {
                e.addAdress(new Adress(bestelling.get("Plaats").toString(), bestelling.get("Straatnaam").toString(), bestelling.get("Huisnummer").toString(), bestelling.get("Postcode").toString()));
            }
        }
        e.sortAddressesByPostcode();
        return e.getAdresses();
    }

    public void assignOrders(){

    }
    public ArrayList<Bezorger> getBezorgers(){
        ArrayList<Bezorger> bezorgers = new ArrayList<Bezorger>();
        bCursor = bezorgerCol.find(retrievable).iterator();
        while (bCursor.hasNext()) {
            Document user = bCursor.next();
            Bezorger bezorger = new Bezorger(user.get("Name").toString());
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
            System.out.println(randomPassword);
            return true;
        }
    }
    public Object[][] getBezorgerDataTable() {
        // Assuming you want to add 5 bezorgers
        int numBezorgers = getBezorgers().size();
        Object[][] dummyData = new Object[numBezorgers][5];

        for (int i = 0; i < numBezorgers; i++) {
            Document user = bCursor.next();
            String naam = user.get("Name").toString();
            String bestellingen = user.get("Bestellingen").toString();
            int status = (int) user.get("Status");

            dummyData[i][0] = naam;
            dummyData[i][1] = bestellingen;
            dummyData[i][2] = status;
        }

        return dummyData;
    }
}
