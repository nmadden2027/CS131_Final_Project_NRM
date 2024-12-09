import java.util.Random;
public class Enemy implements character{
	Random random = new Random();
	protected String name;
	protected int hp;
    protected int attackDamage;
    protected int enemyType = random.nextInt(2);
    protected int level;
    public Enemy() {
    	hp = 0;
    	attackDamage = 0;
    	name = "";
    }
    public Enemy(int level) {
    	this.level = level;
    	if (level == 1) {
    		if (enemyType == 0) {
    			this.hp = 20;  
    			this.attackDamage = 4;  
    			this.name = "Goblin Underling";  
    		}
    		if (enemyType == 1) {
    		this.hp = 60;
    		this.attackDamage = 3;
    		this.name = "Orc Soilder";
    		}
    	}
    	if (level == 2) {
    		if (enemyType == 0) {
    			this.hp = 40;
    			this.attackDamage = 8;
    			this.name = "Demon Soilder";
    		}
    		if (enemyType == 1) {
    			this.hp = 20;
    			this.attackDamage = 10;
    			this.name = "Knight of the 9 Realms";
    		}
    	}
    	if (level == 3) {
    		if (enemyType == 0) {
    			this.hp = 50;
    			this.attackDamage = 10;
    			this.name = "Demon General";
    		}
    		if (enemyType == 1) {
    			this.hp = 80;
    			this.attackDamage = 8;
    			this.name = "Orc General";
    		}
    		if (enemyType == 2) {
    			this.hp = 40;
    			this.attackDamage = 12;
    			this.name = "Goblin Overlord";
    		}
    	}
    	if (level == 4) {
    		this.name = "The Devil";
    		this.hp = 100;
    		this.attackDamage = 15;
    	}
    	
    } 
    public int attack() {
        return this.attackDamage;  // Return the fixed attack damage for this enemy
    }

    public String death() {
        return ("The enemy has been slain! You gain " + level * 100 + " coins");
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttackDamage() {
		return attackDamage;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public int getEnemyType() {
		return enemyType;
	}
	public void setEnemyType(int enemyType) {
		this.enemyType = enemyType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
    
}

