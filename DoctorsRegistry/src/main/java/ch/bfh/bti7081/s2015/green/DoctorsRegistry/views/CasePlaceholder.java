package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class CasePlaceholder extends HorizontalLayout {
	private static final long serialVersionUID = -6650955988566804565L;
	
	public CasePlaceholder(String datetime, String name, String gender, String age, String phoneNumber) {
		this.setStyleName(ValoTheme.PANEL_WELL);
		this.setWidth("95%");
		
		Label line2 = new Label(name);
		this.addComponent(line2);
		
		Label line5 = new Label(gender);
		this.addComponent(line5);
		
		Label line6 = new Label(age);
		this.addComponent(line6);
		
		Label line4 = new Label("Doktor/in");
		this.addComponent(line4);
		
		Label line1 = new Label(datetime);
		this.addComponent(line1);
		
		Label line3 = new Label(phoneNumber);
		this.addComponent(line3);
		
		Button btnStart = new Button();
		btnStart.setCaption("Start Meeting");
		btnStart.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		btnStart.addStyleName(ValoTheme.BUTTON_TINY);
		
		this.addComponent(btnStart);
	}
	
	public CasePlaceholder() {
		this.setStyleName(ValoTheme.TABLE_COMPACT);
		this.setWidth("95%");
		
		Label line2 = new Label("Patient");
		this.addComponent(line2);
		
		Label line5 = new Label("Gender");
		this.addComponent(line5);
		
		Label line6 = new Label("Age");
		this.addComponent(line6);
		
		Label line4 = new Label("Doctor");
		this.addComponent(line4);
		
		Label line1 = new Label("Meeting");
		this.addComponent(line1);
		
		Label line3 = new Label("Phone number");
		this.addComponent(line3);
		
		Label line7 = new Label("");
		this.addComponent(line7);
	}

}
