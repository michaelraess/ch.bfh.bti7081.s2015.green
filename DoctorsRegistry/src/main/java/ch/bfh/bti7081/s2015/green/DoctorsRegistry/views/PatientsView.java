package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientModel;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

public class PatientsView extends VerticalLayout implements View {
	
	public static final String NAME = "Patients";
	private static final long serialVersionUID = 3085702648286504902L;
	
	private PatientModel pm = new PatientModel();
	private Patient newPatient;
	private Window modalNewPatient;
	private BeanFieldGroup<Patient> formFieldBindings;

	CssLayout content;
	
	public PatientsView() {
		
	}

	@SuppressWarnings("serial")
	private CssLayout getContent() {
		CssLayout pv = new CssLayout();
		
		pv.setSizeUndefined();
		pv.setStyleName("patients-wrapper");
		
		Label title = new Label("Patients Overview");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName("patients-title");
		pv.addComponent(title);
		
		Button btnCreate = new Button("Add patient");
		btnCreate.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	if (modalNewPatient.getParent() != null) {
					Notification.show("Window is already open.", Type.TRAY_NOTIFICATION);
		        } else {
		            getUI().addWindow(modalNewPatient);
		        }
		    }
		});
		btnCreate.addStyleName(ValoTheme.BUTTON_PRIMARY + " " + ValoTheme.BUTTON_SMALL + " patients-button-add");
		pv.addComponent(btnCreate);
		
		this.modalNewPatient = new Window("Add patient");
		this.modalNewPatient.setModal(true);
		this.modalNewPatient.setWidth("500px");
		this.modalNewPatient.setHeight("600px");
		FormLayout form = new PatientForm();
		Button add = new Button("Add");
		add.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	try {
		            formFieldBindings.commit();

		            String msg = String.format("Added '%s %s'.",
		            		newPatient.getFirstname(),
		            		newPatient.getLastname());
		            
		            pm.add(newPatient);
		            
		            modalNewPatient.close();
		            
		            removeComponent(content);
		            content = getContent();
		    		addComponent(content);
		            
		            Notification.show(msg, Type.TRAY_NOTIFICATION);
		        } catch (FieldGroup.CommitException e) {}
		    }
		});
		add.setStyleName(ValoTheme.BUTTON_PRIMARY);
		add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		form.addComponent(add);
		newPatient = new Patient();
		formFieldBindings = BeanFieldGroup.bindFieldsBuffered(newPatient, form);
		this.modalNewPatient.setContent(form);
		
		CssLayout patients = new CssLayout();
		patients.setSizeUndefined();
		patients.setStyleName("patients-boxes");
		
		ArrayList<Patient> patientsList = pm.getAll(0);
		for (Patient p : patientsList) {
			CssLayout patient = new CssLayout();
			patient.setSizeUndefined();
			patient.addStyleName(ValoTheme.PANEL_WELL + " patients-box");
			patient.addComponent(new Label(p.getFirstname() + " " + p.getLastname()));
			patient.addComponent(new Label(p.getEmail()));
			patient.addComponent(new Label(p.getMobile()));
			
			Button btnEdit = new Button("");
			btnEdit.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			    	getUI().getNavigator().navigateTo("Patients/" + event.getButton().getData());
			    }
			});
			btnEdit.setData(p.getId());
			btnEdit.setStyleName("patients-button-edit");
			btnEdit.setIcon(FontAwesome.EDIT);
			patient.addComponent(btnEdit);
			
			Button btnDelete = new Button("");
			btnDelete.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			    	pm.delete(pm.get((int) event.getButton().getData()));
					removeComponent(content);
					content = getContent();
					addComponent(content);
					Notification.show("Patient deleted.", Type.TRAY_NOTIFICATION);
			    }
			});
			btnDelete.setData(p.getId());
			btnDelete.setStyleName("patients-button-delete");
			btnDelete.setIcon(FontAwesome.TRASH_O);
			btnDelete.addStyleName(ValoTheme.BUTTON_DANGER);
			patient.addComponent(btnDelete);
			
			patients.addComponent(patient);
		}
		pv.addComponent(patients);
		
		return pv;
	}
	
	public void enter(ViewChangeEvent event) {
		if (content != null) {
			removeComponent(content);
		}
		
		if (event.getParameters() != "") {
			PatientDataView pdv = new PatientDataView(Integer.parseInt(event.getParameters()));
			content = pdv.getContent();
		} else {
			content = getContent();
		}
		
		addComponent(content);
	}

}
