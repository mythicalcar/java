package scr;

import org.bson.BsonArray;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class Bezorger {
    String id;
    String name;
    String email;
    int status;
    BsonArray orders;
    //add other fields. is a bestellingen field really necessary?
    public Bezorger(String id, String name, String email, int status){
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }
}
