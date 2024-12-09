public class characterParent implements character{
    protected int hp;
    protected String weapon;
    protected Inventory inventory;
    protected int coins;
    protected int attackDamage;
    protected int weaponLevel;
    protected int armorLevel;

    public characterParent() {
        hp = 0;
        weapon = "";
        this.inventory = new Inventory(10);  // Initialize the inventory
        coins = 500;
        weaponLevel = 0;
        armorLevel = 0;
    }

    // getters and setters
    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

	// Base attack method, can be overridden by subclasses if necessary
    public int attack() {
        return 0;  
    }
    public void health() {
    	
    }
    // Death method to be overridden
    public String death() {
        return ("Character died.");
    }
    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    // Display the player's inventory
    public String displayInventory() {
        return inventory.displayInventory();
    }

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getWeaponLevel() {
		return weaponLevel;
	}

	public void setWeaponLevel(int weaponLevel) {
		this.weaponLevel = weaponLevel;
	}

	public int getArmorLevel() {
		return armorLevel;
	}

	public void setArmorLevel(int armorLevel) {
		this.armorLevel = armorLevel;
	}
    
}

