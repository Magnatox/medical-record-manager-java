package Project;

import java.util.ArrayList;
import java.util.List;

public class BilgiSistemi {

    List<Hasta> hList = new ArrayList<Hasta>();
    List<Doktor> dList = new ArrayList<Doktor>();
    List<Tedavi> tList = new ArrayList<Tedavi>();
    boolean isDoktorExist = true;
    boolean isHastaExist = true;

    public boolean hastaEkle(Hasta currentHasta) {
        if (hList.isEmpty()) {
            isHastaExist = false;
            hList.add(currentHasta);
        } else {
            Hasta hasta = hList.stream().filter(findhas -> currentHasta.getTCKimlik().equals(findhas.getTCKimlik()))
                    .findAny().orElse(null);
            if (hasta == null) {
                isHastaExist = false;
                hList.add(currentHasta);
            } else {
                isHastaExist = true;
            }
        }

        return isHastaExist;
    }

    public Hasta hastaBul(String TCKimlik) {
        Hasta hasta = hList.stream().filter(findhas -> TCKimlik.equals(findhas.getTCKimlik()))
                .findAny().orElse(null);
        return hasta;
    }

    public boolean doktorEkle(Doktor currentDoktor) {
        if (dList.isEmpty()) {
            isDoktorExist = false;
            dList.add(currentDoktor);
        } else {
            Doktor doktor = dList.stream().filter(finddok -> currentDoktor.getTCKimlik().equals(finddok.getTCKimlik()))
                    .findAny().orElse(null);
            if (doktor == null) {
                isDoktorExist = false;
                dList.add(currentDoktor);
            } else {
                isDoktorExist = true;
            }
        }

        return isDoktorExist;
    }

    public Doktor doktorBul(String TCKimlik) {
        Doktor doktor = dList.stream().filter(finddok -> TCKimlik.equals(finddok.getTCKimlik()))
                .findAny().orElse(null);
        return doktor;
    }

    public void tedaviEkle(Hasta hasta, Doktor doktor) {
        if (hList.contains(hasta) && dList.contains(doktor)) {
            Tedavi tedavi = new Tedavi(hasta, doktor);
            tList.add(tedavi);
        }
    }

    public Tedavi tedaviBul(Doktor doktor) {
        Tedavi tedavi = tList.stream().filter(findt -> doktor.getTCKimlik().equals(findt.getDoktor().getTCKimlik()))
                .findAny().orElse(null);
        return tedavi;
    }
}
