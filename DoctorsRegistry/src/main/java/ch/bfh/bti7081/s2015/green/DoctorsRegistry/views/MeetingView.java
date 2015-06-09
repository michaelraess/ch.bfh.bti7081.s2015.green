package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.GeneralController;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Medication;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.PatientMedication;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.MedicationModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientMedicationModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class MeetingView extends VerticalLayout implements View {
	private static final long serialVersionUID = 8795567558578720165L;
	public static final String NAME = "Meeting";
	
	private final Table medicationTable = new Table();
	private final PatientMedicationModel pmm = new PatientMedicationModel();
	
	private int meetingId = -1;
	
	public MeetingView() {
		this.setWidth("100%");
		//Replaceable content
		Label l = new Label("Meeting");
		l.addStyleName(ValoTheme.LABEL_H2);
		this.addComponent(l);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if(event.getParameters() == null || event.getParameters().trim().equals("")) {
			GeneralController.globalNavigator.navigateTo("");
		} else {		
			String meetingId = event.getParameters();
			try {
				this.meetingId = Integer.parseInt(meetingId);
			} catch (Exception e) {
				GeneralController.globalNavigator.navigateTo("");
			}
			
			//Show UI
			HorizontalLayout generalView = new HorizontalLayout();
			generalView.setWidth("100%");
			
			VerticalLayout notesView = new VerticalLayout();
			notesView.setWidth("70%");
			Label notesLabel = new Label("Notes");
			notesLabel.addStyleName(ValoTheme.LABEL_H3);
			notesView.addComponent(notesLabel);
			
			TextArea taNotes = new TextArea();
			taNotes.setWidth("100%");
			taNotes.setHeight("500px");
			notesView.addComponent(taNotes);
			
			generalView.addComponent(notesView);
			
			VerticalLayout medicationView = new VerticalLayout();
			Label medicationLabel = new Label("Medication");
			medicationLabel.addStyleName(ValoTheme.LABEL_H3);
			medicationView.addComponent(medicationLabel);
			
			medicationView.addComponent(medicationTable);
			medicationTable.setWidth("100%");
			refreshMedicationTable();
			
			//Medication
			Button addMedication = new Button("Add Medication");
			addMedication.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			addMedication.addClickListener(new ClickListener() {
				private static final long serialVersionUID = -172315121977571465L;

				@Override
				public void buttonClick(ClickEvent event) {
					UI.getCurrent().addWindow(new AddMedicationWindow());
				}
			});
			medicationView.addComponent(addMedication);
			
			generalView.addComponent(medicationView);
			
			this.addComponent(generalView);
			
			//Finish
			Button btnFinish = new Button("Finishs Meeting");
			btnFinish.addStyleName(ValoTheme.BUTTON_FRIENDLY);
			this.addComponent(btnFinish);
			this.setComponentAlignment(btnFinish, Alignment.MIDDLE_RIGHT);
		}
	}
	
	void refreshMedicationTable() {
		ArrayList<PatientMedication> alPm = pmm.getMedicationForAppointment(this.meetingId);
		medicationTable.setContainerDataSource(new BeanItemContainer<PatientMedication>(PatientMedication.class, alPm));
		medicationTable.setVisibleColumns(new Object[] {"id", "name", "dose"} );
		medicationTable.setColumnHeaders( new String[] {"ID", "Name", "Dose"} );
		medicationTable.setPageLength(medicationTable.size());
	}
	
	class AddMedicationWindow extends Window {
		private static final long serialVersionUID = 6619334484249836704L;
		
		ComboBox cbMedication = new ComboBox("Medication");
        TextField tfDose = new TextField("Dose");
        
	    public AddMedicationWindow() {
	        super("Add Medication"); // Set window caption
	        center();

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        
	        //List of Medications
	        MedicationModel mm = new MedicationModel();
	        ArrayList<Medication> alMedic = mm.getAllMedications(10);
	        for(Medication m : alMedic) {
	        	cbMedication.addItem(m);
	        }
	        
	        cbMedication.setRequired(true);
	        cbMedication.setNullSelectionAllowed(false);
	        tfDose.setRequired(true);
	        tfDose.setConverter(Integer.class);
	        
	        content.addComponent(cbMedication);
	        content.addComponent(tfDose);
	        content.setMargin(true);
	        setContent(content);
	        
	        // Trivial logic for closing the sub-window
	        Button addBtn = new Button("Add");
	        addBtn.addStyleName("dr-window-adminbutton");
	        addBtn.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 3152464480037120579L;

				public void buttonClick(ClickEvent event) {
	            	if(validate()) {
	            		PatientMedicationModel pmm = new PatientMedicationModel();
	            		pmm.addMedicationForAppointment(MeetingView.this.meetingId, ((Medication)cbMedication.getValue()).getName(), (Integer)tfDose.getConvertedValue());
	            		
	            		refreshMedicationTable();
	            		
	            		close(); // Close the sub-window
	            	} else {
	            		Notification.show("Error", "Please check the form", Notification.Type.ERROR_MESSAGE);
	            	}
	            }
	        });
	        Button cancel = new Button("Cancel");
	        cancel.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 8470534126676202422L;

				public void buttonClick(ClickEvent event) {
	                close(); // Close the sub-window
	            }
	        });
	        cancel.addStyleName("dr-window-adminbutton");
	        content.addComponent(new HorizontalLayout(addBtn, cancel));
	    }
	    
	    public boolean validate() {
	    	return cbMedication.isValid() && tfDose.isValid() && ((Integer)tfDose.getConvertedValue()) < ((Medication)cbMedication.getValue()).getDayDose();
	    }
	}

}
