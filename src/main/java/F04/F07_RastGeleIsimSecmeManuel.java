package F04;

import java.util.Random;

public class F07_RastGeleIsimSecmeManuel {
    public static void main(String[] args) {

        String[] isimlerDizi = {"Ahmet", "Omer", "Mehmet", "Elif", "Deniz", "Berivan", "Suleyman", "Samet"};
        Random rastgele = new Random();
        int kacinciEleman = rastgele.nextInt(isimlerDizi.length);
        System.out.println("Rastgele seçilen isim: " + isimlerDizi[kacinciEleman]);


    }
}