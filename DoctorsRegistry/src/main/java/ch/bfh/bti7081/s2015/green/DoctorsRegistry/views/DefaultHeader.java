package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

public class DefaultHeader {

	private VerticalLayout header = null;
	//-- Menu
	private MenuBar menu = null;
	
	public DefaultHeader() {
		this.header = new VerticalLayout();
	}
	
	public void setManu(MenuBar menu) {
		this.menu = menu;
	}
	
	public MenuBar getMenu() {
		return this.menu;
	}
	
	public AbstractLayout renderHeader() {
		if(menu == null) {
			this.menu = new MenuBar();
			
		} else {
			this.header.addComponent(menu);
		}
		
		return this.header;
	}
	
}
