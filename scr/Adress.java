package scr;

public class Adress {
    String plaats;
    String straatnaam;
    String huisnummer;
    String toevoeging;

    public Adress(String plaats, String straatnaam, String huisnummer) {
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.toevoeging = null;
    }

    public Adress(String plaats, String straatnaam, String huisnummer, String toevoeging) {
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
    }

    @Override
    public String toString() {
        if (this.toevoeging==null){
            return plaats + " " + straatnaam + " " + huisnummer;
        }
        return plaats + " " + straatnaam + " " + huisnummer + " " + toevoeging;

    }
}
