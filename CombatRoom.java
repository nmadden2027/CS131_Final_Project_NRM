public class CombatRoom extends Room {

    private Enemy enemy;
    protected static int level;

    // Constructor for level-based initialization
    public CombatRoom(int level) {
        this.level = level;
        this.enemy = new Enemy(level);
        
        // Null check before accessing enemy methods
        if (this.enemy != null) {
            description = "This is a level " + level + " room. A " + enemy.getName() + " stares you down ready to fight.";
        } else {
            description = "This is a level " + level + " room. No enemy present.";
        }
        extraInfo = ""; // Empty extra info for now
    }

    // Constructor with custom description and extra info
    public CombatRoom(String description, String extraInfo, int level) {
        super(description, extraInfo);
        this.level = level;
        this.enemy = new Enemy(level);
    }

    // Method to handle enemy attack on the player
 // Method to handle enemy attack on the player
    public void enemyAttack(characterParent player) {
        // Check if enemy is null or dead
        if (enemy == null || enemy.getHp() <= 0) {
            Game.mainTextArea.setText("There is no enemy to fight in this room.");
            return;
        }

        StringBuilder combatText = new StringBuilder();  // Using StringBuilder for efficient text concatenation

        try {
            int playerDamage = player.attack();  // Player's damage
            int enemyDamage = enemy.attack();  // Enemy's damage

            // Inflict damage on both the player and enemy
            player.hp -= enemyDamage;
            enemy.hp -= playerDamage;

            // Display combat results
            if (player.hp <= 0) {
                combatText.append("You have been defeated by the " + enemy.getName() + "!");
                // Optional: Call game over logic here
            } 
            if (enemy.getHp() <= 0) {
                combatText.append("You defeated the " + enemy.getName() + "!\nYou have earned " + level * 100 + " coins!");
                enemy = null;
            } 
            else {
                combatText.append("Enemy attacks you for " + enemyDamage + " damage!\n")
                          .append("You attack the enemy for " + playerDamage + " damage!\n")
                          .append("The enemy now has " + enemy.getHp() + " health left.");
            }

            // If no enemy is present, provide that feedback to the player
            if (enemy == null) {
                combatText.append("\nNo enemy is present in this room.");
                level = 0;  // Indicating no combat can happen in this room anymore
            }

        } catch (NullPointerException e) {
            combatText.append("Error: Unable to perform combat. Enemy might be null.");
        }

        // Set the text in the main UI area once, after gathering all the info
        Game.mainTextArea.setText(combatText.toString());
    }


    // Getters and setters with null checks
    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public boolean isEnemyDead() {
        return enemy == null || (enemy != null && enemy.getHp() <= 0);
    }

    // Additional method to check if room is safe (no enemy)
    public boolean isRoomSafe() {
        return enemy == null;
    }

    // Method to get enemy name safely
    public String getEnemyName() {
        return enemy != null ? enemy.getName() : "No enemy";
    }

	public static int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
    
}