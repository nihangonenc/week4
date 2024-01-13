import java.util.Scanner;
public class Game {
    private Scanner input = new Scanner(System.in);
    public void Start(){
        System.out.println("Macera Oyununa Hoş Geldiniz!");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın "+ player.getName() + " bu karanlık ve sisli adaya hoş geldiniz!");
        player.selectChar();
        Location location = null;
        Location safeHouse= new SafeHouse(player);
        Location toolStore = new ToolStore(player);
        Location cave = new Cave(player);
        Location forest= new Forest(player);
        Location river = new River(player);
        Location mine = new Mine(player);

        while(true){
            player.printInfo();
            System.out.println("--------------BÖLGELER--------------");
            System.out.println("1-Güvenli Ev: Burası sizin için güvenli, düşman yok.");
            System.out.println("2-Eşya Dükkanı: Silah veya zırh satın alabilirsiniz.");
            System.out.println("3-Mağara: Mağaraya gidersen karşına zombi çıkacak, ödül olarak yemek kazanabilirsin.");
            System.out.println("4-Orman: Ormana gidersen karşına vampir çıkacak, ödül olarak odun kazanabilirsin.");
            System.out.println("5-Nehir: Nehire gidersen karşına ayı çıkacak, ödül olarak su kazanabilirsin.");
            System.out.println("6-Maden: Madene gidersen karşına yılan çıkacak, ödül ise sürpriz.");
            System.out.println("0-Çıkış Yap: Oyunu sonlandır.");

            System.out.print("Gitmek istediğiniz bölgeyi seçiniz: " );
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = safeHouse;
                    break;
                case 2:
                    location = toolStore;
                    break;
                case 3:
                    location = cave;
                    break;
                case 4:
                    location = forest;
                    break;
                case 5:
                    location = river;
                    break;
                case 6:
                    location = mine;
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge seçiniz.");
            }
            if(location == null){
                System.out.println("Bu karanlık ve sisli adadan çabuk vazgeçtiniz.");
                break;
            }

            if(!location.onLocation()){
               System.out.println("Game Over");
               break;
            }

        }

    }

}