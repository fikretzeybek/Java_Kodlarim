package P05;

import java.util.Scanner;

public class DoWhileRakamlariTopla {
    public static void main(String[] args) {
        // Kullanıcdan sayı alıp rakamlar toplamını hesaplayıp yazdırın.

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tamsayı girniz:");
        int sayi = scanner.nextInt();
        int rakam = 0;
        int rakamlarToplami = 0;

        do {
            rakam = sayi % 10;
            rakamlarToplami += rakam;
            sayi /= 10;
        } while (sayi > 0);
        System.out.println(rakamlarToplami);


    }
}
