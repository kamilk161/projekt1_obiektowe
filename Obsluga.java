package projekt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Obsluga {

    //Sprawdzenie czy użytkownik podał liczbę
    private static Boolean isParsable(String toParse) {
        try {
            Integer.parseInt(toParse);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //Pobranie pracownika o podanym numerze
    private static Pracownik getPracownik(List<Pracownik> pracownicy, String numerStr) {
        int numer;
        if (isParsable(numerStr)) {
            numer = Integer.parseInt(numerStr);
        } else {
            System.out.println("Numer pracownika musi być liczbą");
            return null;
        }

        Pracownik pracownik;
        try {
            pracownik = pracownicy.get(numer);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Pracownik o podanym numerze nie istnieje");
            return null;
        }
        return pracownik;
    }

    //Wyświetlanie listy pracowników
    private static void wyswietlPracownikow(List<Pracownik> pracownicy) {
        for (int i = 0; i < pracownicy.size(); i++) {
            System.out.println(i + ": " + pracownicy.get(i));
        }
        System.out.println("");
    }

    //Zapis do pliku
    private static void zapisDoPliku(List<Pracownik> pracownicy) {
        try {
            File file = new File("pracownicy.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Pracownik pracownik : pracownicy) {
                bufferedWriter.write(pracownik.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Zapis zakończył się sukcesem.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu.");
        }
    }

    public static void main(String[] args) {
        Boolean status = true;
        List<Pracownik> pracownicy = new ArrayList<Pracownik>();
        Scanner scanner = new Scanner(System.in);
        String helper;

        while (status) {
            System.out.println("a - Wyświetl wszystkich pracowników");
            System.out.println("b - Dodaj pracownika");
            System.out.println("c - Daj podwyżke");
            System.out.println("d - Przenieś pracownika na urlop");
            System.out.println("e - zapisz raport do pliku");
            System.out.println("x - zakończ");

            char wybor = scanner.next().charAt(0);
            scanner.nextLine();
            switch(wybor) {
                case 'a':
                    wyswietlPracownikow(pracownicy);
                    break;
                case 'b':
                    System.out.print("Podaj imię: ");
                    String imie = scanner.nextLine();

                    System.out.print("Podaj pensję: ");
                    helper = scanner.nextLine();
                    int pensja = isParsable(helper) ? Integer.parseInt(helper) : 0;

                    System.out.print("Czy na urlopie?(true, false) ");
                    Boolean urlop = false;

                    if (scanner.hasNextBoolean()) {
                        urlop = scanner.nextBoolean();
                    }

                    pracownicy.add(new Pracownik(imie, pensja, urlop));
                    break;
                case 'c':
                    wyswietlPracownikow(pracownicy);
                    System.out.println("Podaj numer pracownika: ");
                    helper = scanner.nextLine();
                    Pracownik doPodwyzki = getPracownik(pracownicy, helper);

                    if (doPodwyzki != null) {
                        System.out.println("Podaj kwote podwyzki: ");
                        helper = scanner.nextLine();
                        int podwyzka = isParsable(helper) ? Integer.parseInt(helper) : 0;
                        doPodwyzki.setPensja(doPodwyzki.getPensja() + podwyzka);
                    }
                    break;
                case 'd':
                    wyswietlPracownikow(pracownicy);
                    System.out.println("Podaj numer pracownika: ");
                    helper = scanner.nextLine();
                    Pracownik naUrlop = getPracownik(pracownicy, helper);

                    if (naUrlop != null) {
                        naUrlop.setUrlop(true);
                    }
                    break;
                case 'e':
                    zapisDoPliku(pracownicy);
                    break;
                case 'x':
                    status = false;
                    break;
                default:
                    System.out.println("Nie ma takiej opcji.");
            }
            scanner.reset();
        }
        scanner.close();
    }

}
