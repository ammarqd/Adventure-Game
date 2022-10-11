import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {
    
    private JFrame frame = new JFrame();  
    private JPanel panel = new JPanel();
    private JLabel round = new JLabel();
    private JLabel playerName = new JLabel();
    private JLabel playerHp = new JLabel();
    private JLabel playerLv = new JLabel();
    private JLabel enemyName = new JLabel();
    private JLabel enemyHp = new JLabel();
    private JLabel enemyLv = new JLabel();
    private JTextArea gameDialogue = new JTextArea();

    public GUI(GameLogic game) {

        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0x222222));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));
        GridBagConstraints c = new GridBagConstraints();

        round.setText("Round " + (Enemy.getCount() + 1));
        round.setForeground(Color.white);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(round, c);

        playerName.setText(game.getPlayer().getName());
        playerName.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(playerName, c);

        playerHp.setText("HP " + game.getPlayer().getHp() + "/" + game.getPlayer().getMaxHp());
        playerHp.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(playerHp, c);
        
        playerLv.setText("Lv. " + game.getPlayer().getLevel());
        playerLv.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(playerLv, c);

        enemyName.setText(game.getEnemy()[0].getName());
        enemyName.setHorizontalAlignment(JLabel.RIGHT);
        enemyName.setForeground(Color.white);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(enemyName, c);
        
        enemyHp.setText("HP " + game.getEnemy()[0].getHp() + "/" + game.getEnemy()[0].getMaxHp());
        enemyHp.setHorizontalAlignment(JLabel.RIGHT);
        enemyHp.setForeground(Color.white);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(enemyHp, c);

        enemyLv.setText("Lv. " + game.getEnemy()[0].getLevel());
        enemyLv.setHorizontalAlignment(JLabel.RIGHT);
        enemyLv.setForeground(Color.white);
        c.gridx = 2;
        c.gridy = 2;
        panel.add(enemyLv, c);

        JButton attackButton = new JButton("Attack");
        attackButton.setBackground(new Color(0x333333));
        attackButton.setForeground(Color.white);
        attackButton.setFocusable(false);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.insets = new Insets(20,0,0,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(attackButton, c);

        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                game.battle();
        }});

        JButton magicButton = new JButton("Heal");
        magicButton.setBackground(new Color(0x333333));
        magicButton.setForeground(Color.white);
        magicButton.setFocusable(false);
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.insets = new Insets(20,5,0,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(magicButton, c);

        magicButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (game.getPlayer().isAlive() && game.getEnemy()[Enemy.getCount()].isAlive()) {
                    gameDialogue.setText("");
                    print(game.getPlayer().cast());
                    game.enemyTurn();
                    update(game);
                }
        }});

        JButton talkButton = new JButton("Talk");
        talkButton.setBackground(new Color(0x333333));
        talkButton.setForeground(Color.white);
        talkButton.setFocusable(false);
        c.gridx = 2;
        c.gridy = 3;
        c.weightx = 1;
        c.insets = new Insets(20,5,0,0);
        panel.add(talkButton, c);
        c.fill = GridBagConstraints.HORIZONTAL;

        talkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (game.getPlayer().isAlive() && game.getEnemy()[Enemy.getCount()].isAlive()) {
                    gameDialogue.setText(game.getPlayer().talk());
                    gameDialogue.append("\n" + game.getEnemy()[Enemy.getCount()].talk());
                }
        }});

        gameDialogue.setText("A " + game.getEnemy()[0].getName() + " has appeared!" + "\n");
        gameDialogue.setLineWrap(true);
        gameDialogue.setWrapStyleWord(true);
        gameDialogue.setBackground(new Color(0x333333));
        gameDialogue.setForeground(Color.white);
        gameDialogue.setBorder(new EmptyBorder(10, 10, 10, 10));
        gameDialogue.setEditable(false);
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 1;
        c.weighty = 1;
        c.ipady = 80;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(gameDialogue, c);

        frame.add(panel);
        frame.setTitle("Adventure Game");
        frame.setSize(400,400);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }  

    public void print(String message) {
        gameDialogue.append(message + "\n");
    }

    public void clear() {
        gameDialogue.setText("");
    }

    public void update(GameLogic game, Entity enemy) {
        round.setText("Round " + (Enemy.getCount() + 1));
        playerHp.setText("HP " + game.getPlayer().getHp() + "/" + game.getPlayer().getMaxHp());
        playerLv.setText("Lv. " + game.getPlayer().getLevel());
        enemyName.setText(enemy.getName());
        enemyHp.setText("HP " + enemy.getHp() + "/" + enemy.getMaxHp());
        enemyLv.setText("Lv. " + enemy.getLevel());
    }

    public void update(GameLogic game) {
        playerHp.setText("HP " + game.getPlayer().getHp() + "/" + game.getPlayer().getMaxHp());
    }
    
}  