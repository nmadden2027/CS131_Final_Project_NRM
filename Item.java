public class Item {
    protected static String name;
    private String description;
    private String type;  // Type of the item ("Potion", "Upgrade", etc.)
    private int effect;    // Effect value (e.g., healing amount or upgrade level)

    // Constructor for potions and upgrades
    public Item(String name, String description, String type, int effect) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getEffect() {
        return effect;
    }

    public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}

	// Method to use the item
    public String use(characterParent player) {
        if (type.equals("Potion")) {
            // Potion effects (e.g., healing or damage boost)
            if (name.equals("Health Potion")) {
                player.hp += effect;  // Heal player by effect amount
                return "You used a " + name + " and restored " + effect + " HP.";
            } else if (name.equals("Damage Potion")) {
                // You can implement a boost to player attack here
                // For simplicity, just a message
                return "You used a " + name + " and your attack damage was doubled for 1 turn.";
            }
        } else if (type.equals("Upgrade")) {
            // Handle upgrade effects (e.g., increasing weapon damage or armor)
            if (name.equals("Weapon Upgrade")) {
                // Assuming we are upgrading the weapon here
                player.attackDamage += effect;  // Increase weapon damage by effect amount
                return "Your weapon has been upgraded. New damage: " + player.attack();
            } else if (name.equals("Armor Upgrade")) {
                // Upgrade armor, for example, increasing defense
                player.hp += effect;  // Increase armor by effect amount
                return "Your armor has been upgraded. New defense: " + player.hp;
            }
        }
        return "This item cannot be used.";
    }

    // A method for displaying item info (you can use it for inventory display)
    public String displayItemInfo() {
        return name + ": " + description + " (Effect: " + effect + ")";
    }
}
