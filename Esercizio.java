import java.util.Random;
import java.util.Scanner;

public class GestoreNumeriCasuali {

    public static void mostraVettore(int[] vettore, int dimensione) {
        for (int i = 0; i < dimensione; i++) {
            System.out.println(vettore[i]);
        }
    }

    public static void ordinaVettore(int[] vettore, int dimensione) {
        boolean scambiato;
        for (int i = 0; i < dimensione; i++) {
            scambiato = false;
            for (int j = 0; j < dimensione - 1 - i; j++) {
                if (vettore[j] > vettore[j + 1]) {
                    int temp = vettore[j];
                    vettore[j] = vettore[j + 1];
                    vettore[j + 1] = temp;
                    scambiato = true;
                }
            }
            if (!scambiato) break;
        }
    }

    public static int unisciVettori(int[] vettoreA, int lenA, int[] vettoreB, int lenB, int[] unione) {
        int i = 0, j = 0, k = 0;
        while (i < lenA && j < lenB) {
            if (vettoreA[i] < vettoreB[j]) {
                unione[k++] = vettoreA[i++];
            } else {
                unione[k++] = vettoreB[j++];
            }
        }
        while (i < lenA) unione[k++] = vettoreA[i++];
        while (j < lenB) unione[k++] = vettoreB[j++];
        return k;
    }

    public static int cercaValore(int[] vettore, int dimensione, int valore) {
        int sinistra = 0, destra = dimensione - 1;
        while (sinistra <= destra) {
            int centro = (sinistra + destra) / 2;
            if (vettore[centro] == valore) return centro;
            if (vettore[centro] < valore) sinistra = centro + 1;
            else destra = centro - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random casuale = new Random();

        System.out.print("Quanti numeri casuali vuoi generare? ");
        int quantita;
        do {
            quantita = Integer.parseInt(input.nextLine());
        } while (quantita <= 0);

        int[] vettorePari = new int[quantita];
        int[] vettoreDispari = new int[quantita];
        int contaPari = 0, contaDispari = 0;

        for (int i = 0; i < quantita; i++) {
            int numeroGenerato = casuale.nextInt(quantita * 100) + 1;
            if (numeroGenerato % 2 == 0)
                vettorePari[contaPari++] = numeroGenerato;
            else
                vettoreDispari[contaDispari++] = numeroGenerato;
        }

        System.out.println("\n--- Numeri pari generati ---");
        mostraVettore(vettorePari, contaPari);

        System.out.println("\n--- Numeri dispari generati ---");
        mostraVettore(vettoreDispari, contaDispari);

        ordinaVettore(vettorePari, contaPari);
        ordinaVettore(vettoreDispari, contaDispari);

        System.out.println("\n--- Numeri pari ordinati ---");
        mostraVettore(vettorePari, contaPari);

        System.out.println("\n--- Numeri dispari ordinati ---");
        mostraVettore(vettoreDispari, contaDispari);

        int[] vettoreUnito = new int[quantita * 2];
        int totale = unisciVettori(vettorePari, contaPari, vettoreDispari, contaDispari, vettoreUnito);

        System.out.println("\n--- Tutti i numeri uniti e ordinati ---");
        mostraVettore(vettoreUnito, totale);

        System.out.print("\nInserisci un numero da cercare: ");
        int daCercare = Integer.parseInt(input.nextLine());
        int posizione = cercaValore(vettoreUnito, totale, daCercare);

        if (posizione != -1)
            System.out.println("Il numero " + daCercare + " è stato trovato alla posizione " + posizione + ".");
        else
            System.out.println("Il numero " + daCercare + " non è presente nel vettore.");

        input.close();
    }
}
