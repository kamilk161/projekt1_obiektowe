package projekt;

public class Pracownik {
    private String imie;
    private int pensja;
    private boolean urlop;

    public Pracownik(String imie, int pensja, Boolean urlop) {
        this.imie = imie;
        this.pensja = pensja;
        this.urlop = urlop;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }

    public boolean isUrlop() {
        return urlop;
    }

    public void setUrlop(Boolean urlop) {
        this.urlop = urlop;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "imie='" + imie + '\'' +
                ", pensja=" + pensja +
                ", urlop=" + urlop +
                '}';
    }
}