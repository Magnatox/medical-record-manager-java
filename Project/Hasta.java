package Project;

public class Hasta extends Kisi {

    public Hasta(String isim, String TCkimlik, int dogumYili) {
        super(isim, TCkimlik);
        m_DogumYili = dogumYili;
    }

    private int m_DogumYili;

    public int getDogumYili() {
        return m_DogumYili;
    }

}
