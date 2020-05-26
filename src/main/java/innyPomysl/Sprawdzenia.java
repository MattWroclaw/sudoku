package innyPomysl;

public class Sprawdzenia {

    public int[] sprawdzenieMozliweCfyry(int[] rzad) {
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

}
