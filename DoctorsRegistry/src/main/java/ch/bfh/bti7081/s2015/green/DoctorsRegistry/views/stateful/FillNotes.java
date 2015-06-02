package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class FillNotes extends VerticalLayout implements View, DRStates {
	private static final long serialVersionUID = 2735630294867209502L;

	@Override
	public void selectView(final StateContext context, final AbstractOrderedLayout v) {
		Label l = new Label("Notes");
		l.addStyleName(ValoTheme.LABEL_H2);
		v.addComponent(l);
		
		//Main Layout
		FormLayout fl = new FormLayout();
		fl.setWidth("100%");
		
		TextArea notes = new TextArea();
		notes.setWidth("95%");
		notes.setHeight("300px");
		fl.addComponent(notes);
		
		//Buttons
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%");
		fl.addComponent(buttonPanel);
		
		Button btnPrintPrescription = new Button("Print Prescription");
		btnPrintPrescription.setId("btnPrintPrescription");
		buttonPanel.addComponent(btnPrintPrescription);
		buttonPanel.setComponentAlignment(btnPrintPrescription, Alignment.MIDDLE_LEFT);
		btnPrintPrescription.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				context.setState(new PrescriptionView());
				context.invokeView(v);
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
		
		Button btnAddMedication = new Button("Add Medication");
		btnAddMedication.setId("btnAddMedication");
		btnAddMedication.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		buttonPanel.addComponent(btnAddMedication);
		buttonPanel.setComponentAlignment(btnAddMedication, Alignment.MIDDLE_RIGHT);
		btnAddMedication.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				context.setState(new MedicationView());
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
