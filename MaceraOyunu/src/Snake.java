import java.util.Random;
public class Snake extends Obstacle{
    private static Random r = new Random();
    public Snake() {
        super(4,"Yılan",0,12,0);
        int damage = r.nextInt(4) + 3;
        super.setDamage(damage);
    }
}
