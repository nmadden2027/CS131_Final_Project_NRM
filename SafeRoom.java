public class SafeRoom extends Room {
	public SafeRoom() {
		description = "Welcome to a safe room.";
		extraInfo = "What would you like to purchase?";
	}
    public SafeRoom(String description, String extraInfo) {
        super(description, extraInfo);
    }
    public static void safeRoomButtons() {
    	Game.mainTextArea.setText("Welcome to a safe room. What would you like to purchase?");
        Game.choice1.setText("Buy Items");
        Game.choice1.setActionCommand("b");
        Game.choice2.setText("Heal Character");
        Game.choice2.setActionCommand("s2");
        Game.choice3.setText("Leave Store");
        Game.choice3.setActionCommand("l");
    }
}
