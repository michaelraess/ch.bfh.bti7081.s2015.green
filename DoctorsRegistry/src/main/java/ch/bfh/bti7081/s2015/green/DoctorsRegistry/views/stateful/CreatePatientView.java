package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class CreatePatientView extends VerticalLayout implements View, DRStates {
	private static final long serialVersionUID = -6639140066232100367L;

	private TextField patientFirstName = null;
	private TextField patientLastName = null;
	
	public CreatePatientView() {
		
	}
	
	@Override
	public void selectView(final StateContext context, final AbstractOrderedLayout v) {
		//Replaceable content
		Label l = new Label("Patient Data");
		l.addStyleName(ValoTheme.LABEL_H2);
		v.addComponent(l);
		
		FormLayout fl = new FormLayout();
		fl.setWidth("100%");
		
		//First name
		patientFirstName = new TextField("First Name");
		patientFirstName.setRequired(true);
		patientFirstName.setWidth("50%");
		fl.addComponent(patientFirstName);
		
		//Last name
		patientLastName = new TextField("Last Name");
		patientLastName.setRequired(true);
		patientLastName.setWidth("50%");
		fl.addComponent(patientLastName);
		
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%");
		fl.addComponent(buttonPanel);
		
		Button btnErase = new Button("Erase");
		btnErase.setId("btnErase");
		buttonPanel.addComponent(btnErase);
		buttonPanel.setComponentAlignment(btnErase, Alignment.MIDDLE_LEFT);
		btnErase.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -5874924304883208954L;

			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
		
		Button btnNewAppointment = new Button("Create Next Appointment");
		btnNewAppointment.setId("btnNewAppointment");
		buttonPanel.addComponent(btnNewAppointment);
		buttonPanel.setComponentAlignment(btnNewAppointment, Alignment.MIDDLE_LEFT);
		btnNewAppointment.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				context.setState(new CreateMeetingView());
				context.invokeView(v);
			}
		});
		
		Button btnSave = new Button("Start Meetng");
		btnSave.setId("btnSave");
		btnSave.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		buttonPanel.addComponent(btnSave);
		buttonPanel.setComponentAlignment(btnSave, Alignment.MIDDLE_RIGHT);
		btnSave.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -6032005823480838964L;

			@Override
			public void buttonClick(ClickEvent event) {
				context.setState(new FillNotes());
				context.invokeView(v);
			}
		});
		
		v.addComponent(fl);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
