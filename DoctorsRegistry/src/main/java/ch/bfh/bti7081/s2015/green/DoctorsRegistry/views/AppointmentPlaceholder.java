package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.GeneralController;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Appointment;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class AppointmentPlaceholder extends HorizontalLayout {
	private static final long serialVersionUID = 3671539622196044708L;
	
	public AppointmentPlaceholder(final Appointment appntmnt) {
		this.setStyleName(ValoTheme.PANEL_WELL);
		this.setWidth("95%");
		
		Label line1 = new Label(appntmnt.getDate() + " " + appntmnt.getTime());
		this.addComponent(line1);
		
		Button btnStart = new Button("Start Meeting");
		btnStart.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		btnStart.addStyleName(ValoTheme.BUTTON_SMALL);
		btnStart.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6335360076620891688L;

			@Override
			public void buttonClick(ClickEvent event) {
				//VaadinService.getCurrentRequest().
				GeneralController.globalNavigator.navigateTo(MeetingView.NAME + "/" + appntmnt.getId());
			}
		});
		
		this.addComponent(btnStart);
		this.setComponentAlignment(btnStart, Alignment.MIDDLE_RIGHT);
	}
	
}
