package ch.bfh.bti7081.s2015.green.DoctorsRegistry.components;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.MenuNavigatorHelper;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.declarative.Design;
@DesignRoot
public class Menu extends HorizontalLayout {
	MenuBar menu;
	
	public Menu() {
		Design.read(this);

//		addMenuItem(menu, "Dashboard", "/");
//		addMenuItem(menu, "Fälle", "/cases/");
//		addMenuItem(menu, "Termine", "/appointments/");
//		addMenuItem(menu, "Patienten", "/patients/");
		
		generateMenusFromViews();

	}
	
	private void generateMenusFromViews() {
		//MenuNavigator navigator = (MenuNavigator)getUI().getNavigator();
		//navigator.
		
		for (ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.MenuItem menuItem : MenuNavigatorHelper.MENUITEMS) {
			addMenuItem(this.menu, menuItem.getViewMenuName(), menuItem.getViewName());
		}
		
	}

	public void addMenuItem(MenuBar menu, String viewMenuName, final String viewName) {
		menu.addItem(viewMenuName,  new MenuBar.Command() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7525289177064121777L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				getUI().getNavigator().navigateTo(viewName);
			}
		});
	}
}
