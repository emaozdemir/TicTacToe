import java.util.Scanner;
import java.util.Random;
/*
    Oyun, bir oyuncunun bilgisayara karşı Üçlü Karşılıklı (Tic-Tac-Toe) oynadığı bir oyun olarak tasarlanmalıdır.
    Oyun, 3x3 boyutunda bir ızgaradan oluşan bir tahtada oynanmalıdır.
    Oyuncu, kendi sembolü olarak 'X' veya 'O' seçer. Oyun, kimin başlayacağını rastgele seçmelidir.
    Oyuncu ve rakip sırayla boş bir kareye sembollerini koyar. Bir oyuncu üç aynı sembolü (dikey, yatay veya çapraz olarak) bir sıraya getirirse, oyunu kazanır.
    Oyun tahtası dolarsa ve kimse üçlü sıra yapamamışsa, oyun berabere sonuçlanır.
    Oyun, oyuncuya yeniden başlama imkânı sunar ve oyuncunun kazandığı oyun sayısını takip eder.

    not:static kelimesi, değişkenin veya metodun sınıf düzeyinde (class level)
     olduğunu belirtir; bu durumda, nesne (instance) oluşturmadan doğrudan kullanabiliriz.
 */

 public class TicTacToe {
    static char[][] tahta = new char[3][3];//Oyun, 3x3 boyutunda bir ızgaradan oluşan bir tahtada oynanmalıdır.tanımlandıgı yer
    static char symbol1;
    static char symbol2;
    static Random random = new Random();//
    static Scanner scanner = new Scanner(System.in);//kullanıcıdan kolsoldan bilgi almak 

    public static void main(String[] args) {
        System.out.println("X veya O seçin");
        symbol1 = scanner.next().charAt(0);
        symbol2 = (symbol1 == 'X') ? 'O' : 'X';//ternary
        /*
            if (symbol1 == 'X') {
            symbol2 = 'O';
            } else {
            symbol2 = 'X';
            }
         */

        initializeBoard();//Tahtayı başlatmak için her hücreyi boş (' ') karakteriyle doldurur.
        printBoard();//Tahtayı ekrana yazdırır, böylece oyuncu hamleleri görebilir.

        // Oyun sırası rastgele belirleniyor
        boolean playerTurn = random.nextBoolean();
        System.out.println((playerTurn ? "Oyuncu" : "Bilgisayar") + " başlıyor!");

        // Oyun döngüsü
    while (true) {
        if (playerTurn) { // Oyuncunun hamlesi
            System.out.println("Hamle yapın: Satır ve Sütun (0-2 arası)");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            // Geçerli bir hamle yapılana kadar hamle istenir
            if (makeMove(row, col, symbol1)) { 
                printBoard();
                if (checkWinner(symbol1)) { // Oyuncunun kazanıp kazanmadığını kontrol eder
                    System.out.println("Tebrikler, kazandınız!");
                    break;
                }
                playerTurn = false; // Sıra bilgisayara geçer
            }
        } else { // Bilgisayarın hamlesi
            System.out.println("Bilgisayarın hamlesi...");
            int row, col;
            // Geçerli bir hamle bulana kadar döngü devam eder
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (!makeMove(row, col, symbol2));
            printBoard();
            if (checkWinner(symbol2)) { // Bilgisayarın kazanıp kazanmadığını kontrol eder
                System.out.println("Bilgisayar kazandı.");
                break;
            }
            playerTurn = true; // Sıra oyuncuya geçer
        }

        if (isDraw()) { // Beraberlik durumunu kontrol eder
            System.out.println("Oyun berabere!");
            break;
        }
    }
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tahta[i][j] = ' ';
    }

    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print("|" + tahta[i][j]);
            System.out.println("|");
        }
    }

    static boolean makeMove(int row, int col, char symbol) {//Bir hamle yapar. Eğer belirtilen hücre boşsa, oyuncunun
        // sembolünü yerleştirir; değilse, hata verir.
        // Satır ve sütun sınır içinde mi kontrolü
    if (row < 0 || row > 2 || col < 0 || col > 2) { // Bu satır sınır kontrolünü yapar
        System.out.println("Lütfen 0-2 arası bir sayı girin.");
        return false;
    }
    if (tahta[row][col] == ' ') { // Eğer hücre boşsa
        tahta[row][col] = symbol; // Sembolu hücreye yerleştirir
        return true; // Başarılı olduğunu belirtmek için true döner
    } else {
        System.out.println("Bu hücre dolu, başka bir hücre seçin.");
    }
    return false; // Başarısız olduğunu belirtmek için false döner
    }

    static boolean checkWinner(char symbol) {//Kazanma durumunu kontrol eder. Yatay, dikey veya çapraz bir şekilde
        // üç aynı sembol yan yana geldiğinde kazanan ilan edilir.
        for (int i = 0; i < 3; i++) {
            // Satırları ve sütunları kontrol et
            if (tahta[i][0] == symbol && tahta[i][1] == symbol && tahta[i][2] == symbol) return true;//return metod hemen sonlanır.
            if (tahta[0][i] == symbol && tahta[1][i] == symbol && tahta[2][i] == symbol) return true;
            // Eğer herhangi bir yerde üçlü bir sembol dizilimi varsa true döner ve oyun kazanılmış olur; aksi durumda false döner ve oyun devam eder.
        }
        // Çaprazları kontrol et
        return (tahta[0][0] == symbol && tahta[1][1] == symbol && tahta[2][2] == symbol) ||
               (tahta[0][2] == symbol && tahta[1][1] == symbol && tahta[2][0] == symbol);
    }

    static boolean isDraw() {// Beraberlik olup olmadığını kontrol eder. Tüm hücreler dolu ancak kazanan yoksa beraberlik ilan edilir.
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (tahta[i][j] == ' ') 
                return false;//// Eğer boş hücre varsa beraberlik yok
        return true;// // Boş hücre yoksa beraberlik var
    }
    /*
        printBoard(): Tahtayı ekrana yazar.
        makeMove(): Belirtilen konuma hamle yapmaya çalışır, başarılı olursa true, yoksa false döner.
        checkWinner(): Bir oyuncunun kazanıp kazanmadığını kontrol eder.
        isDraw(): Beraberlik durumunu kontrol eder.
        initializeBoard(): Oyun tahtasını temizler.


        tahta, bir 3x3 matris

            ' ' | ' ' | ' '
            ' ' | ' ' | ' '
            ' ' | ' ' | ' '

            tahta[0][0] sol üst hücre
            tahta[1][1] orta hücre
            tahta[2][2] sağ alt hücre

            Bu çapraz şu şekilde görünür:

           
            Kodu kopyala
            X |   |  
              | X |  
              |   | X

     */
}