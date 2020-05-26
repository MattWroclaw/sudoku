package innyPomysl;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        int[] rzad1 = {0,1,0,0,9,0,0,7,0};
        int[] rzad2 = {6,0,4,0,0,0,2,0,9};
        int[] rzad3 = {9,0,0,5,0,6,0,0,3};
        int[] rzad4 = {0,9,0,6,0,1,0,3,0};
        int[] rzad5 = {4,0,0,0,0,0,0,0,6};
        int[] rzad6 = {0,7,0,2,0,5,0,1,0};
        int[] rzad7 = {5,0,0,1,0,9,0,0,8};
        int[] rzad8 = {7,0,8,0,0,0,3,0,1};
        int[] rzad9 = {0,4,0,0,6,0,0,2,0};

        int[][] sudoku = {rzad1, rzad2, rzad3, rzad4, rzad5, rzad6, rzad7, rzad8, rzad9};
        Sprawdzenia sprawdzenia = new Sprawdzenia();

        int[][] ints = sprawdzenia.wypisanieCyfrZKwadracika(sudoku);

        for (int[] rzad: ints) {
            System.out.println(Arrays.toString(rzad));
        }

    }
}
