package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int index;
    static BilgiSistemi bilgi = new BilgiSistemi();
    static Scanner read = new Scanner(System.in);

    public static void Print(String x) {
        System.out.println(x);
    }

    public static String toString(Doktor doktor) {
        return (String.format(
                "%-20s %s\n%-20s %s\n%-20s %d\n__________________________________________________________________________________________",
                "Doktor Ismi:", doktor.getisim(), "Doktor TC Kimlik No:", doktor.getTCKimlik(), "Doktor Diploma No:",
                doktor.getDiplomaNo()));
    }

    public static String toString(Hasta hasta) {
        return (String.format(
                "%-20s %s\n%-20s %s\n%-20s %d\n__________________________________________________________________________________________",
                "Hasta Ismi:", hasta.getisim(), "Hasta TC Kimlik No:", hasta.getTCKimlik(), " Hasta Dogum Yili No:",
                hasta.getDogumYili()));
    }

    public static String toString(Tedavi tedavi) {
        return (String.format(
                "%-12s %-20s\t%-12s %-20s\n%-12s %-20s\t%-12s %-20s\nAyrintilari:\n%s\n__________________________________________________________________________________________",
                "Doktor Ismi:", tedavi.getDoktor().getisim(), "Hasta Ismi:", tedavi.getHasta().getisim(),
                "Doktor TCKN:", tedavi.getDoktor().getTCKimlik(), "Hasta TCKN:", tedavi.getHasta().getTCKimlik(),
                tedavi.getAyrintilar()));
    }

    public static List<Tedavi> findTedaviByDoktor(Doktor doktor) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        List<Tedavi> filtredList = new ArrayList<Tedavi>();
        for (Tedavi tedavi : bilgi.tList) {
            if (tedavi.getDoktor().getTCKimlik() == doktor.getTCKimlik()) {
                filtredList.add(tedavi);
            }
        }
        return filtredList;
    }

    public static List<Tedavi> findTedaviByHasta(Hasta hasta) throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        List<Tedavi> filtredList = new ArrayList<Tedavi>();
        for (Tedavi tedavi : bilgi.tList) {
            if (tedavi.getHasta().getTCKimlik() == hasta.getTCKimlik()) {
                filtredList.add(tedavi);
            }
        }
        return filtredList;
    }

    public static void PrintDoktorList() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Print(String.format("ID\t%-20s %-15s", "Doktor Ismi", "Doktor TC Kimlik No"));
        for (Doktor doktor : bilgi.dList) {
            Print(String.format("%02d.\t%-20s %-15s", bilgi.dList.indexOf(doktor) + 1, doktor.getisim(),
                    doktor.getTCKimlik())
                    + "\n_______________________________________________________________________");
        }

    }

    public static void PrintHastaList() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Print(String.format("ID\t%-20s %-15s", "Hasta Ismi", " Hasta TC Kimlik No"));
        for (Hasta hasta : bilgi.hList) {
            Print(String.format("%02d.\t%-20s %-15s", bilgi.hList.indexOf(hasta) + 1, hasta.getisim(),
                    hasta.getTCKimlik()) + "\n_______________________________________________________________________");
        }
    }

    public static void PrintTedaviList() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Print(String.format("ID  %-20s %-20s %-20s %-20s", "Doktor Ismi", "Doktor TC Kimlik No", "Hasta Ismi",
                "Hasta TC Kimlik No"));
        for (Tedavi tedavi : bilgi.tList) {
            Print(String.format("%02d. %-20s %-20s %-20s %-20s", bilgi.tList.indexOf(tedavi) + 1,
                    tedavi.getDoktor().getisim(),
                    tedavi.getDoktor().getTCKimlik(), tedavi.getHasta().getisim(),
                    tedavi.getHasta().getTCKimlik())
                    + "\n_______________________________________________________________________________________");
        }

    }

    public static void doktorMenu() throws InterruptedException, IOException, InputMismatchException {
        boolean flag = true;
        boolean validIndex;
        while (flag) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Print("1. Doktor Listesi\n2. Doktor Ekle\n3. Doktor Ara\n\n0. Geriye Don...");
            do {
                try {
                    Scanner read = new Scanner(System.in);
                    index = read.nextInt();

                    validIndex = false;
                } catch (Exception e) {
                    Print("Gecerli Sayi Girin...");
                    validIndex = true;
                }
            } while (validIndex);
            switch (index) {
                case 2:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Isim Girin:\n");
                    String name = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("TCKN Girin:\n");
                    String TC = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Diploma No Girin:\n");
                    int diplomaNo = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            diplomaNo = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (!bilgi.doktorEkle(new Doktor(name, TC, diplomaNo))) {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Print(name + " kisisi Basariyla Eklendi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    } else {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Print("Doktor Mevcuttur!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 1:
                    PrintDoktorList();
                    Print("\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 3:
                    findDoktorMenu();
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    flag = true;

            }
        }
    }

    public static void hastaMenu() throws InterruptedException, IOException, InputMismatchException {
        boolean flag = true;
        boolean validIndex;
        while (flag) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Print("1. Hasta Listesi\n2. Hasta Ekle\n3. Hasta Ara\n\n0. Geriye Don...");
            do {
                try {
                    Scanner read = new Scanner(System.in);
                    index = read.nextInt();

                    validIndex = false;
                } catch (Exception e) {
                    Print("Gecerli Sayi Girin...");
                    validIndex = true;
                }
            } while (validIndex);
            switch (index) {
                case 2:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Isim Girin:\n");
                    String name = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("TCKN Girin:\n");
                    String TC = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Dogum Yili Girin:\n");
                    int dy = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            dy = read.nextInt();
                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (!bilgi.hastaEkle(new Hasta(name, TC, dy))) {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Print(name + " Kisisi Basariyla Eklendi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    } else {
                        Print("Hasta Mevcuttur!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 1:
                    PrintHastaList();
                    Print("\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 3:
                    findHastaMenu();
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    flag = true;

            }
        }
    }

    public static void tedaviMenu() throws InterruptedException, IOException, InputMismatchException {
        boolean flag = true;
        boolean validIndex;
        while (flag) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Print("1. Tedavi Listesi\n2. Tedavi Ekle\n3. Tedavi Ara\n4. Ayrintilar Ekle\n\n0. Geriye Don...");
            do {
                try {
                    Scanner read = new Scanner(System.in);
                    index = read.nextInt();

                    validIndex = false;
                } catch (Exception e) {
                    Print("Gecerli Sayi Girin...");
                    validIndex = true;
                }
            } while (validIndex);
            switch (index) {
                case 2:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Doktor'un ID'sini Girin:\n");
                    int dID = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            dID = read.nextInt();
                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Hasta'nin ID'sini Girin:\n");
                    int hID = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            hID = read.nextInt();
                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {
                        bilgi.tedaviEkle(bilgi.hList.get(hID - 1), bilgi.dList.get(dID - 1));
                        Print("Tedavi Basariyla Eklendi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    } catch (IndexOutOfBoundsException e) {
                        Print("Girdiginiz ID Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }

                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 1:
                    PrintTedaviList();
                    Print("\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 3:
                    findTedaviMenu();
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                case 4:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Tedavi ID'si Girin\n");
                    int tID = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            tID = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    try {
                        bilgi.tList.get(tID - 1);
                        Print("Ayrintilari Girin:");
                        String ayrintilar = read.nextLine();
                        bilgi.tList.get(tID - 1).setAyrintilar(ayrintilar);
                        Print("Tamamlandi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");

                    } catch (IndexOutOfBoundsException e) {
                        Print("Girdiginiz Tedavi ID'si Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                default:
                    flag = true;

            }
        }
    }

    public static void findDoktorMenu() throws InterruptedException, IOException, InputMismatchException {
        boolean flag = true;
        boolean validIndex;
        while (flag) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Print("1. Doktor ID'si Kullanarak Ara\n2. Doktor TCKN'si Kullanarak Ara\n\n0. Geriye Don...");
            do {
                try {
                    Scanner read = new Scanner(System.in);
                    index = read.nextInt();

                    validIndex = false;
                } catch (Exception e) {
                    Print("Gecerli Sayi Girin...");
                    validIndex = true;
                }
            } while (validIndex);
            switch (index) {
                case 1:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Doktor'un ID'sini Girin:");
                    int id = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            id = read.nextInt();
                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {
                        Print("ID: " + id + '\n' + toString(bilgi.dList.get(id - 1)));
                    } catch (IndexOutOfBoundsException e) {
                        Print("Girdiginiz ID Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 2:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Doktor'un TCKN'sini Girin:");
                    String TC = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {
                        Print(toString(bilgi.doktorBul(TC)));
                        Print("\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    } catch (NullPointerException e) {
                        Print("Girdiginiz TCKN Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    flag = true;
            }
        }
    }

    public static void findHastaMenu() throws InterruptedException, IOException, InputMismatchException {
        boolean flag = true;
        boolean validIndex;
        while (flag) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Print("1. Hasta ID'si Kullanarak Ara\n2. Hasta TCKN'si Kullanarak Ara\n\n0. Geriye Don...");
            do {
                try {
                    Scanner read = new Scanner(System.in);
                    index = read.nextInt();

                    validIndex = false;
                } catch (Exception e) {
                    Print("Gecerli Sayi Girin...");
                    validIndex = true;
                }
            } while (validIndex);
            switch (index) {
                case 1:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Hasta'nin ID'sini Girin:");
                    int id = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            id = read.nextInt();
                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {

                        Print("ID: " + id + '\n' + toString(bilgi.hList.get(id - 1)));
                    } catch (IndexOutOfBoundsException e) {
                        Print("Girdiginiz ID Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 2:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Hasta'nin TCKN'sini Girin:");
                    String TC = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {

                        Print(toString(bilgi.hastaBul(TC)));
                    } catch (NullPointerException e) {
                        Print("Girdiginiz TCKN Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    flag = true;
            }
        }
    }

    public static void findTedaviMenu() throws InterruptedException, IOException, InputMismatchException {
        boolean flag = true;
        boolean validIndex;
        while (flag) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Print("1. Tedavi ID'si Kullanarak Ara\n2. Doktor TCKN'si Kullanarak Ara\n3. Hasta TCKN'si Kullanarak Ara\n\n0. Geriye Don...\n1. Ana Menuye Don...");
            do {
                try {
                    Scanner read = new Scanner(System.in);
                    index = read.nextInt();

                    validIndex = false;
                } catch (Exception e) {
                    Print("Gecerli Sayi Girin...");
                    validIndex = true;
                }
            } while (validIndex);
            switch (index) {
                case 1:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Tedavi ID'si Girin: ");
                    int id = 0;
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            id = read.nextInt();
                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {

                        Print("ID: " + id + "\n\n" + toString(bilgi.tList.get(id - 1)));
                        Print("\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    } catch (IndexOutOfBoundsException e) {
                        Print("Girdiginiz ID Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");

                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 2:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Doktor TCKN'si Girin: ");
                    String dTC = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {
                        for (Tedavi tedavi : findTedaviByDoktor(bilgi.doktorBul(dTC))) {
                            Print(toString(tedavi));
                            Print("\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                        }
                    } catch (NullPointerException e) {
                        Print("Girdiginiz TCKN Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 3:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Print("Hasta TCKN'si Girin: ");
                    String hTC = read.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    try {
                        for (Tedavi tedavi : findTedaviByHasta(bilgi.hastaBul(hTC))) {
                            Print(toString(tedavi));
                            Print("\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                        }
                    } catch (NullPointerException e) {
                        Print("Girdiginiz TCKN Bulunamadi!\n\n0. Geriye Don...\n1. Ana Menuye Don...");
                    }
                    do {
                        try {
                            Scanner read = new Scanner(System.in);
                            index = read.nextInt();

                            validIndex = false;
                        } catch (Exception e) {
                            Print("Gecerli Sayi Girin...");
                            validIndex = true;
                        }
                    } while (validIndex);
                    if (index == 1) {
                        flag = false;
                    } else {
                        break;
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    flag = true;
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException, IOException, IndexOutOfBoundsException, InputMismatchException {
        boolean flag = true;
        boolean validIndex;
        while (flag) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Print("1. Doktor Islemleri\n2. Hasta Islemleri\n3. Tedavi Islemleri\n\n9. Programi Kapat...");
            do {
                try {
                    Scanner read = new Scanner(System.in);
                    index = read.nextInt();

                    validIndex = false;
                } catch (Exception e) {
                    Print("Gecerli Sayi Girin...");
                    validIndex = true;
                }
            } while (validIndex);
            switch (index) {
                case 1:
                    doktorMenu();
                    flag = true;
                    break;
                case 2:
                    hastaMenu();
                    flag = true;
                    break;
                case 3:
                    tedaviMenu();
                    flag = true;
                    break;
                case 9:
                    System.exit(0);

            }
        }
    }
}