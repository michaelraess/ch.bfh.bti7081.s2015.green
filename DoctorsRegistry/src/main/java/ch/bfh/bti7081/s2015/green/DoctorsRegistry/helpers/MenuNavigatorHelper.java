package ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers;

import java.util.ArrayList;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;


public class MenuNavigatorHelper{
	
	public static ArrayList<MenuItem> MENUITEMS = new ArrayList<MenuItem>();

	public static void addViewWithMenu(Navigator navigator, String viewName, String viewMenuName, Class<? extends View> viewClass) {
		navigator.addView(viewName, viewClass);
		MENUITEMS.add(new MenuItem(viewName, viewMenuName));
	}
	
	public static void addView(Navigator navigator, String viewName, Class<? extends View> viewClass) {
		navigator.addView(viewName, viewClass);
	}
	

}
