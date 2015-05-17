package ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.DoctorsRegisteryUI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;


public class MenuNavigatorHelper{
	
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void addViewWithMenu(Navigator navigator, String viewName, String viewMenuName, Class<? extends View> viewClass) {
		navigator.addView(viewName, viewClass);
		this.menuItems.add(new MenuItem(viewName, viewMenuName));
	}
	
	public void addView(Navigator navigator, String viewName, Class<? extends View> viewClass) {
		navigator.addView(viewName, viewClass);
	}
	
	
	

}
