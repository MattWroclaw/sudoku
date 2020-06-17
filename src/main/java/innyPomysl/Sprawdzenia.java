package innyPomysl;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sprawdzenia {

    private int[] sprawdzenieMozliweCfyry(int[] rzad) { // pakuje to tablicy tylko te cyfry które nie występują w rzędzie
        int[] pomocniczy = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int cyfraWystepujaca : rzad
        ) {
            if (cyfraWystepujaca > 0)
                pomocniczy[cyfraWystepujaca - 1] = 0;
        }
        return pomocniczy;
    }

    public int[][] sprawdzenieRzedow(int[][] sudoku) {
        int[][] mozliweCywryWRzedach = new int[9][9];
        int licznik = 0;
        for (int[] rzad : sudoku) {
            int[] juzSprawdzone = sprawdzenieMozliweCfyry(rzad);
            mozliweCywryWRzedach[licznik] = juzSprawdzone;
            licznik++;
        }
        return mozliweCywryWRzedach;
    }

    private int[][] odwroceniePionNaRzad(int[][] sudoku) {
        int[][] odwroconyMatrix = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                odwroconyMatrix[j][i] = sudoku[i][j];
            }
        }
        return odwroconyMatrix;
    }

     int[][] sprawdzenieKolumn(int[][] sudoku) {
        int[][] odwrocnyMatrix = odwroceniePionNaRzad(sudoku);
        return sprawdzenieRzedow(odwrocnyMatrix);
    }

    public int[][] wypisanieCyfrZKwadracika(int[][] sudoku) {
        int[][] cyfryZKwadracika = new int[9][9];

        int duzyLicznik = 0;
        for (int k = 0; k < 9; k = k + 3) {
            for (int m = 0; m < 9; m = m + 3) {
                int[] pom = new int[9];
                int licznik = 0;
                for (int i = k; 3 + k - i > 0; i++) {
                    for (int j = m; 3 + m - j > 0; j++) {
                        pom[licznik] = sudoku[i][j];
                        licznik++;
                    }
                }
                cyfryZKwadracika[duzyLicznik] = pom;
                duzyLicznik++;
            }
        }
        return cyfryZKwadracika;
    }

    public int[][] sprawdzenieMozliwychCyfrZKwadracikow(int[][] sudoku) {
        int[][] cyfryZKwadracikow = wypisanieCyfrZKwadracika(sudoku);
        return sprawdzenieRzedow(cyfryZKwadracikow);
    }

    public int[][] sprawdznieWPoszczegolnychKomorkachMoliwychCyfrBezKwadratow(int[][] sudoku, int[][] mozliweWRzedach, int[][] mozliweWKolumnach) {
        int[][] odpowiedz = new int[81][9];
        int licznik = 0;

        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                int[] pomocnicza = new int[9];

                for (int j = 0; j < 9; j++) {
                    if (mozliweWRzedach[k][j] == mozliweWKolumnach[i][j]) {
                        pomocnicza[j] = mozliweWRzedach[k][j];
                    }
                }
                odpowiedz[licznik] = pomocnicza;
                licznik++;
            }
        }

        return odpowiedz;
    }

    public int[][] sprawdzenieWrazZKwadratami(int[][] sudoku, int[][] sprawdzoneRzedyIKolumny, int[][] mozliweWKwadracie) {
        int[][] odpowiedz = new int[81][9];

        for (int wierszKwadratu = 0; wierszKwadratu < 3; wierszKwadratu++) {
            for (int kwadrat = 0; kwadrat < 3; kwadrat++) {
                for (int wiersz = 0; wiersz < 3; wiersz++) {
                    for (int pole = 0; pole < 3; pole++) {
                        for (int i = 0; i < 9; i++) {
                            if (mozliweWKwadracie[kwadrat + 3*wierszKwadratu][i] == sprawdzoneRzedyIKolumny[pole + 9 * wiersz + 3 * kwadrat + wierszKwadratu * 27][i]) {
                                odpowiedz[pole + 9 * wiersz + 3 * kwadrat + wierszKwadratu * 27][i] = mozliweWKwadracie[kwadrat + 3*wierszKwadratu][i];
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((sudoku[i][j] != 0)) {
                    int[] pom = new int[9];
                    pom[j] = sudoku[i][j] * (-1);
                    odpowiedz[j + 9 * i] = pom;
                }
            }
        }
        return odpowiedz;
    }

    int[][] drukowanieRozwiazan(int[][] suroweRozwiazania) {

        int [][] komorkiZPojedynczymiRozwiazaniami = new int[suroweRozwiazania.length][];

        for (int i = 0; i < suroweRozwiazania.length; i++) {

            if ((zawieraMinusowe(suroweRozwiazania[i]) == false) &&
                    (czyJestPojedynczeRozwiazanie(suroweRozwiazania[i]) == true)) {
//                System.out.println("Komorka nr " + (i+1) + Arrays.toString(suroweRozwiazania[i]));
                komorkiZPojedynczymiRozwiazaniami[i] = suroweRozwiazania[i];
            }
//              TODO w tej metodzie trzeba zmienić "poj rozwiązanie" na wielokrotne rozwiązanie
//            czyli jak nie ma pojedycznego to patrzymy gdzie jest najmniej opcji, następnie bierzemy pierwszą możliwą cyfrę
//            i sprawdzamy czy jest możliwe rozwiązanie reszty zadania z tą opcją. Jeśli nie, to bierzemy następną.
//             Ale to trzeba zrobić na to osobną metodę...
        }
        return komorkiZPojedynczymiRozwiazaniami;
    }

    private boolean zawieraMinusowe(int[] tab) {
        boolean minusowe = false;
        for (int liczba : tab) {
            if (liczba < 0) {
                minusowe = true;
            }
        }
        return minusowe;
    }

    private boolean czyJestPojedynczeRozwiazanie(int[] tab) {
        boolean pojedynczeRozwiazanie = false;
        int licznikRozwiazan = 0;
        for (int liczba : tab) {
            if (liczba > 0) {
                licznikRozwiazan++;
            }
        }
        if (licznikRozwiazan == 1) {
            pojedynczeRozwiazanie = true;
        }
        return pojedynczeRozwiazanie;
    }

// Pobranie rozwiazan i wpisanie ich do wejściowej tabeli sudoku (zastąpienie 0)

    public int[][] sudokuZCzesciowymiRozwiazaniami ( int [][]sudoku ,int[][] pojedynczeRozwiazania) {
        for (int i=0; i<pojedynczeRozwiazania.length; i++){

                if (pojedynczeRozwiazania[i] != null) {
//                    znalezc  gdzie jest cyfra != 0
                    int indeksOdpowiedzi =0;
                    for ( int j=0; j<9; j++){
                            if (pojedynczeRozwiazania[i][j] >0){
                                indeksOdpowiedzi = j;
//                    zapisac ja na odpoweiednim miejscu w sudoku (zastapic dotychczasowe zero
                                sudoku[(i)/9] [(i) %9] = pojedynczeRozwiazania[i] [indeksOdpowiedzi];
                                break;
                            }
                    }
                }
        }
        return sudoku;
    }

    public int[][] sudokuZKrokiemRozwiazania_GLOBAL (int [][] sudoku){
        int[][] kolumn = sprawdzenieKolumn(sudoku);

        int[][] rzedow = sprawdzenieRzedow(sudoku);

        int[][] kolumnaIrzad = sprawdznieWPoszczegolnychKomorkachMoliwychCyfrBezKwadratow(sudoku, rzedow, kolumn);

        int[][] sprawdzenieMozliwychCyfrZKwadracikow = sprawdzenieMozliwychCyfrZKwadracikow(sudoku);

        int[][] pierwszyKwadrat_gotowe = sprawdzenieWrazZKwadratami(sudoku ,kolumnaIrzad, sprawdzenieMozliwychCyfrZKwadracikow);

        int[][] pojedynczeRozwiazania = drukowanieRozwiazan(pierwszyKwadrat_gotowe);

        int[][] noweSudoku = sudokuZCzesciowymiRozwiazaniami(sudoku, pojedynczeRozwiazania);

        return noweSudoku;

    }


    public int[][] rozwiazywanieWPetli(int[][] sudoku){

        while (!czyJuzRozwiazana(sudoku)){
            sudoku = sudokuZKrokiemRozwiazania_GLOBAL(sudoku);
        }
        return sudoku;
    }

    private boolean czyJuzRozwiazana (int [][] sudoku){
        boolean juzRozwiazana = true;
        for (int [] rzad : sudoku){
            for (int i=0; i<rzad.length; i++){
                if (rzad[i] ==0){
                    juzRozwiazana = false;
                    break;
                }
            }
        }
        return juzRozwiazana;
    }

    // metoda testowa, wypisuje index komórki w kolejności od tej gdzie jest najwęższe rozwiązanie
    public  int ktoraKomorkaMaNajbardziejJednoznaczneRozwiazania (int [][] sprawdzenieWrazZKwadratami){

        int[] ileRozwiazanNaDanymIndexie = new int[sprawdzenieWrazZKwadratami.length];
// sprawdzenie ilościowo ile jest rozwiązań w poszczególnych komórkach
        int indexyKomorek = 0;
        for (int[] rozwiazaniaZDanejKomorki : sprawdzenieWrazZKwadratami){
            int licznikPotencjalnychRozwiazan =0;
            for (int potencjalneRozwiazanie : rozwiazaniaZDanejKomorki){
                if (potencjalneRozwiazanie > 0){
                    licznikPotencjalnychRozwiazan ++;
                }
            }
            ileRozwiazanNaDanymIndexie[indexyKomorek] = licznikPotencjalnychRozwiazan;
            indexyKomorek++;
        }
//        sprawdzenie, minimalnej liczny rozwiązań spośród wszystkich komórek
        int najmniejWynikow = IntStream.of(ileRozwiazanNaDanymIndexie).filter(value -> value>0).min().getAsInt();

//        odnalezienie komórki, która ma daną liczbę rozwiązań, oraz wskazanie jakie to są rozwiązania..
        int indexKomorkiGdzieJestNajbardziejPrecyzyjnyWynik = -1;
        for (int i=0; i<sprawdzenieWrazZKwadratami.length; i++){
            int licznikCyfr =0;
            for (int cyfra :
                    sprawdzenieWrazZKwadratami[i]) {
                if (cyfra>0){
                    licznikCyfr++;
                }
            }
            if (licznikCyfr == najmniejWynikow){
                indexKomorkiGdzieJestNajbardziejPrecyzyjnyWynik = i;
            }
        }
        return indexKomorkiGdzieJestNajbardziejPrecyzyjnyWynik;
    }


}