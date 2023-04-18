package kbs;

import java.util.Iterator;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
//Wh3VKQTXN6jobpC0
public class Database {
    MongoClient client = MongoClients.create(
            "mongodb+srv://henk:Wh3VKQTXN6jobpC0@kbs.teqpcjy.mongodb.net/?retryWrites=true&w=majority");
    MongoDatabase db = client.getDatabase("Users");
    MongoCollection<Document> col = db.getCollection("accounts");
    BasicDBObject retrievable = new BasicDBObject();
    FindIterable<Document> iterDoc;
    Iterator<Document> it;
    MongoCursor<Document> cursor;
    int i = 1;

    Database() {
        // eh
        retrieveAllDocs();
    }

    public Boolean createAccount(String name, String password) {
        retrievable.put("Name", name);
        cursor = col.find(retrievable).iterator();

        if (cursor.hasNext()) {
            return false;
        } else {
            Document document = new Document("_id", i);
            document.append("Name", name);
            document.append("Password", password);
            document.append("Notes", "");
            col.insertOne(document);
            return true;
        }
    }

    public Boolean checkUserData(String name, String password) {
        System.out.println(password);
        Boolean returnVal = false;
        retrievable.put("Name", name);
        cursor = col.find(retrievable).iterator();
        if (cursor.hasNext()) {
            Document user = cursor.next();
            if (password.equals(user.get("Password"))) {
                returnVal = true;
            }
        }
        System.out.println(returnVal);
        return returnVal;
    }

    public void retrieveAllDocs() {
        iterDoc = col.find();
        it = iterDoc.iterator();
        while (it.hasNext()) {
            i++;
            it.next();
            // System.out.println(i);
        }
    }

    public void saveNotes(String notes, String name) {
        col.updateOne(Filters.eq("Name", name), Updates.set("Notes", notes));
    }

    public Document getUser(String name) {
        retrievable.put("Name", name);
        cursor = col.find(retrievable).iterator();
        Document user = cursor.next();

        return user;
    }
}
