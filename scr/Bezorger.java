package scr;

import java.util.ArrayList;

public class Bezorger {
    public String name;
    public String email;
    public int status;
    //add other fields. is a bestellingen field really necessary?
    public Bezorger(String name, String email, int status){
        this.name = name;
        this.status = status;
    }
}
