package erp.client.javafx.entity;

public enum UserType {
	ALL 			("All"),
    ADMINISTRATOR   ("Administrator"),
    LOCAL_USER      ("Local User");

    private String type;

    UserType(String type) {
        this.type = type;
    }
    
    public String getUserType() {
    	return this.type;
    }
    
    public static UserType getUserType(String userType) {
    	UserType[] values = UserType.values();
    	for(UserType ut : values) {
    		if(ut.getUserType().equalsIgnoreCase(userType))
    			return ut;
    	}
    	return null;
    }
}
