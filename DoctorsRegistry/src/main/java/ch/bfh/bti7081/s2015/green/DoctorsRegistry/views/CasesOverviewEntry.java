package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CasesOverviewEntry extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5466465250521151036L;

	public CasesOverviewEntry(String datetime, String patient, String doctor) {
		this.setStyleName("casesOverviewEntry");
		this.setWidth("100%");
		
		Label line2 = new Label(datetime);
		this.addComponent(line2);
		
		Label line1 = new Label(patient);
		this.addComponent(line1);
		
		Label line3 = new Label(doctor);
		this.addComponent(line3);
	}
	
}
