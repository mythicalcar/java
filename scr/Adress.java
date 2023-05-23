package scr;

public class Adress {
    String plaats;
    String straatnaam;
    String huisnummer;

    public Adress(String plaats, String straatnaam, String huisnummer) {
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
    }

    @Override
    public String toString() {
            return plaats + " " + straatnaam + " " + huisnummer;
    }
}
