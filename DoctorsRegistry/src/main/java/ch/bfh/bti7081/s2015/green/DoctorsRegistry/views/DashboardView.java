package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Case;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.CaseModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.UserModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.placeholders.CasePlaceHolder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class DashboardView extends VerticalLayout implements View {
	private static final long serialVersionUID = -8781446538751439955L;
	public static final String NAME = "Cases";
	private ControlActions controlActions = new ControlActions() {
		
		@Override
		public void createNewCase(ClickEvent event) {
			
		}
	};

	public DashboardView() {
		//this.setSizeFull();
		this.setSizeUndefined();
		this.setStyleName("dr-wrapper");
		
		/*DSGraphView dsGraph = new DSGraphView();
		this.addComponent(dsGraph);*/
		
		//Add Control Panel
		this.createControlPanel();
		
		//Adding content
		this.createMainContent();
	}

	private VerticalLayout rightLayout = null;
	private VerticalLayout casesLayout = null;
	
	private void createMainContent() {
		HorizontalLayout hlMain = new HorizontalLayout();
		hlMain.setWidth("100%");
		this.addComponent(hlMain);
		
		//--Cases that we have
		rightLayout = new VerticalLayout();
		Label zbf = new Label("Last Cases");
		zbf.addStyleName(ValoTheme.LABEL_H3);
		rightLayout.addComponent(zbf);
		
		/*CssLayout patients = new CssLayout();
		patients.setSizeUndefined();
		patients.setStyleName("patients-boxes");*/
		
		casesLayout = new VerticalLayout();
		
		rightLayout.addComponent(casesLayout);
		hlMain.addComponent(rightLayout);
		hlMain.setExpandRatio(rightLayout, 1);
	}
	
	public void reloadCases() {
		casesLayout.removeAllComponents();
		try {
			CaseModel cm = new CaseModel();
			ArrayList<Case> alCases = cm.getAllCases(10);
			
			for(Case c : alCases) {
				CasePlaceHolder cph = new CasePlaceHolder(c);
				casesLayout.addComponent(cph);
			}
		} catch (Exception e) {
			
		}
	}

	private void createControlPanel() {
		Label actionsLabel = new Label("Quick Actions");
		actionsLabel.addStyleName(ValoTheme.LABEL_H3);
		this.addComponent(actionsLabel);
		
		HorizontalLayout hl = new HorizontalLayout();
		this.addComponent(hl);
		
		Button btnCreate = new Button();
		btnCreate.setCaption("Create Case");
		btnCreate.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		btnCreate.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 8780929602616467769L;

			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(new CreateCaseWindow());
			}
		});
		
		hl.addComponent(btnCreate);
	}
	
	class CreateCaseWindow extends Window {
		private static final long serialVersionUID = 4480034605123114259L;
		private ComboBox patientSelect = null;
		private ComboBox doctorSelect = null;
        
	    public CreateCaseWindow() {
	        super("Create Case"); // Set window caption
	        center();

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        content.setMargin(true);
	        setContent(content);
	        
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
			
			content.addComponent(patientSelect);
			
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
			
			content.addComponent(doctorSelect);
	        
	        // Trivial logic for closing the sub-window
	        Button createBtn = new Button("Create");
	        createBtn.addStyleName("dr-window-adminbutton");
	        createBtn.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 7095064966678439199L;

				public void buttonClick(ClickEvent event) {
	            	if(validateForm()) {
						CaseModel cm = new CaseModel();
						
						int caseId = cm.addCase();
						cm.attachPatient(caseId, ((Patient)patientSelect.getValue()).getId());
						cm.attachDoctor(caseId, ((User)doctorSelect.getValue()).getId());
						
						reloadCases();
					} else {
						Notification.show("Error", "Please check the form", Notification.Type.ERROR_MESSAGE);
					}
	            }
	        });
	        Button cancelBtn = new Button("Cancel");
	        cancelBtn.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 4747244850324176779L;

				public void buttonClick(ClickEvent event) {
	                close(); // Close the sub-window
	            }
	        });
	        cancelBtn.addStyleName("dr-window-adminbutton");
	        content.addComponent(new HorizontalLayout(createBtn, cancelBtn));
	    }
	    
	    public boolean validateForm() {
			return this.patientSelect.isValid() && this.doctorSelect.isValid();
		}
	}
	
	public interface ControlActions {
		void createNewCase(ClickEvent event);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		reloadCases();
	}

	public ControlActions getControlActions() {
		return controlActions;
	}

	public void setControlActions(ControlActions controlActions) {
		this.controlActions = controlActions;
	}
}
