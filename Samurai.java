public class Samurai extends characterParent{
	public Samurai() {
		super();
        hp = 40;
        weapon = "Katana";
        attackDamage = 0;
    }

    @Override
    public int attack() {
        if (weaponLevel == 0) {
        	attackDamage = 12;
            return 12;  
        }
        if (weaponLevel == 1) {
        	attackDamage = 15;
        	return 15;
        }
        if (weaponLevel >= 2) {
        	attackDamage = 18;
        	return 18;
        }
        else {
        	return 0;
        }
    }
    public void health() {
    	if (armorLevel == 0) {
    		hp = 40;
    	}
    	if (armorLevel == 1) {
    		hp = 55;
    	}
    	if (armorLevel >= 2) {
    		hp = 70;
    	}
    }

    @Override
    public String death() {
        return ("At least I died with honor.");
    }
}
