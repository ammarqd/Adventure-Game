import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Entity {

    private int mp;
    private int maxMp;
    private Weapon weapon = new Weapon("Bronze Sword", 1);
    private ArrayList<String> inventory = new ArrayList<>();

    public Player() {
        super(30, 7, 1, new Spell("cure", 20));
        this.maxMp = 5;
        this.mp = maxMp;
    }

    public int getMp() {
        return mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setMaxMp(int mp) {
        this.maxMp = mp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    // polymorphic attack range method
    //
    public int attackRange() {
        return ThreadLocalRandom.current().nextInt(getAttack() - 2, getAttack() + 2) + getWeapon().getDamage();
    }

    // polymorphic player cast method
    //
    public String cast() {
        if (mp > 0) {
            int currentHp = getHp();
            healHp(getSpell().getPotency());
            this.mp--;
            return "You cast " + getSpell().getAction() + ", you heal " + (getHp() - currentHp) + " HP.";
        } else 
            return "You have no remaining MP.";
    }

    // polymorphic talk method
    //
    public String talk() {
        return "You attempt to talk it out.";
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }
    

}