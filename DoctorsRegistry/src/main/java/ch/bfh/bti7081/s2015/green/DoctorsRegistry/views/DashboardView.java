package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Appointment;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.DateTimeConv;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.AppointmentModel;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class DashboardView extends HorizontalLayout implements View {
	private static final long serialVersionUID = -8781446538751439955L;
	public static final String NAME = "Dashboard";

	public DashboardView() {
		
		this.setSizeFull();
		//Adding content
		//--Left Column
		VerticalLayout leftLayout = new VerticalLayout();
		Label nt = new Label("Nächste Termine");
		nt.addStyleName(ValoTheme.LABEL_H2);
		leftLayout.addComponent(nt);
		
		try {
			AppointmentModel am = new AppointmentModel();
			ArrayList<Appointment> alAppmnt = am.getNextAppointments(10);
			
			if(alAppmnt.size() == 0) {
				leftLayout.addComponent(new Label("No Meetings Yet"));
			}
			
			for(Appointment a : alAppmnt) {
				String dateTime = DateTimeConv.convertTime(a.getDateTime(), "dd.MM.yyyy HH:mm:ss");
				AppointmentPlaceholder ap = new AppointmentPlaceholder(dateTime, "Melanie Rindiger", "f", "69", "0123456789");
				leftLayout.addComponent(ap);
			}
		} catch (Exception e) {
			leftLayout.addComponent(new Label("No Meetings Yet"));
		}
		
		this.addComponent(leftLayout);
		this.setExpandRatio(leftLayout, 5);
		
		//--Right Column
		VerticalLayout rightLayout = new VerticalLayout();
		Label zbf = new Label("Zuletzt bearbeitete Fälle");
		zbf.addStyleName(ValoTheme.LABEL_H2);
		rightLayout.addComponent(zbf);
		
		for(int i=0; i<4; i++) {
			LastCasePlaceholder lcp = new LastCasePlaceholder("12.05.2015 08:00", "Melanie Rindiger", "f", "69", "0123456789");
			rightLayout.addComponent(lcp);
		}
		
		this.addComponent(rightLayout);
		this.setExpandRatio(rightLayout, 5);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
