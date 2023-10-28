package ATM_Proje;


import java.text.SimpleDateFormat;
import java.util.*;

public class atmProje {

    static Scanner scanner = new Scanner(System.in);
    private static final String musteriHesapNo = "111111111111";
    private static String musteriSifre = "aaaa";
    private static final String musteriAdi = "Alimiz Canımız ";
    private static final String musteriIban = "TR320010009999901234567890"; //"TR320010009999901234567890";
    private static double bakiye = 5000;
    private static int girisDenemesi = 3;
    private static final Date sonBasariliGiris = new Date();
    private static final List<String> islemler = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        //musteriGiris();
        anaEkran();
    }

    public static void musteriGiris() throws InterruptedException {
        System.out.println("Lütfen 12 haneli müşteri hesap numaranızı giriniz : ");
        String inputHesapNo = scanner.next();
        while (girisDenemesi > 0) {

            System.out.println("Lütfen 4 haneli müşteri şifrenizi giriniz : ");
            String inputSifre = scanner.next();

            System.out.println("Bilgileriniz kontrol ediliyor, lütfen bekleyiniz.");

            Thread.sleep(1000); // 5 saniye bekleme süresi

            if (inputHesapNo.equals(musteriHesapNo) && inputSifre.equals(musteriSifre)) {
                System.out.println("Sayın " + musteriAdi + "hoş geldiniz.");
                break; // Doğru giriş yapıldığında döngüyü kır
            } else {
                girisDenemesi--;
                System.out.println("Şifreniz hatalıdır, kalan giriş hakkınız: " + girisDenemesi);
            }
        }

        if (girisDenemesi == 0) {
            System.out.println("Hesabınız başarısız 3 giriş denemesinden dolayı bloke olmuştur.");
            cikisYap();
        }

    }

    public static void anaEkran() throws InterruptedException {
        System.out.println("=====GELBANK ATM'YE HOŞGELDİNİZ=====");
        System.out.println("===Lütfen Yapmak İstediğiniz İşlemi Seçiniz===");
        System.out.println("[1] Bakiye Sorgulama");
        System.out.println("[2] Para Çekme");
        System.out.println("[3] Para Yatırma");
        System.out.println("[4] Para Gönderme");
        System.out.println("[5] Şifre Değiştirme");
        System.out.println("[6] Çıkış Yapma");

        int secim = scanner.nextInt();

        switch (secim) {
            case 1:
                bakiyeSorgula();
                break;
            case 2:
                paraCekme();
                break;
            case 3:
                paraYatirma();
                break;
            case 4:
                paraGonderme();
                break;
            case 5:
                sifreDegistirme();
                break;
            case 6:
                cikisYap();
                System.out.println("Çıkış yapılıyor. İyi günler!");
                break;
            default:
                System.out.println("Geçersiz işlem seçimi. Lütfen tekrar deneyin.");
                anaEkran(); // Geçersiz seçim, tekrar anaEkranı çağırır
                break;
        }
    }

    private static void bakiyeSorgula() throws InterruptedException {

        SimpleDateFormat sonTarih = new SimpleDateFormat("dd.MM.yyyy");

        Calendar takvim = Calendar.getInstance();
        takvim.setTime(sonBasariliGiris);
        takvim.add(Calendar.DAY_OF_MONTH, -2);
        Date ikiGunOnce = takvim.getTime();

        System.out.println("Son başarılı girişiniz : " + sonTarih.format(ikiGunOnce) + " 'dür");

        System.out.println("\nHesabınızda yapılan son 5 işlem listelenmiştir:");
        islemler.add("Alınan EFT ---------------> 500 TL");
        islemler.add("Karttan yapılan harcama --> 100 TL");
        islemler.add("Karttan yapılan harcama -->  50 TL");
        islemler.add("Gönderilen Havale --------> 300 TL");
        islemler.add("ATM para çekme -----------> 200 TL");

        for (int i = Math.max(0, islemler.size() - 5); i < islemler.size(); i++) {
            System.out.println(islemler.get(i));
        }

        //for (int i = 0; i < 14; i++) {// i değeri kadar boş satır yazdıracak
        //    System.out.println();
        //}

        System.out.println("\nHesap bakiyeniz: " + bakiye + " TL'dir");
        System.out.println("Ana ekrana yönlendiriliyorsunuz, lütfen bekleyiniz..");
        Thread.sleep(1000);
        anaEkran();

    }

    private static void paraCekme() throws InterruptedException {

        System.out.println("Bakiyeniz: " + bakiye + " TL");

        if (bakiye > 0) {
            System.out.println("Lütfen çekmek istediğiniz miktarı giriniz: ");
            double cekilecekMiktar = scanner.nextDouble();

            if (cekilecekMiktar > bakiye) {
                System.out.println("Çekmek istediğiniz miktar bakiyenizin üzerindedir, " +
                                   "bakiyenizin üzerinde çekim yapamazsınız.");
                paraCekme(); // Hatalı miktar, tekrar miktar girişi iste
                return;
            } else {
                bakiye -= cekilecekMiktar;
                System.out.println("Çekim işlemi gerçekleştirildi, bakiyeniz: " + bakiye + " TL' dir");
            }

            System.out.println("Başka bir işlem yapmak ister misiniz? \n[1] Evet  \n[0] Hayır");
            int devamEtSecimi = scanner.nextInt();
            if (devamEtSecimi == 1) {
                anaEkran(); // Ana ekrana geri dön
            } else {
                cikisYap(); // Çıkış yap
            }

        } else {
            System.out.println("Hesabınızda yeterli bakiye bulunmadığından işleminiz yerine getirilememiştir");
        }
        Thread.sleep(3000);
        anaEkran();
    }


}

