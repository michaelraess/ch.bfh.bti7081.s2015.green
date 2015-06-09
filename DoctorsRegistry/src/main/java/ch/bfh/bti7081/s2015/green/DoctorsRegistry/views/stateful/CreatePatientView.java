package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.CaseModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.UserModel;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class CreatePatientView extends VerticalLayout implements View, DRStates {
	private static final long serialVersionUID = -6639140066232100367L;

	private TextField patientFirstName = null;
	private TextField patientLastName = null;
	
	private ComboBox patientSelect = null;
	private ComboBox doctorSelect = null;
	
	public CreatePatientView() {
		
	}
	
	@Override
	public void selectView(final StateContext context, final AbstractOrderedLayout v) {
		v.setSizeUndefined();
		v.setStyleName("dr-wrapper");
		
		//Replaceable content
		Label l = new Label("Patient Data");
		l.addStyleName(ValoTheme.LABEL_H2);
		v.addComponent(l);
		
		FormLayout fl = new FormLayout();
		fl.setWidth("100%");
		
		//Select Patient Field
		patientSelect = new ComboBox("Select Patient");
		patientSelect.setRequired(true);
		patientSelect.setRequiredError("You have to select a patient");
		patientSelect.setImmediate(true);
		patientSelect.setNullSelectionAllowed(false);
		PatientModel pm = new PatientModel();
		ArrayList<Patient> alPatients = pm.getAllWithoutCase();
		
		for(Patient p : alPatients) {
			patientSelect.addItem(p);
		}
		
		fl.addComponent(patientSelect);
		
		//Select Doctor
		doctorSelect = new ComboBox("Select Doctor");
		doctorSelect.setRequired(true);
		doctorSelect.setRequiredError("You have to select a doctor");
		doctorSelect.setImmediate(true);
		doctorSelect.setNullSelectionAllowed(false);
		UserModel um = new UserModel();
		ArrayList<User> alDoctors = um.getAllDoctors();
		
		for(User p : alDoctors) {
			doctorSelect.addItem(p);
		}
		
		fl.addComponent(doctorSelect);
		
		//Control Buttons
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%");
		fl.addComponent(buttonPanel);
		
		/*Button btnErase = new Button("Erase");
		btnErase.setId("btnErase");
		buttonPanel.addComponent(btnErase);
		buttonPanel.setComponentAlignment(btnErase, Alignment.MIDDLE_LEFT);
		btnErase.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -5874924304883208954L;

			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});*/
		
		Button btnNewAppointment = new Button("Create Next Appointment");
		btnNewAppointment.setId("btnNewAppointment");
		buttonPanel.addComponent(btnNewAppointment);
		buttonPanel.setComponentAlignment(btnNewAppointment, Alignment.MIDDLE_LEFT);
		btnNewAppointment.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(validateForm()) {
					context.setState(new CreateMeetingView());
					context.invokeView(v);
				} else {
					Notification.show("Error", "Please check the form", Notification.Type.ERROR_MESSAGE);
				}
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
				if(validateForm()) {
					CaseModel cm = new CaseModel();
					
					int caseId = cm.addCase();
					cm.attachPatient(caseId, ((Patient)patientSelect.getValue()).getId());
					cm.attachDoctor(caseId, ((User)doctorSelect.getValue()).getId());
					
					context.setCaseId(caseId);
					
					context.setState(new FillNotes());
					context.invokeView(v);
				} else {
					Notification.show("Error", "Please check the form", Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		
		v.addComponent(fl);
	}
	
	public boolean validateForm() {
		return this.patientSelect.isValid() && this.doctorSelect.isValid();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
