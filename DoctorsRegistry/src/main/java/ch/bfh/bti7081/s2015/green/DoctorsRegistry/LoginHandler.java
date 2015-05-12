package ch.bfh.bti7081.s2015.green.DoctorsRegistry;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.LoginView;

import com.vaadin.navigator.Navigator;


public class LoginHandler {
	
	static boolean isLoggedIn = false;
	static Navigator navigator;
	static DashboardUI ui;

	public static boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return isLoggedIn;
	}

	public static void setIsLoggedIn(boolean b) {
		// TODO Auto-generated method stub
		isLoggedIn = b;
	}

	public static void setNavigator(Navigator navigator) {
		LoginHandler.navigator = navigator;
	}

	public static void toMain() {
		navigator.removeView(LoginView.NAME);
		ui.createMainView();
		
	}

	public static void setUI(DashboardUI dashboardUI) {
		ui = dashboardUI;
	}
	
}