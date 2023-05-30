package src;

public class Adress {
    String plaats;
    String straatnaam;
    String huisnummer;
    String postcode;


    public Adress(String plaats, String straatnaam, String huisnummer, String postcode) {
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public String toString() {
            return plaats + " " + straatnaam + " " + huisnummer + " " + postcode;
    }
}
