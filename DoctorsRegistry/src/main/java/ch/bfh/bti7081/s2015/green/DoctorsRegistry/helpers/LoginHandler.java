package ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.DashboardUI;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.Login;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;


public class LoginHandler {
	
	boolean isLoggedIn = false;
	Navigator navigator;
	DashboardUI ui;

	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		System.out.println("isLoggedIn: " + isLoggedIn);
		return isLoggedIn;
	}

	public void setIsLoggedIn(boolean b) {
		// TODO Auto-generated method stub
		isLoggedIn = b;
	}

	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}

	public void toMain() {
		navigator.removeView(Login.NAME);
		ui.createMainView();
		
	}

	public void setUI(DashboardUI dashboardUI) {
		ui = dashboardUI;
	}
	
	public static LoginHandler getLoginHandler() {
		VaadinSession vs = VaadinSession.getCurrent();
		
		if(vs.getAttribute(LoginHandler.class) == null) {	
			vs.setAttribute(LoginHandler.class, new LoginHandler());
		}
		
		return vs.getAttribute(LoginHandler.class);
	}
	
}
