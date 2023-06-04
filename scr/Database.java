package scr;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import java.lang.reflect.Array;
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
    Gson gson = new Gson();

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

    public Document getBezorgerId(String id) {
        BasicDBObject retrievable = new BasicDBObject();
        //retrievable.put("_id", new ObjectId(id));
        bCursor = bezorgerCol.find(retrievable).iterator();
        Document bezorger = new Document();
        while(bCursor.hasNext()){
            bezorger = bCursor.next();
            if (bezorger.get("_id").toString().equals(id)) {
                return bezorger;
            }
        }
        //Document user = bCursor.next();

        return bezorger;
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
    public void assignOrders(String bezorgerId){
        ArrayList<Bestelling> bestellingen = getAvailableBestellingen();
        //create getBestellingStatus method? it may be inefficient to sort orders you're not going to be assigning anyways
        //I think I just noticed that the orders are sorted by postcode after sorting by distance - shouldn't it be the other way round?
        bestellingen = Orders.sortBestellingenByPostcode(bestellingen);
        int OrdersPerBezorger = 1;
        //make sure the bestelling has status 0
        Object[] bestellingArray = new Object[OrdersPerBezorger];
        //bestellingArray.add(1, b);
        if(bestellingen.isEmpty() == false) {
            for (int i = 0; i < OrdersPerBezorger; i++) {
                //System.out.println(bezorgerId);
//                Document bezorger = getBezorgerId(bezorgerId);
//                bestellingArray = bezorger.get("Bestellingen");
//                BasicDBObject retrievable = new BasicDBObject();
//                bestellingArray.add(bestellingArray)
                bestellingArray[i] = bestellingen.get(i).id;
                bestellingenCol.updateOne(Filters.eq("_id", new ObjectId(bestellingen.get(i).id)), Updates.set("Status", 1));
//                bestellingArray.add(new BsonValue(bestellingen.get(i).id));
            }

            String bestellingArrayJson = gson.toJson(bestellingArray);
            bezorgerCol.updateOne(Filters.eq("_id", new ObjectId(bezorgerId)), Updates.set("Bestellingen", BsonArray.parse(bestellingArrayJson)));
            bezorgerCol.updateOne(Filters.eq("_id", new ObjectId(bezorgerId)), Updates.set("Status", 1));
//            bezorgerCol.updateOne(Filters.eq("_id", new ObjectId(bezorgerId)), Updates.set("Bestellingen", BsonArray.parse(bestellingArrayJson)));
        }
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
        BasicDBObject retrievable = new BasicDBObject();
        oCursor = bestellingenCol.find(retrievable).iterator();
        while (oCursor.hasNext()) {
            Document bestelling = oCursor.next();
            //System.out.println(bestelling);
//            String plaats = bestelling.get("Plaats").toString();
//            String straatnaam = bestelling.get("Straatnaam").toString();
//            String huisnummer = bestelling.get("Huisnummer").toString();
//            String postcode = bestelling.get("Postcode").toString();
//            int status = (int) bestelling.get("Status");
            bestellingen.add(new Bestelling(bestelling.get("_id").toString(), new Adress(bestelling.get("Plaats").toString(), bestelling.get("Straatnaam").toString(), bestelling.get("Huisnummer").toString(), bestelling.get("Postcode").toString()), (int) bestelling.get("Status")));
        }
        return bestellingen;
    }

    public Bestelling getBestellingById(String bestellingId){
        Bestelling bestellingToReturn = new Bestelling("-1", new Adress("", "", "", ""), -1);
        BasicDBObject retrievable = new BasicDBObject();
        oCursor = bestellingenCol.find(retrievable).iterator();
        while (oCursor.hasNext()) {
            Document bestelling = oCursor.next();
            if(bestelling.get("_id").toString().equals(bestellingId)){
                bestellingToReturn = new Bestelling(bestelling.get("_id").toString(), new Adress(bestelling.get("Plaats").toString(), bestelling.get("Straatnaam").toString(), bestelling.get("Huisnummer").toString(), bestelling.get("Postcode").toString()), (int) bestelling.get("Status"));
            }
            //System.out.println(bestelling);
//            String plaats = bestelling.get("Plaats").toString();
//            String straatnaam = bestelling.get("Straatnaam").toString();
//            String huisnummer = bestelling.get("Huisnummer").toString();
//            String postcode = bestelling.get("Postcode").toString();
//            int status = (int) bestelling.get("Status");
//            bestellingen.add(new Bestelling(bestelling.get("_id").toString(), new Adress(bestelling.get("Plaats").toString(), bestelling.get("Straatnaam").toString(), bestelling.get("Huisnummer").toString(), bestelling.get("Postcode").toString()), (int) bestelling.get("Status")));
        }
        return bestellingToReturn;
    }

    public ArrayList<Bestelling> getAvailableBestellingen(){
        ArrayList<Bestelling> bestellingen = new ArrayList<Bestelling>();
        BasicDBObject retrievable = new BasicDBObject();
        oCursor = bestellingenCol.find(retrievable).iterator();
        while (oCursor.hasNext()) {
            Document bestelling = oCursor.next();
            //System.out.println(bestelling);
//            String plaats = bestelling.get("Plaats").toString();
//            String straatnaam = bestelling.get("Straatnaam").toString();
//            String huisnummer = bestelling.get("Huisnummer").toString();
//            String postcode = bestelling.get("Postcode").toString();
//            int status = (int) bestelling.get("Status");
            if((int) bestelling.get("Status") == 0){
                bestellingen.add(new Bestelling(bestelling.get("_id").toString(), new Adress(bestelling.get("Plaats").toString(), bestelling.get("Straatnaam").toString(), bestelling.get("Huisnummer").toString(), bestelling.get("Postcode").toString()), (int) bestelling.get("Status")));
            }
        }
        return bestellingen;
    }

    public ArrayList<Bestelling> getBestellingenFromBezorger(String bezorgerId){
        ArrayList<Bestelling> bestellingen = new ArrayList<>();
//        retrievable.put("_id", new ObjectId(bezorgerId));
//        System.out.println(new ObjectId(bezorgerId) + bezorgerId);
        BasicDBObject retrievable = new BasicDBObject();
        //retrievable.put("_id", new ObjectId(bezorgerId));
        bCursor = bezorgerCol.find(retrievable).iterator();//bezorgerCol.find(retrievable);
        while(bCursor.hasNext()){
            Document bezorger = bCursor.next();
            //System.out.println(bezorger.get("_id" + " " + bezorgerId));
            if(bezorger.get("_id").toString().equals(bezorgerId)){
                //getdeclared fields is not the answer here
                Object[] bestellingenArray = bezorger.get("Bestellingen").getClass().getDeclaredFields();
                for (Object bestelling:bestellingenArray) {
                    System.out.println(bestelling.toString());
                    bestellingen.add(getBestellingById(bestelling.toString()));
                }
            }
        }
        return bestellingen;

        //bezorger.get("Bestellingen");
        //bestellingen = bezorger.get("Bestellingen");
        //return bestellingen;
    }

    //gebruik bezorger id ipv naam?
    public Object[][] getBestellingenDataTableManager(String bezorgerId){
        //int bestellingenSize = getBestellingen().size();
        ArrayList<Bestelling> bezorgerBestellingen = getBestellingenFromBezorger(bezorgerId);
        int bestellingenDataSize = bezorgerBestellingen.size();
        Object[][] bestellingenData = new Object[bestellingenDataSize][3];
        //int bestellingenDataCursor = 0;
//        BasicDBObject retrievable = new BasicDBObject();
//        oCursor = bestellingenCol.find(retrievable).iterator();
        System.out.println(bestellingenDataSize);
        for (int i = 0; i < bestellingenDataSize; i++){
            //System.out.println("hi");
            BasicDBObject retrievable = new BasicDBObject();
            oCursor = bestellingenCol.find(retrievable).iterator();
            while(oCursor.hasNext()){
                Document bestelling = oCursor.next();
                if (bestelling.get("_id").toString().equals(bezorgerBestellingen.get(i))) {
                    String plaats = bestelling.get("Plaats").toString();
                    String straatnaam = bestelling.get("Straatnaam").toString();
                    String huisnummer = bestelling.get("Huisnummer").toString();
                    String postcode = bestelling.get("Postcode").toString();
                    //int status = (int) bestelling.get("Status");

                    bestellingenData[i][0] = plaats;
                    bestellingenData[i][1] = straatnaam;
                    bestellingenData[i][2] = huisnummer;
                    bestellingenData[i][3] = postcode;
                }
            }
        }
        return bestellingenData;
    }
    //le method for updating bezorger status
}
