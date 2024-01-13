import java.util.Random;

public class Mine extends BattleLoc{
    String randomAward = randomAward();
    public Mine(Player player) {

        super(player, "Maden", new Snake(), Mine.randomAward(),5);
    }
    public static String randomAward(){
        Random r = new Random();
        int randomAward = r.nextInt(100);
        if (randomAward < 15){
            int randomWeapon = r.nextInt(100);
            if (randomWeapon < 20){
                return "Tüfek";
            }else if (randomWeapon <50){
                return "Kılıç";
            }else {
                return "Tabanca";
            }

        } else if (randomAward <30){
            int randomArmor = r.nextInt(100);
            if(randomArmor < 20){
                return "Ağır Zırh";
            }else if (randomArmor < 50){
                return "Orta Zırh";
            }else {
                return "Hafif Zırh";
            }

        } else if (randomAward < 55){
            int randomMoney = r.nextInt(100);
            if(randomMoney < 20){
                return "10";
            }else if (randomMoney <50){
                return "5";
            }else {
                return "1";
            }

        }else{
            return null;
        }
    }
}
