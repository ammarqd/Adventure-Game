import java.util.Scanner;

/* ***************************************
  @author    Ammar Qadir
  @date      19 April 2022
  @version   1
    
  A game where you travel as an 
  adventurer fighting 5 enemies 
  in a dark fantasy world.
   ****************************************/

public class Main {

    private static GUI gameGUI;
    private static GameLogic game = new GameLogic();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your player name.");
        String name = scanner.nextLine(); 
        game.getPlayer().SetName(name);
        game.initEnemyDatabase(); // Intialises the main enemy data from a txt file
        gameGUI = new GUI(game);
    }

    // Helper method that interacts with the GUI for printing on textarea
    //
    public static void print(String message) {
        gameGUI.print(message);
    }

    // Helper method that interacts with the GUI to clear text on textarea
    //
    public static void clear() {
        gameGUI.clear();
    }

    // Helper method that updates GUI player and enemy stats during battle
    //
    public static void update() {
        gameGUI.update(game);
    }
    
    public static void update(Entity enemy) {
        gameGUI.update(game, enemy);
    }

}
