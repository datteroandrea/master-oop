package po2.patterns;

public class Singleton {
    private static Singleton instance = null;
    private String stuff;

    private Singleton(String stuff) {
        this.stuff = stuff;
    }

    public static Singleton getInstance(String stuff) {
        if(instance == null) // creo l'istanza se già non esiste
            instance = new Singleton(stuff);
        if(instance != null && !instance.stuff.equals(stuff)) // cambio i campi dell'istanza aggiornandoli a valori nuovi
            instance.stuff = stuff;
        return instance;
    }

}
