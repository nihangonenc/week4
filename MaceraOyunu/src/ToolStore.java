public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Mağazaya hoş geldiniz.");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1- Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Çıkış Yap");
            System.out.print("Seçiminiz: ");
            int selectCase = Location.input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz bir seçenek girdiniz. Tekrar seçiniz: ");
                selectCase = Location.input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz");
                    showMenu = false;
                    break;

            }
        }
        return true;
    }
    public void printWeapon(){
        System.out.println("Silahlar");
        for (Weapon w :Weapon.weapons()){
            System.out.println(w.getId() + "-" + w.getName() + " Para : " + w.getPrice() + " Hasar: " + w.getDamage());
        }
        System.out.println("0- Çıkış Yap");
    }
    public void buyWeapon(){
        System.out.print("Bir silah seçiniz: ");

        int selectWeaponID = input.nextInt();
        while(selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.print("Geçersiz bir seçenek girdiniz. Tekrar seçiniz: ");
            selectWeaponID = input.nextInt();
        }
        if(selectWeaponID != 0){
            Weapon selectWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectWeapon != null){
                if(selectWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli bakiyeniz bulunmamaktadır.");
                }else {
                    System.out.println(selectWeapon.getName() + " silahını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan bakiyeniz: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectWeapon);
                }
            }
        }
    }
    public void printArmor(){
        System.out.println("Zırhlar");
        for(Armor a : Armor.armors()){
            System.out.println(a.getId() + "-" + a.getName() + " Para: " + a.getPrice() + " Engelleme : " + a.getBlock());
        }
        System.out.println("0- Çıkış Yap");
    }
    public void buyArmor(){
        System.out.print("Bir zırh seçiniz: ");

        int selectArmorID = input.nextInt();
        while(selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.print("Geçersiz bir seçenek girdiniz. Tekrar seçiniz: ");
            selectArmorID = input.nextInt();
        }
        if(selectArmorID != 0){
            Armor selectArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectArmor != null){
                if(selectArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli bakiyeniz bulunmamaktadır.");
                }else {
                    System.out.println(selectArmor.getName() + " zırhını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan bakiyeniz: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectArmor);
                }
            }

        }
    }
}
