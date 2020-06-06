package innyPomysl;

public class Pomocnicze {

    public static void main(String[] args) {


        int zmienna = 0;
        int licznikKWadratow = 0;
        for (int wierszKwadratu = 0; wierszKwadratu < 3; wierszKwadratu++) {

            for (int kwadrat = 0; kwadrat < 3; kwadrat++) {

                for (int wiersz = 0; wiersz < 3; wiersz++) {
                    for (int pole = 0; pole < 3; pole++) {
                        if (zmienna != kwadrat) {
                            zmienna = kwadrat;
                            System.out.println("zmiana kwadratu, numer kwadratu = " + licznikKWadratow);
                        }
                        System.out.println(pole + 9 * wiersz + 3 * kwadrat + wierszKwadratu * 27);
                    }
                }

            }
            licznikKWadratow++;
        }

    }
}
