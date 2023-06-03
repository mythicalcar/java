package scr;

public class Bestelling {
    String id;
    Adress adress;
    int status;
    public Bestelling(String id, Adress adress, int status){
        this.id = id;
        this.adress = adress;
        this.status = status;
    }
    public String getPostcode() {
        return adress.getPostcode();
    }
}
