package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.placeholders;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Appointment;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Case;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.AppointmentPlaceholder;

public class CasePlaceHolder extends VerticalLayout {
	private static final long serialVersionUID = -6352304370496529962L;

	public CasePlaceHolder(Case c) {
		this.setWidth("100%"); 
		this.addStyleName(ValoTheme.PANEL_WELL + " patients-box patients-box-full");
		
		HorizontalLayout hlMain = new HorizontalLayout();
		
		VerticalLayout vlPatient = new VerticalLayout();
		
		vlPatient.addComponent(new Label(c.getPatient().getFirstname() + " " + c.getPatient().getLastname()));
		vlPatient.addComponent(new Label(c.getPatient().getPhone() + " (" + c.getPatient().getMobile() + ")"));
		
		hlMain.addComponent(vlPatient);
		
		Embedded e = new Embedded("", new ThemeResource("images/arrow.png"));
		e.setWidth("60px");
		e.setStyleName("dr-dashboard-arrow");
		hlMain.addComponent(e);
		
		VerticalLayout vlDoctor = new VerticalLayout();
		
		vlDoctor.addComponent(new Label(c.getDoctor().getFirstName() + " " + c.getDoctor().getLastName()));
		vlDoctor.addComponent(new Label(c.getDoctor().getEmail()));
		
		hlMain.addComponent(vlDoctor);
		
		this.addComponent(hlMain);
		
		//Appointments
		Label lAppList = new Label("Appointments");
		lAppList.addStyleName(ValoTheme.LABEL_H4);
		this.addComponent(lAppList);
		
		for(Appointment a : c.getAlAppnmt()) {
			AppointmentPlaceholder ap = new AppointmentPlaceholder(a.getDate() + " " + a.getTime());
			this.addComponent(ap);
		}
		
		/*Button btnEdit = new Button();
		btnEdit.setStyleName("patients-button-edit");
		btnEdit.setIcon(FontAwesome.EDIT);
		btnEdit.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -3441780767428890308L;
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("Patients/1");
			}
		});
		this.addComponent(btnEdit);*/
	}
	
	
	
}
