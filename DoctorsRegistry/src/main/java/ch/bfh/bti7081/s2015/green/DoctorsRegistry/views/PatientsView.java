package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

public class PatientsView extends CssLayout implements View {
	
	public static final String NAME = "Patients";
	private static final long serialVersionUID = 3085702648286504902L;
	
	private Patient newPatient;
	private Window modalNewPatient;
	private BeanFieldGroup<Patient> formFieldBindings;

	public PatientsView() {
		this.setSizeUndefined();
		this.setStyleName("patients-wrapper");
		
		Label title = new Label("Patients Overview");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName("patients-title");
		this.addComponent(title);
		
		Button btnCreate = new Button("Add patient", this::addPatient);
		btnCreate.addStyleName(ValoTheme.BUTTON_PRIMARY + " " + ValoTheme.BUTTON_SMALL + " patients-button-add");
		this.addComponent(btnCreate);
		
		this.modalNewPatient = new Window("Add patient");
		this.modalNewPatient.setModal(true);
		this.modalNewPatient.setWidth("500px");
		this.modalNewPatient.setHeight("600px");
		FormLayout form = new PatientForm();
		Button add = new Button("Add", this::add);
		add.setStyleName(ValoTheme.BUTTON_PRIMARY);
		add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		form.addComponent(add);
		newPatient = new Patient();
		formFieldBindings = BeanFieldGroup.bindFieldsBuffered(newPatient, form);
		this.modalNewPatient.setContent(form);
		
		CssLayout patients = new CssLayout();
		patients.setSizeUndefined();
		patients.setStyleName("patients-boxes");
		for (int i = 0; i < 9; i++) {
			CssLayout patient = new CssLayout();
			patient.setSizeUndefined();
			patient.addStyleName(ValoTheme.PANEL_WELL + " patients-box");
			patient.addComponent(new Label("Melanie Rindiger I (f) 69"));
			patient.addComponent(new Label("076 638 64 66"));
			Button btnEdit = new Button("", this::editPatient);
			btnEdit.setStyleName("patients-button-edit");
			btnEdit.setIcon(FontAwesome.EDIT);
			patient.addComponent(btnEdit);
			patients.addComponent(patient);
		}
		this.addComponent(patients);
	}

	private void add(Button.ClickEvent event) {
    	try {
            formFieldBindings.commit();

            // Add patient to database
            
            this.modalNewPatient.close();
            
            String msg = String.format("Added '%s %s'.",
            		newPatient.getFirstname(),
            		newPatient.getLastname());
            
            Notification.show(msg, Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {}
    }
	
	private void addPatient(Button.ClickEvent event) {
		if (this.modalNewPatient.getParent() != null) {
			Notification.show("Window is already open.", Type.TRAY_NOTIFICATION);
        } else {
            getUI().addWindow(this.modalNewPatient);
        }
	}
	
	private void editPatient(Button.ClickEvent event) {
		getUI().getNavigator().navigateTo("Patients/1");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
