import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Entity{

    private static int count = 0;

    public Enemy(String name, int hp, int attack, int level, Spell spell) {
        super(name, hp, attack, level, spell);
    }

    // polymorphic attack range method
    //
    public int attackRange() {
        return ThreadLocalRandom.current().nextInt(getAttack() - 2, getAttack() + 2);
    }

    // polymorphic cast method
    //
    public String cast() {
        return "The enemy casts " + getSpell().getAction() + ", take " + getSpell().getPotency() + " damage.";
    }

    // polymorphic talk method
    //
    public String talk() {
        return "The " + this.getName() + " is not amused.";
    }
    
    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Enemy.count = count;
    }

}