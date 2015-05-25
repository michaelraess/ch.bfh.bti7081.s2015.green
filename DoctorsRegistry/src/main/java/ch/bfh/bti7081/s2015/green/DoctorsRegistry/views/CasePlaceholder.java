package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class CasePlaceholder extends HorizontalLayout {
	private static final long serialVersionUID = -6650955988566804565L;
	
	public CasePlaceholder(String caseNumber, String patientName, String doctorName) {
		this.setStyleName(ValoTheme.PANEL_WELL);
		this.setWidth("95%");
		
		Label line1 = new Label(caseNumber);
		this.addComponent(line1);
		
		Label line2 = new Label(patientName);
		this.addComponent(line2);
		
		Label line3 = new Label(doctorName);
		this.addComponent(line3);
		
		Button btnStart = new Button();
		btnStart.setCaption("Start Meeting");
		btnStart.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		btnStart.addStyleName(ValoTheme.BUTTON_TINY);
		this.addComponent(btnStart);
		this.setComponentAlignment(btnStart, Alignment.MIDDLE_RIGHT);
	}
	
	public CasePlaceholder() {
		this.setStyleName(ValoTheme.TABLE_COMPACT);
		this.setWidth("95%");
		
		Label line1 = new Label("Case Number");
		this.addComponent(line1);
		
		Label line2 = new Label("Patient Name");
		this.addComponent(line2);
		
		Label line3 = new Label("Doctor Name");
		this.addComponent(line3);
		
		Label line7 = new Label("");
		this.addComponent(line7);
	}

}
