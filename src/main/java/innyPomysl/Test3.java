package innyPomysl;

import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        int[] rzad1 = {0,8,3,6,0,5,0,0,0};
        int[] rzad2 = {0,0,0,7,0,0,0,3,0};
        int[] rzad3 = {0,0,7,0,9,3,0,0,0};
        int[] rzad4 = {0,5,0,4,0,0,8,0,0};
        int[] rzad5 = {0,3,0,0,8,0,0,9,0};
        int[] rzad6 = {0,0,9,0,0,1,0,2,0};
        int[] rzad7 = {0,0,0,1,4,0,6,0,0};
        int[] rzad8 = {0,2,0,0,0,6,0,0,0};
        int[] rzad9 = {0,0,0,3,0,8,7,1,0};
        int[][] sudoku = {rzad1, rzad2,rzad3,rzad4,rzad5,rzad6,rzad7,rzad8,rzad9};
        Sprawdzenia spr = new Sprawdzenia();
        int[][] ints = spr.rozwiazywanieWPetli(sudoku);
        for (int[]wiersz: ints ) {
            System.out.println(Arrays.toString(wiersz));
        } ;


    }
}
