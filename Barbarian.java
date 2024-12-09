public class Barbarian extends characterParent {
    public Barbarian() {
    	super();
        hp = 50;
        weapon = "Battle Axe";
        attackDamage = 10;
    }

    @Override
    public int attack() {
        if (weaponLevel == 0) {
        	attackDamage = 10;
            return 10;  
        }
        if (weaponLevel == 1) {
        	attackDamage = 12;
        	return 12;
        }
        if (weaponLevel >= 2) {
        	attackDamage = 15;
        	return 15;
        }
        else {
        	return 0;
        }
    }
    public void health() {
    	if (armorLevel == 0) {
    		hp = 50;
    	}
    	if (armorLevel == 1) {
    		hp = 65;
    	}
    	if (armorLevel >= 2) {
    		hp = 80;
    	}
    }

    @Override
    public String death() {
        return ("Your character has died. His last words were \"I will see you yn Valhalla my friend.\"");
    }
}