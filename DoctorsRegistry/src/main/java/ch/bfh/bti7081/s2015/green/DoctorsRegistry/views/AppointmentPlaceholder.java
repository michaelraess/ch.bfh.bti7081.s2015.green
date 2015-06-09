package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class AppointmentPlaceholder extends HorizontalLayout {
	private static final long serialVersionUID = 3671539622196044708L;
	
	public AppointmentPlaceholder(String datetime) {
		this.setStyleName(ValoTheme.PANEL_WELL);
		this.setWidth("95%");
		
		Label line1 = new Label(datetime);
		this.addComponent(line1);
		
		Button btnStart = new Button("Start Meeting");
		btnStart.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		btnStart.addStyleName(ValoTheme.BUTTON_SMALL);
		
		this.addComponent(btnStart);
		this.setComponentAlignment(btnStart, Alignment.MIDDLE_RIGHT);
	}
	
}
