import java.sql.SQLOutput;
import java.util.Random;
public abstract class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private boolean isAwardTaken;
    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
        this.isAwardTaken = false;
    }
    @Override
    public boolean onLocation() {
        if (isAwardTaken == true) {
            System.out.println("Daha önce buraya geldiniz.");
            System.out.println();
            return true;
        }
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şu an buradasınız: " + this.getName());
        System.out.println("Dikkatli ol! Burada " +  obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor.");
        System.out.print("Savaşmak için S, Kaçmak için ise K'yi seçiniz: ");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + " düşmanlarının hepsini yendiniz.");
            if (this.award.equals("Food") && !this.getPlayer().getInventory().isFood()){
                System.out.println(this.award + " kazandınız.");
                this.getPlayer().getInventory().setFood(true);

            }else if (this.award.equals("Water") && !this.getPlayer().getInventory().isWater()){
                System.out.println(this.award + " kazandınız.");
                this.getPlayer().getInventory().setWater(true);

            } else if (this.award.equals("Firewood") && !this.getPlayer().getInventory().isFirewood()){
                System.out.println(this.award + " kazandınız.");
                this.getPlayer().getInventory().setFirewood(true);
            } else {
                System.out.println(this.award + " kazandınız.");
                this.getPlayer().getInventory().setRandomAward(true);
            }
            return true;
        } else {
            System.out.println("Savaştan kaçıldı.");
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz!");
            return false;
        }
        return true;
    }
    public void makeAwardTaken() {
        this.isAwardTaken = true;
    }
    public boolean combat(int obsNumber){
        for ( int i = 1; i <= obsNumber; i++ ){
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            System.out.println("Savaş Başlıyor");
            playerStats();
            obstacleStats(i);

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                Random random = new Random();
                int whoStarts = random.nextInt(2);
                if(whoStarts == 0){
                    System.out.println();
                    System.out.println("İlk hamleyi siz yapıyorsunuz.");
                    System.out.print("Vurmak için V, kaçmak için K yazınız: ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if( selectCombat.equals("V")){
                        System.out.println("Siz vurdunuz.");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }else {
                        return false;
                    }

                }else {
                    System.out.println();
                    System.out.println(this.getObstacle().getName() + " ilk hamleyi yapıyor.");
                    if (this.getObstacle().getHealth() > 0){
                        System.out.println(this.getObstacle().getName() + " size vurdu");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz.");
                System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel Paranız: " + this.getPlayer().getMoney());
                this.makeAwardTaken();
            }else {
                return false;
            }
        }
        return true;
    }
    public void afterHit(){
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı: " + this.getObstacle().getHealth());
        System.out.println("---------------------------");
    }
    public void playerStats(){
        System.out.println("---------------------------");
        System.out.println("Oyuncu Değerleri");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());

    }
    public void obstacleStats(int i){
        System.out.println("---------------------------");
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri");
        System.out.println("Sağlık: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödül: " + this.getObstacle().getAward());
    }
    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1 ;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
