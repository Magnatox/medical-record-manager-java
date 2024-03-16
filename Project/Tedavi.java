package Project;

public class Tedavi {
    private Hasta m_Hasta;
    private Doktor m_Doktor;
    private String m_Ayrintilar = "";

    public Tedavi(Hasta hasta, Doktor doktor) {
        m_Hasta = hasta;
        m_Doktor = doktor;
    }

    public Hasta getHasta() {
        return m_Hasta;
    }

    public Doktor getDoktor() {
        return m_Doktor;
    }

    public String getAyrintilar() {
        return m_Ayrintilar;
    }

    public void setAyrintilar(String ayrinti) {
        m_Ayrintilar = ayrinti;
    }
}
