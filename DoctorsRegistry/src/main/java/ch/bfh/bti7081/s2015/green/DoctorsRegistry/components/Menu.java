package ch.bfh.bti7081.s2015.green.DoctorsRegistry.components;

import java.io.File;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.DoctorsRegisteryUI;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.LoginHandler;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.declarative.Design;
@DesignRoot
public class Menu extends HorizontalLayout {
	MenuBar menu;
	Image logo;
	Label labelUser;
	
	public Menu() {
		Design.read(this);
		generateMenusFromViews();
		logo.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/VAADIN/images/logo.jpg")));
		this.labelUser.setValue("You are logged in as: " + LoginHandler.getLoginHandler().getUserName());
	}
	
	private void generateMenusFromViews() {
		
		/* Here, we fetch the Registered Views
		   (registered with this.menuNavigatorHelper.addViewWithMenu(getNavigator(), Dashboard.NAME, Dashboard.MENUNAME, Dashboard.class);)
		   and add it to the Menu.
		 */
		for (ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.MenuItem menuItem : ((DoctorsRegisteryUI)DoctorsRegisteryUI.getCurrent()).getMenuNavigatorHelper().getMenuItems()) {
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
