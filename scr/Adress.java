package scr;

public class Adress {
    String plaats;
    String straatnaam;
    String huisnummer;
    String toevoeging;
    String postcode;

    public Adress(String plaats, String straatnaam, String huisnummer, String postcode) {
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.setPostcode(postcode);
        this.toevoeging = null;
    }

    public Adress(String plaats, String straatnaam, String huisnummer, String toevoeging, String postcode) {
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
        this.postcode = postcode;
    }

    public void setPostcode(String postcode) {
        if (postcode.matches("\\d{4}[A-Za-z]{2}")) {
            this.postcode = postcode;
        } else {
            System.out.println("ongeldige postcode");
        }
    }

}
