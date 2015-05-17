package ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;


public class LoginHandler {
	
	boolean isLoggedIn = false;
	Navigator navigator;
	private String eMail;

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean b) {
		// TODO Auto-generated method stub
		isLoggedIn = b;
	}
	
	public boolean authenticate(String eMail, String password) {
		this.eMail = eMail;
		// TODO Implement Login
		this.isLoggedIn = true;
		return true; // TODO create check;
	}
	
	public static LoginHandler getLoginHandler() {
		VaadinSession vs = VaadinSession.getCurrent();
		
		if(vs.getAttribute(LoginHandler.class) == null) {	
			vs.setAttribute(LoginHandler.class, new LoginHandler());
		}
		
		return vs.getAttribute(LoginHandler.class);
	}

	public String getUserName() {
		return eMail;
	}
	
}
