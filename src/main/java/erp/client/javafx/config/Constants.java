package erp.client.javafx.config;

public class Constants {

	public static class User {
		public static final String LOGIN_URL = "/authentication/authenticate";
		public static final String REFRESH_TOKEN_URL = "/authentication/refreshtoken";
		public static final String GET_LOGGED_USER = "/authentication/get-logged-user";
		public static final String GET_ALL_USER_URL = "/configurations/user/get-all-users";
		public static final String GET_ALL_USER_ROLES_URL = "/configurations/user/get-all-roles";
		public static final String ADD_USER_URL = "/configurations/user/save";
		public static final String REMOVE_USER_URL = "/configurations/user/remove";
	}
	
	public static class GST {
		public static final String GET_ALL_STATE_CODE_URL = "/gst/get-all-state-code";
	}
	
	public static class Dealer {
		public static final String GET_ALL_DEALERS_URL = "/dealer/get-all-dealers";
		public static final String GET_ALL_DEALERS_FOR_SEARCH_BOX_URL = "/dealer/get-all-dealers-for-search-box";
		public static final String SAVE_DEALER_URL = "/dealer/save";
		public static final String REMOVE_DEALER_URL = "/dealer/remove";
	}
	
	public static class Stock {
		public static final String GET_ALL_STOCK_TRANSACTIONS = "/stock/transactions/get-all";
	}
	
	public static class StockIn {
		public static final String GET_ALL_STOCK_IN = "/stock/stock-in/get-all";
		public static final String GET_CATEGORY_URL = "/stock/stock-in/get-category-list";
		public static final String ADD_STOCK_URL = "/stock/stock-in/save";
	}
	
	public static class StockReturn {
		public static final String GET_ALL_STOCK_RETURN = "/stock/stock-return/get-all";
	}
}
