package innyPomysl;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Test2 {
    public static void main(String[] args) {
        int[] rzad1 = {0,0,0,0,0,4,0,0,0};
        int[] rzad2 = {6,2,0,0,0,0,0,0,8};
        int[] rzad3 = {5,0,0,0,0,0,4,1,0};
        int[] rzad4 = {1,0,0,4,0,9,5,0,0};
        int[] rzad5 = {0,0,2,5,0,6,7,0,0};
        int[] rzad6 = {0,0,9,2,0,7,0,0,6};
        int[] rzad7 = {0,9,6,0,0,0,0,0,1};
        int[] rzad8 = {2,0,0,0,0,0,0,5,4};
        int[] rzad9 = {0,0,0,3,0,0,0,0,0};
        int[][] sudoku = {rzad1, rzad2,rzad3,rzad4,rzad5,rzad6,rzad7,rzad8,rzad9};

        Sprawdzenia spr = new Sprawdzenia();
        System.out.println("Kolumny");
        int[][] sprawdzenieKolumn = spr.sprawdzenieKolumn(sudoku);
        for (int[] kol: sprawdzenieKolumn
             ) {
            System.out.println(Arrays.toString(kol));
        }

        System.out.println("Rzedy");
        int[][] sprawdzenieRzedow = spr.sprawdzenieRzedow(sudoku);
        for (int[] rzad: sprawdzenieRzedow
        ) {
            System.out.println(Arrays.toString(rzad));
        }

        System.out.println("Sprawdzone ale bez kwadratow");
        int[][] wPoszczegolnychKomorkachMoliwychCyfrBezKwadratow =
                spr.sprawdznieWPoszczegolnychKomorkachMoliwychCyfrBezKwadratow(sudoku, sprawdzenieRzedow, sprawdzenieKolumn);
        int i=0;
        for (int[] sprawdzone : wPoszczegolnychKomorkachMoliwychCyfrBezKwadratow){
            System.out.println(i+ " "+Arrays.toString(sprawdzone));
            i++;
        }

        System.out.println("Sprawdzenie wraz z uwzględniem Kwadratów");
        int[][] sprawdzenieWrazZKwadratami = spr.sprawdzenieWrazZKwadratami(sudoku,
                wPoszczegolnychKomorkachMoliwychCyfrBezKwadratow,
                spr.sprawdzenieMozliwychCyfrZKwadracikow(sudoku));
        int k =0;
        for (int[] sprawdzWrazKwadr :  sprawdzenieWrazZKwadratami){
            System.out.println(k+" "+Arrays.toString(sprawdzWrazKwadr));
            k++;
        }

        System.out.println("Wypisanie: komorka - ile ma rozwiazan");
        int komorkaMaNajbardziejJednoznaczneRozwiazania = spr.ktoraKomorkaMaNajbardziejJednoznaczneRozwiazania(sprawdzenieWrazZKwadratami);
        System.out.println(komorkaMaNajbardziejJednoznaczneRozwiazania);

//        int najmniejWynikow = IntStream.of().filter(value -> value>0).min().getAsInt();
//        System.out.println((najmniejWynikow));

    }
}
