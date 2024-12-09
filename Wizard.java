public class Wizard extends characterParent {
    public Wizard() {
    	super();
        hp = 30;
        weapon = "Basic Staff";
        attackDamage = 0;
        
    }

    @Override
    public int attack() {
        if (weaponLevel == 0) {
        	attackDamage = 15;
            return 15;  
        }
        if (weaponLevel == 1) {
        	attackDamage = 18;
        	return 18;
        }
        if (weaponLevel >= 2) {
        	attackDamage = 20;
        	return 20;
        }
        else {
        	return 0;
        }
    }
    public void health() {
    	if (armorLevel == 0) {
    		hp = 30;
    	}
    	if (armorLevel == 1) {
    		hp = 45;
    	}
    	if (armorLevel >= 2) {
    		hp = 60;
    	}
    }

    @Override
    public String death() {
        return ("The wizard falls...");
    }
}