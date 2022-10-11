import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class GameLogic implements Serializable {

    private Entity[] enemy = new Enemy[5];
    private Entity player = new Player();

    // Initialises the enemy data from a txt file and stores it in an array for use in the program.
    //
    public void initEnemyDatabase() {
        try {
            BufferedReader enemyFile = new BufferedReader(new FileReader("enemyDatabase.txt"));

            for (int i = 0; i < enemy.length; i++) {

                String line = enemyFile.readLine();
                String[] splitArray = line.split(", "); // Regex to parse comma delimited strings in txt file
                this.enemy[i] = enemyStats(splitArray);
            }
            enemyFile.close();
        } catch (IOException e) {
            Main.print("Error, file not found.");
            e.printStackTrace();
        }
    }

    // Creates enemy objects with the given stats parsed from the txt file
    //
    public Enemy enemyStats(String[] splitArray) {
        String name = splitArray[0];
        int hp = Integer.parseInt(splitArray[1]);
        int attack = Integer.parseInt(splitArray[2]);
        int level = Integer.parseInt(splitArray[3]);
        Spell spell = new Spell(splitArray[4], Integer.parseInt(splitArray[5]));
        Enemy enemy = new Enemy(name, hp, attack, level, spell);
        return enemy;
    }

    // Main game battle loop initiated through GUI attack button event listener
    //
    public void battle() {

        if (player.isAlive() && enemy[Enemy.getCount()].isAlive()) {

            int playerDamage = player.attackRange();
            enemy[Enemy.getCount()].damageHp(playerDamage);
            Main.clear();
            Main.print("You attack for " + playerDamage + " damage.");

        enemyTurn();

        // For retrieving a new enemy if current enemy has died
        //
        } else if (player.isAlive() && !enemy[Enemy.getCount()].isAlive() && Enemy.getCount() < enemy.length) {
            levelUp();
            Enemy.setCount(Enemy.getCount() + 1);
            Main.clear();
            Main.print("A " + enemy[Enemy.getCount()].getName() + " has appeared!");
        }
        Main.update(enemy[Enemy.getCount()]);        
    }

    public void enemyTurn() {
        if (enemy[Enemy.getCount()].isAlive()) {
            int enemyDamage = enemy[Enemy.getCount()].attackRange();
            player.damageHp(enemyDamage);
            Main.print("The " + enemy[Enemy.getCount()].getName() + " attacks for " + enemyDamage + " damage.");
            if (player.getHp() <= 0) {
                Main.print("\nYou died.");
            }
        } else {
            Main.print("You defeated the " + enemy[Enemy.getCount()].getName() + ".");
        }
    }

    public void levelUp() {
        //player.setMaxMp(player.getMaxMp() + 1);
        //player.setMp(player.getMaxMp());
        player.setLevel(1);
        player.setAttack(2);
        player.setMaxHp(10);
        player.healHp(player.getMaxHp() - player.getHp());
        /*if (player.getLevel() == 10)
            this.weapon = new Weapon("Claymore", 5);
        else  if (player.getLevel() == 20)
            player.weapon = new Weapon("Zweihander", 10);
        else if (player.getLevel() == 30)
            this.weapon = new Weapon("Excalibur", 20);*/
    }

    public Entity getPlayer() {
        return this.player;
    }

    public Entity[] getEnemy() {
        return this.enemy;
    }

}