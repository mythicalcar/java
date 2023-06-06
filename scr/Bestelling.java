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

    public String toString(){
        return "Bestelling[id=" + this.id + ", adress=" + this.adress + ", status=" + this.status + "]";
    }
}
