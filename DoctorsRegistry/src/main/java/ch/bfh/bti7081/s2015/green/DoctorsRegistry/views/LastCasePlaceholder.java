package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LastCasePlaceholder extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5466465250521151036L;

	public LastCasePlaceholder(String datetime, String name, String gender, String age, String phoneNumber) {
		this.setStyleName("dashboard-placeholder");
		this.setWidth("50%");
		
		Label line2 = new Label(name + " (" + gender + ") " + age);
		this.addComponent(line2);
		
		Label line1 = new Label(datetime);
		this.addComponent(line1);
		
		Label line3 = new Label(phoneNumber);
		this.addComponent(line3);
	}
	
}
