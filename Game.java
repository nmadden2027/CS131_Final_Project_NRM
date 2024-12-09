import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * A text-based adventure game with multiple rooms, combat, and player progression.
 * 
 * @author Niko Madden
 */
public class Game {
 /**
 * Initializes the game window, sets up the initial game state, and creates the game map.
 * Configures the main window, title panel, and start button panel.
 */    
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, playerPanel, partyButtonPanel, partyTextPanel, classPanel, mapPanel;
    JLabel coinsLabelNumber, coinsLabel;
    static JPanel choiceButtonPanel;
    JLabel titleName, hpLabel;
    static JLabel hpLabelNumber;

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 40);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton;
    static JButton choice1;
    static JButton choice2;
    static JButton choice3;
    JButton barbarian;
    JButton wizard;
    JButton samurai;
    public static JTextArea mainTextArea;
    JTextArea partyTextArea;
    int coins;
    int playerHP;
    int playerAttack;
    int playerArmor;
    String weapon;
    characterParent player;
    TitleScreenHandler tsHandler = new TitleScreenHandler(); 
    PartyScreenHandler psHandler = new PartyScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    
    JFrame windowMap; 
    JButton[] mapButtons;
    Room[][] map;
    int playerX = 0, playerY = 0; // Starting position
    JLabel playerPositionLabel;
    Inventory inventory = new Inventory(10);
    boolean[][] visited = new boolean[5][5];  // To track if the player has visited a room



    public Game() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);  
        window.setVisible(true);
        con = window.getContentPane();

        setupTitlePanel();  
        setupStartButtonPanel(); 
        player = new characterParent();
        // Initialize the map
        map = new Room[5][5];
        map[0][0] = new SafeRoom();
        map[0][1] = new CombatRoom(1);
        map[0][2] = new CombatRoom(1);
        map[0][3] = new CombatRoom(2);
        map[0][4] = new CombatRoom(3);
        map[1][0] = new CombatRoom(1);
        map[1][1] = new CombatRoom(1);
        map[1][2] = new CombatRoom(1);
        map[1][3] = new SafeRoom();
        map[1][4] = new CombatRoom(2);
        map[2][0] = new CombatRoom(1);
        map[2][1] = new CombatRoom(1);
        map[2][2] = new CombatRoom(2);
        map[2][3] = new CombatRoom(3);
        map[2][4] = new CombatRoom(3);
        map[3][0] = new CombatRoom(2);
        map[3][1] = new SafeRoom();
        map[3][2] = new CombatRoom(2);
        map[3][3] = new CombatRoom(3);
        map[3][4] = new SafeRoom("This is the last safe room you will go through before you meet the final boss.\n", "This will be your last chance to buy items or upgrade before you end the game.");
        map[4][0] = new CombatRoom(3);
        map[4][1] = new CombatRoom(3);
        map[4][2] = new CombatRoom(3);
        map[4][3] = new SafeRoom("This is the last safe room you will go through before you meet the final boss.\n", "This will be your last chance to buy items or upgrade before you end the game.");
        map[4][4] = new CombatRoom("As you enter there is a loud laugh in the distance\n", "The torches in the room all of a sudden light up and your face to face with the devil himself",4);    
    }
    // set up title panel
    private void setupTitlePanel() {
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);  
        titleNamePanel.setBackground(Color.black);     
        titleNamePanel.setLayout(null);        
        
        titleName = new JLabel("The Treasure of 100 Kings");
        titleName.setForeground(Color.white);           
        titleName.setFont(titleFont);                   
        titleName.setBounds(50, 30, 500, 80);  
        titleNamePanel.add(titleName);
        con.add(titleNamePanel);  
    }
    // set up start button panel
    private void setupStartButtonPanel() {
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
        startButtonPanel.setLayout(null);  
        
        startButton = new JButton("Start Game");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.black);
        startButton.setFont(normalFont);
        startButton.setBounds(0, 0, 200, 100);  
        startButton.addActionListener(tsHandler);
        
        startButtonPanel.add(startButton);
        con.add(startButtonPanel);       
    }
    // Party screen set up where you choose character class
    public void createPartyScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        
        partyTextPanel = new JPanel();
        partyTextPanel.setBounds(100, 100, 600, 250);
        partyTextPanel.setBackground(Color.black);
        con.add(partyTextPanel);
        
        partyTextArea = new JTextArea("This is where you choose your character class.\nStarting Stats\nBarbarian: HP:50 Attack Damage:10\nWizard: HP:30 Attack Damage:15\nSamurai: HP:40 Attack Damage:12");
        partyTextArea.setBackground(Color.black);
        partyTextArea.setForeground(Color.white);
        partyTextArea.setFont(normalFont);
        partyTextArea.setLineWrap(true);
        partyTextArea.setBounds(100, 100, 600, 250);
        partyTextPanel.add(partyTextArea);
        
        partyButtonPanel = new JPanel();
        partyButtonPanel.setBounds(250, 350, 300, 150);
        partyButtonPanel.setBackground(Color.black);
        con.add(partyButtonPanel);
        partyButtonPanel.setLayout(new GridLayout(3,1));
        
        barbarian = new JButton("Barbarian Class");
        barbarian.setBackground(Color.black);
        barbarian.setForeground(Color.black);
        barbarian.setFont(normalFont);
        partyButtonPanel.add(barbarian);
        barbarian.addActionListener(psHandler);
        barbarian.addActionListener(choiceHandler);
        barbarian.setActionCommand("b1");
        
        wizard = new JButton("Wizard Class");
        wizard.setBackground(Color.black);
        wizard.setForeground(Color.black);
        wizard.setFont(normalFont);
        partyButtonPanel.add(wizard);
        wizard.addActionListener(psHandler);
        wizard.addActionListener(choiceHandler);
        wizard.setActionCommand("w1");
        
        samurai = new JButton("Samurai Class");
        samurai.setBackground(Color.black);
        samurai.setForeground(Color.black);
        samurai.setFont(normalFont);
        partyButtonPanel.add(samurai);
        samurai.addActionListener(psHandler);
        samurai.addActionListener(choiceHandler);
        samurai.setActionCommand("s1");
    } 
    // Game screen set up where the rest of the game will take place
    private void createGameScreen() {
    	//Gets rid of everything previously set up
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        partyTextPanel.setVisible(false);
        partyTextArea.setVisible(false);
        partyButtonPanel.setVisible(false);
        
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);  
        mainTextArea.setWrapStyleWord(true);  
        mainTextPanel.add(mainTextArea);
        
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        con.add(choiceButtonPanel);
        choiceButtonPanel.setLayout(new GridLayout(3,1));
        
        choice1 = new JButton("Attack");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.black);
        choice1.setFont(normalFont);
        choiceButtonPanel.add(choice1);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");

        choice2 = new JButton("Open Inventory");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.black);
        choice2.setFont(normalFont);
        choiceButtonPanel.add(choice2);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        
        choice3 = new JButton("Open Map To Move");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.black);
        choice3.setFont(normalFont);
        choiceButtonPanel.add(choice3);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 6)); 
        con.add(playerPanel);

        hpLabel = new JLabel("HP: ");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);

        coinsLabel = new JLabel("Coins: ");
        coinsLabel.setFont(normalFont);
        coinsLabel.setForeground(Color.white);
        playerPanel.add(coinsLabel);

        coinsLabelNumber = new JLabel("0");
        coinsLabelNumber.setFont(normalFont);
        coinsLabelNumber.setForeground(Color.white);
        playerPanel.add(coinsLabelNumber);

        // Set initial room description
        mainTextArea.setText(map[0][0].getDescription() + " " + map[0][0].getExtraInfo());

        playerSetup();
        SafeRoom.safeRoomButtons();
    }
    // sets the player hp and weapon type at top of game screen
    private void playerSetup() {
        playerHP = player.hp;
        coins = player.coins;
        playerArmor = player.armorLevel;
        playerAttack = player.weaponLevel;
        hpLabelNumber.setText("" + playerHP);
        coinsLabelNumber.setText("" + player.coins);  
    }
 
    // handles all button actions
    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();

            // Character selection logic
            if(yourChoice.equals("b1")) {
                player = new Barbarian();
            }
            if(yourChoice.equals("w1")) {
                player = new Wizard();
            }
            if(yourChoice.equals("s1")) {
                player = new Samurai();
            }

            // Inventory Management
            if (yourChoice.equals("c2")) {
                mainTextArea.setText(inventory.displayInventory());
                choice1.setText("Use Health Potion");
                choice1.setActionCommand("Health");
                choice2.setText("Close Inventory");
                choice2.setActionCommand("Close");
                
            }

            // Map Navigation
            if(yourChoice.equals("c3")) {
                openMap();
            }

            // Combat Actions
            if(yourChoice.equals("c1")) {  
                handleAttack();
            }

            // Inventory Close
            if(yourChoice.equals("Close")) {
                choice1.setText("Attack");
                choice1.setActionCommand("c1");
                choice2.setText("Open Inventory");
                choice2.setActionCommand("c2");
                choice3.setText("Open Map");
                choice3.setActionCommand("c3");
            }

            // Store Menu
            if (yourChoice.equals("b")) {
                mainTextArea.setText("What do you want to buy? Health Potions are 200 coins each.");
                choice1.setText("Buy Health Potions");
                choice1.setActionCommand("h");
                choice2.setText("Upgrade");
                choice2.setActionCommand("u");
                choice3.setText("Back To Store");
                choice3.setActionCommand("Back");
            }

            // Buy Health Potion
            if (yourChoice.equals("h") && coins >= 200) {
                Item healthPotion = new Item("Health Potion", "Restores 15 HP", "Potion",15);
                coins = coins - 200;
                coinsLabelNumber.setText("" + coins);
                if (inventory.addItem(healthPotion)) {
                    mainTextArea.setText("You bought a Health Potion.");
                } else {
                    mainTextArea.setText("Inventory is full. Cannot add item.");
                }
                if (coins < 200) {
                	mainTextArea.setText("Not enough coins. Cannot add item.");
                }
            }

            // Back to Store
            if (yourChoice.equals("Back")) {
                SafeRoom.safeRoomButtons();
            }

            // Upgrade Menu
            if (yourChoice.equals("u")) {
                mainTextArea.setText("What do you want to upgrade?" + "\nUpgrading a weapon is 500 coins.\nUpgrading Armor is 700 coins.");
                choice1.setText("Weapon");
                choice1.setActionCommand("u1");
                choice2.setText("Armor");
                choice2.setActionCommand("u2");
                choice3.setText("Back To Store");
                choice3.setActionCommand("Back");
            }
            
            if(yourChoice.equals("u1")) {
            	if (coins >= 500) {
            		playerAttack = playerAttack + 1;
            		player.weaponLevel = playerAttack;
            		coins = coins - 500;
            		coinsLabelNumber.setText("" + coins);
            		mainTextArea.setText("Weapon upgraded to level " + player.weaponLevel + ". Your new attack damage is " + player.attack());
            	}
            	else {
            		mainTextArea.setText("Not enough coins");
            	}
            }
            if(yourChoice.equals("u2")) {
            	if (coins >= 700) {
            		playerArmor = playerArmor + 1;
                	player.armorLevel = playerArmor;
                	coins = coins - 700;
                	coinsLabelNumber.setText("" + coins);
                	mainTextArea.setText("Armor upgraded to level " + player.armorLevel + ". Your new hp is " + player.hp);
            	}
            	else {
            		mainTextArea.setText("Not enough coins");
            	}
            }

            // Leave Safe Room
            if(yourChoice.equals("l")) {
                mainTextArea.setText("You are now about to leave the safe room. Open you map and move on to a combat room.");
                choice1.setText("Attack");
                choice1.setActionCommand("c1");
                choice2.setText("Open Inventory");
                choice2.setActionCommand("c2");
                choice3.setText("Open Map");
                choice3.setActionCommand("c3");
            }
            
            if (yourChoice.equals("s2")) {
            	player.health();
            	mainTextArea.setText("Your character is healed");
            }
            
            // Game Control
            if (yourChoice.equals("quit")) {
                quitGame();
            }
            if (yourChoice.equals("restart")) {
                restartGame();
            }
            if (yourChoice.equals("Health")) {
                Item healthPotion = inventory.getItem(0);  // Assume this retrieves the first item from inventory
                if (healthPotion != null) {
                    String result = healthPotion.use(player);  // Apply the effect to the player
                    mainTextArea.setText(result);  // Display the result
                    inventory.removeItem(0);  // Remove item from inventory
                } else {
                    mainTextArea.setText("No items to use.");
                }
            }
        }
    }

    // gets rid of party screen to go to game screen
    private class PartyScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }
    // gets rid of title screen to go to party screen
    private class TitleScreenHandler implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            createPartyScreen();
        }
    }
    // Handles opening the map
    private void openMap() {
        if (windowMap == null) {
            windowMap = new JFrame("Map");
            windowMap.setSize(600, 600);
            windowMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            windowMap.setLayout(new GridLayout(5, 5));  // 5x5 grid to show all rooms
            // makes a button for each room
            mapButtons = new JButton[25];  

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int index = i * 5 + j;
                    mapButtons[index] = new JButton();

                    final int roomX = i;  // Current row (X)
                    final int roomY = j;  // Current column (Y)

                    mapButtons[index].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Check if the target room is adjacent to the current room
                            if (isAdjacent(roomX, roomY)) {
                                movePlayer(roomX, roomY);  // Move player to new room
                            } 
                        }
                    });

                    // Set the button text and color based on the room type and adjacency
                    updateRoomButton(i, j, index);
                    windowMap.add(mapButtons[index]);
                }
            }
        }
        updateMapDisplay();
        windowMap.setVisible(true);
    }
    // Sets current room and handled most stuff until I added update room button to make more of the changes
    private void updateMapDisplay() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                Room currentRoom = map[i][j];

                // Only enable adjacent rooms
                if (isAdjacent(i, j)) {
                    mapButtons[index].setEnabled(true);  // Enable adjacent rooms
                } else {
                    mapButtons[index].setEnabled(false);  // Disable non-adjacent rooms
                }

                // Update button text and color based on room type and position
                updateRoomButton(i, j, index);
            }
        }
    }
    private void updateRoomButton(int i, int j, int index) {
        Room currentRoom = map[i][j];
        String roomType = "Unknown Room";  // Default room type

        // If the room is unexplored, show "?"
        if (!visited[i][j]) {
            mapButtons[index].setText("?");
            mapButtons[index].setEnabled(true);  // Make it clickable
        } else {
            // If it's explored, set the room type (SafeRoom, CombatRoom, etc.)
            if (currentRoom instanceof SafeRoom) {
                roomType = "Safe Room";
            } else if (currentRoom instanceof CombatRoom) {
                roomType = "Combat Room";
            }

            // Mark the current room
            if (i == playerX && j == playerY) {
                mapButtons[index].setText("You Are Here");
                mapButtons[index].setBackground(Color.YELLOW);  // Highlight the current room
            } else {
                // For the starting room (0,0)
                if (i == 0 && j == 0) {
                    // If player is no longer in starting room, change to Safe Room
                    if (playerX != 0 || playerY != 0) {
                        mapButtons[index].setText("Safe Room");
                    }
                } else {
                    mapButtons[index].setText(roomType);  // Set the room type text for explored rooms
                }
            }

            visited[0][0] = true;
            mapButtons[index].setEnabled(true);  // Enable the button after it's explored
        }
    }

    // Modify the movePlayer method to set hasLeftStartingRoom
    private void movePlayer(int x, int y) {
        if (isValidRoom(x, y)) {
            // Check if leaving the starting room
            if (playerX == 0 && playerY == 0) {
            }

            playerX = x;
            playerY = y;

            Room currentRoom = map[x][y];

            // Mark the current room as visited
            visited[x][y] = true;

            // Update the main text area with room description
            mainTextArea.setText(currentRoom.getDescription() + " " + currentRoom.getExtraInfo());

            // If it's a safe room, update buttons
            if (currentRoom instanceof SafeRoom) {
                SafeRoom.safeRoomButtons();  // Change buttons for safe room
            }

            updateMapDisplay();  // Update the map after the player moves
        }

        windowMap.setVisible(false);  // Close the map window
    }
    // Gets rid of out of bounds problems I had early on in setting up my map
    private boolean isValidRoom(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }
    // Makes it so the user can't jump to any room they want
    private boolean isAdjacent(int x, int y) {
        return (Math.abs(playerX - x) == 1 && playerY == y) || (Math.abs(playerY - y) == 1 && playerX == x);
    }
    private void handleAttack() {
        // Get the current room the player is in
        Room currentRoom = map[playerX][playerY];

        if (currentRoom instanceof CombatRoom) {
            CombatRoom combatRoom = (CombatRoom) currentRoom;

            // Check if there is still an enemy to fight
            if (!combatRoom.isEnemyDead()) {
                // Perform the attack
                combatRoom.enemyAttack(player);

                // Update the player HP in the Game class
                playerHP = player.hp;
                hpLabelNumber.setText("" + playerHP);

                // Check if the enemy is now dead after the attack
                if (combatRoom.isEnemyDead()) {
                    Enemy enemy = combatRoom.getEnemy();
                        coins += 200;
                        player.coins += 200;
                        coinsLabelNumber.setText("" + coins);
                        Game.mainTextArea.setText("You defeated the enemy! You earned " + 200 + " coins.");
                    } 
                }

                // Check if player's health is 0 or less after the attack
                if (playerHP <= 0) {
                    gameOver(); // Trigger the game over screen
                }
            } else {
                Game.mainTextArea.setText("The enemy is already dead.");
            }
        }

    private void gameOver() {
        playerHP = 0;  // Explicitly set HP to 0
        hpLabelNumber.setText("0");  // Update HP label
        mainTextArea.setText(player.death());
        
        choice1.setText("Quit Game");
        choice1.setActionCommand("quit");
        choice2.setText("Restart Game");
        choice2.setActionCommand("restart");
        choice3.setVisible(false);

        choice1.setEnabled(true);
        choice2.setEnabled(true);
    }
 // Quit the game
    private void quitGame() {
        // Close the game window and exit
        System.exit(0);  // This will exit the program
    }

    // Restart the game (you can reset variables and screens)
    private void restartGame() {
        // Reset player stats, map, and all necessary variables
        playerHP = player.hp;  
        playerX = 0;  
        playerY = 0;  
        visited = new boolean[5][5];  
        map = new Room[5][5];  
      

        // Clear the map window and return to the game screen
        windowMap.setVisible(false);
        createGameScreen();  // Or reinitialize the game screen
        mainTextArea.setText("You have returned to the game!");
    }
}
