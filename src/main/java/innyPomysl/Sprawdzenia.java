package innyPomysl;

import java.util.Arrays;

public class Sprawdzenia {

    public int[] sprawdzenieMozliweCfyry(int[] rzad) { // pakuje to tablicy tylko te cyfry które nie występują w rzędzie
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

    public int[][] odwroceniePionNaRzad(int[][] sudoku) {
        int[][] odwroconyMatrix = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                odwroconyMatrix[j][i] = sudoku[i][j];
            }
        }
        return odwroconyMatrix;
    }

    public int[][] sprawdzenieKolumn(int[][] sudoku) {
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

    void drukowanieRozwiazan(int[][] suroweRozwiazania) {
        for (int i = 0; i < suroweRozwiazania.length; i++) {

            if ((zawieraMinusowe(suroweRozwiazania[i]) == false) &&
                    (czyJestPojedynczeRozwiazanie(suroweRozwiazania[i]) == true)) {
                System.out.println("Komorka nr " + (i+1) + Arrays.toString(suroweRozwiazania[i]));
            }

        }

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


}