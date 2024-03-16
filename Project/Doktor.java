package Project;

public class Doktor extends Kisi {

    public Doktor(String isim, String TCkimlik, int diplomaNo) {
        super(isim, TCkimlik);
        m_DiplomaNo = diplomaNo;
    }

    private int m_DiplomaNo;

    public int getDiplomaNo() {
        return m_DiplomaNo;
    }
}
