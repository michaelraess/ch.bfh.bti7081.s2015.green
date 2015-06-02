package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AppointmentPlaceholder extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3671539622196044708L;
	
	public AppointmentPlaceholder(String datetime, String name, String gender, String age, String phoneNumber) {
		this.setStyleName(ValoTheme.PANEL_WELL);
		this.setWidth("95%");
		
		Label line1 = new Label(datetime);
		this.addComponent(line1);
		
		Label line2 = new Label(name + " (" + gender + ") " + age);
		this.addComponent(line2);
		
		Label line3 = new Label(phoneNumber);
		this.addComponent(line3);
	}
	
}
