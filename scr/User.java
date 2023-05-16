package scr;

public abstract class User {
    private String naam;
    private String achternaam;
    private String email;
    private String wachtwoord;

    public User(String naam, String achternaam, String email, String wachtwoord) {
        this.naam = naam;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }
}
