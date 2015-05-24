package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class LastCasePlaceholder extends HorizontalLayout {
	
	private static final long serialVersionUID = 5466465250521151036L;

	public LastCasePlaceholder(String datetime, String name, String gender, String age, String phoneNumber) {
		this.setStyleName("dashboard-placeholder");
		this.setWidth("100%");
		
		Label line2 = new Label(name + " (" + gender + ") " + age);
		this.addComponent(line2);
		
		Label line4 = new Label("Doktor/in");
		this.addComponent(line4);
		
		Label line1 = new Label(datetime);
		this.addComponent(line1);
		
		Label line3 = new Label(phoneNumber);
		this.addComponent(line3);
	}
	
	public LastCasePlaceholder() {
		this.setStyleName("case-header");
		this.setWidth("100%");
		
		Label line2 = new Label("Patient/in");
		this.addComponent(line2);
		
		Label line4 = new Label("Doktor/in");
		this.addComponent(line4);
		
		Label line1 = new Label("Termine");
		this.addComponent(line1);
		
		Label line3 = new Label("Telefon");
		this.addComponent(line3);
	}
	
}
