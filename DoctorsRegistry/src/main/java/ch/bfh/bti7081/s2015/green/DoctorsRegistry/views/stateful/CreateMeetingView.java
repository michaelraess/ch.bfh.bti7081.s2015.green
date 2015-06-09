package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import java.util.ArrayList;
import java.util.Date;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.AppointmentModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientModel;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class CreateMeetingView extends VerticalLayout implements View, DRStates {
	private static final long serialVersionUID = -5954883873210700124L;

	private ComboBox select = null;
	private InlineDateField date = null;
	private TextField tfDescr = null;
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	@Override
	public void selectView(final StateContext context, final AbstractOrderedLayout v) {
		Label l = new Label("New Appointment");
		l.addStyleName(ValoTheme.LABEL_H2);
		v.addComponent(l);
		
		// Some basic content for the window
		VerticalLayout content = new VerticalLayout();
		
		//title.addStyleName(ValoTheme.LABEL_H2);
		content.addComponent(getStyledLabel("Patient"));
		select = new ComboBox();
		select.setRequired(true);
		select.setRequiredError("You have to select a patient");
		select.setImmediate(true);
		select.setNullSelectionAllowed(false);
		   
		// Add some items (the given ID is used as item caption)
		PatientModel pm = new PatientModel();
		ArrayList<Patient> alPatient = pm.getAllWithCase();
		for(Patient p : alPatient) {
			select.addItem(p);
		}
		         
		// User may not select a "null" item
		select.setNullSelectionAllowed(false);
		content.addComponent(select);
		  
		// Date & Time
		content.addComponent(getStyledLabel("Date & Time"));
		 
		date = new InlineDateField();
		date.setRequired(true);
		date.setRequiredError("You have to select a date");
		date.setImmediate(true);
		date.setDateFormat("yyyy-MM-dd");
		date.setResolution(Resolution.MINUTE);
		// Set the date and time to present
		date.setValue(new java.util.Date());
		content.addComponent(date);
		  
		// Description
		content.addComponent(getStyledLabel("Description"));
		tfDescr = new TextField();
		tfDescr.setWidth("100%");
		content.addComponent(tfDescr);
		 
		content.setMargin(true);
		 
		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");
		 
		content.addComponent(hl);
		
		//Buttons
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%");
		content.addComponent(buttonPanel);
		
		Button btnPrintPrescription = new Button("Print Patient Summary");
		btnPrintPrescription.setId("btnPrintPrescription");
		btnPrintPrescription.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		buttonPanel.addComponent(btnPrintPrescription);
		buttonPanel.setComponentAlignment(btnPrintPrescription, Alignment.MIDDLE_RIGHT);
		btnPrintPrescription.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(formValidate()) {
					context.setState(new PrescriptionView());
					context.invokeView(v);
				} else {
					Notification.show("Error", "Please check the form", Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		
		v.addComponent(content);
	}
	
	public boolean formValidate() {
		return select.isValid() && date.isValid();
	}
	
	protected Label getStyledLabel(String title) {
		Label label = new Label(title);
		label.addStyleName(ValoTheme.LABEL_H4);
		return label;
	}

}
