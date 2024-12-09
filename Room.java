public class Room {
    String description;
    String extraInfo;

    public Room() {
    	description = "";
    	extraInfo = "";

    }
    
    public Room(String description, String extraInfo) {
        this.description = description;
        this.extraInfo = extraInfo;
    }

    public void setDescription(String description) {
		this.description = description;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getDescription() {
        return description;
    }

    public String getExtraInfo() {
        return extraInfo;
    }
}
   