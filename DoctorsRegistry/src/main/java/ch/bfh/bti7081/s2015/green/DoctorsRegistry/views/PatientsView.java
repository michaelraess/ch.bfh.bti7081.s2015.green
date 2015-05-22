package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PatientsView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3085702648286504902L;

	public PatientsView() {
		CssLayout wrapper = new CssLayout();
		wrapper.setSizeUndefined();
		wrapper.setStyleName("patients-wrapper");
		
		Label title = new Label("Patienten Übersicht");
		title.setSizeUndefined();
		title.addStyleName("patients-title");
		wrapper.addComponent(title);
		
		CssLayout patients = new CssLayout();
		patients.setSizeUndefined();
		patients.setStyleName("patients-boxes");
		for (int i = 0; i < 9; i++) {
			Label patient = new Label("Melanie Rindiger I (f) 69<br />076 638 64 66", ContentMode.HTML);
			patient.setSizeUndefined();
			patient.addStyleName("patients-box");
			patients.addComponent(patient);
		}
		wrapper.addComponent(patients);
		
		this.addComponent(wrapper);
	}

}
