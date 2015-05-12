package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.io.File;

import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;

public class DefaultHeader extends HorizontalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5826347493879510093L;
	
	//Page
	private Page page = null;
	//Variables
	private String basepath = "";
	//-- Menu
	private MenuBar menu = null;
	
	public DefaultHeader(Page page) {
		this.page = page;
		
		this.basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		//Put logo
		Image logo = new Image();
		logo.setAlternateText("Logo");
		logo.setSource(new FileResource(new File(this.basepath + "/VAADIN/images/logo.jpg")));
		logo.setWidth(50f, Unit.PIXELS);
		this.addComponent(logo);
		
		//Put menu
		this.menu = new MenuBar();
		this.addComponent(menu);
		
		//Search field
		TextField searchField = new TextField();
		searchField.setValue("Suche...");
		searchField.addFocusListener(new FocusListener(){

			/**
			 * The Search Field will delete current Value. 
			 * @author Reto Zoss
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				// TODO Auto-generated method stub
				((TextField) event.getSource()).setValue("");
			}

		});
		this.addComponent(searchField);
	}
	
	@SuppressWarnings("unused")
	public void addMenuItem(String value, final String link) {
		MenuItem menuItem = this.menu.addItem(value,  new MenuBar.Command() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7525289177064121777L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				DefaultHeader.this.page.setLocation(link);
			}
		});
	}
	
}
