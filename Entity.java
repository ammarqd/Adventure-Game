public abstract class Entity {

    private String name;
    private int hp;
    private int maxHp = hp;
    private int attack;
    private int level;
    private Spell spell;

    public Entity(String name, int hp, int attack, int level, Spell spell) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.level = level;
        this.spell = spell;
    }

    public Entity(int hp, int attack, int level, Spell spell) {
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.level = level;
        this.spell = spell;
    }

    public void setAttack(int attack) {
        this.attack += attack;
    }

    public void damageHp(int dmg) {
        this.hp -= dmg;
        if (this.hp < 0) 
            this.hp = 0;
    }

    public void healHp(int hp) {
        this.hp += hp;
        if (this.hp > maxHp) {
            this.hp = getMaxHp();
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp += maxHp;
    }

    public void setLevel(int level) {
        this.level += level;
    }

    public String getName() {
        return name;
    }
    
    public void SetName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
    }

    public Spell getSpell() {
        return spell;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    /* Polymorphic abstract methods*/

    public abstract int attackRange();

    public abstract String cast();
    
    public abstract String talk();

}