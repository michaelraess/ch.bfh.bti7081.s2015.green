package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Appointment;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Case;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.AppointmentModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.CaseModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.CreateEditModal.Invalidator;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class AppointmentsView extends VerticalLayout implements View {
	private static final long serialVersionUID = 3085702648286504902L;
	
	public static final String NAME = "Appointments";
	private final Table table = new Table();

	public AppointmentsView() {
		this.setWidth("100%");
		this.setStyleName("dr-wrapper");
		
		Label title = new Label("Appointments Overview");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName("dr-title");
		this.addComponent(title);
		
		Button btnCreate = new Button();
		btnCreate.setCaption("Add Appointment");
		btnCreate.addStyleName(ValoTheme.BUTTON_FRIENDLY + " " + ValoTheme.BUTTON_SMALL + " dr-button-add");
		btnCreate.addClickListener(new ClickListener() {
		    public void buttonClick(ClickEvent event) {
		        CreateEditModal sub = new CreateEditModal();
		        
		        //Invalidate Table
		        sub.setInvalidator(new Invalidator() {
					
					@Override
					public void invalidate() {
						refreshTable(10);
					}
				});
		        
		        // Add it to the root component
		        UI.getCurrent().addWindow(sub);
		    }
		});
		
		this.addComponent(btnCreate);
		this.setComponentAlignment(btnCreate, Alignment.MIDDLE_RIGHT);
		
		/*addRandomAppointments("Today", 5);
		addRandomAppointments("Tomorrow", 2);
		addRandomAppointments("28. May", 3);*/
		
		//Show 10 future Appointments
		this.showAppointments(10);
	}
	
	private void showAppointments(int limit) {
		Label title = new Label("Future Appointments");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H3);
		title.addStyleName("dr-title");
		this.addComponent(title);
		
		// Define two columns for the built-in container
		table.addContainerProperty("Date", String.class, null);
		table.addContainerProperty("Time", String.class, null);
		table.addContainerProperty("Patient",  String.class, null);
		table.addContainerProperty("Description",  String.class, null);
		table.addContainerProperty("Edit",  Button.class, null);
		
		refreshTable(limit);
		
		this.addComponent(table);
	}
	
	public void refreshTable(int limit) {
		//Showing real data
		CaseModel cm = new CaseModel();
		ArrayList<Case> alCases = cm.getAllCases(limit);
		
		table.removeAllItems();
		
		int i = 0;
		for(Case c : alCases) {
			for(Appointment a : c.getAlAppnmt()) {
				final Button edit = new Button();
				edit.setIcon(FontAwesome.EDIT);
				edit.addClickListener(new ClickListener() {
				    public void buttonClick(ClickEvent event) {
				        CreateEditModal sub = new CreateEditModal();
				        
				        // Add it to the root component
				        UI.getCurrent().addWindow(sub);
				    }
				});
				
				// Add a few other rows using shorthand addItem()
				table.addItem(new Object[]{a.getDate(), a.getTime(), c.getPatient().getFirstname() + " " + c.getPatient().getLastname(), a.getDescr(), edit}, i);
				i++;
			}
		}
		
		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());
	}
	
	private void addRandomAppointments(String strtitle, int count) {
		Label title = new Label(strtitle);
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H3);
		title.addStyleName("dr-title");
		this.addComponent(title);
		
		Table table = new Table();
		// Define two columns for the built-in container
		table.addContainerProperty("Time", String.class, null);
		table.addContainerProperty("Patient",  String.class, null);
		table.addContainerProperty("Description",  String.class, null);
		table.addContainerProperty("Edit",  Button.class, null);
		
		for (int i = 0; i < count; i++) {
			final Button edit = new Button();
			edit.setIcon(FontAwesome.EDIT);
			edit.addClickListener(new ClickListener() {
			    public void buttonClick(ClickEvent event) {
			        CreateEditModal sub = new CreateEditModal();
			        
			        // Add it to the root component
			        UI.getCurrent().addWindow(sub);
			    }
			});
			
			// Add a few other rows using shorthand addItem()
			table.addItem(new Object[]{"08:12", "Melanie", "Psychosis", edit}, i);

		}
		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());
		
		this.addComponent(table);
	}
	
	

	@Override
	public void enter(ViewChangeEvent event) {
		
		
	}

}


//Define a sub-window by inheritance
class CreateEditModal extends Window {
	private static final long serialVersionUID = 5486179873268699265L;

	ComboBox select = null;
	InlineDateField date = null;
	TextField tfDescr = null;
	
	private Invalidator invalidator = new Invalidator() {
		@Override
		public void invalidate() {}
	};
	
	public CreateEditModal() {
	     super("Appointment"); // Set window caption
	     center();
	
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
	     setContent(content);
	     
	     // Disable the close button
	     setClosable(false);
	     
	     HorizontalLayout hl = new HorizontalLayout();
	     hl.setWidth("100%");
	     
	     // Trivial logic for closing the sub-window
	     Button btnCancel = new Button("Cancel");
	     btnCancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 4304573163196098698L;

			public void buttonClick(ClickEvent event) {
				close(); // Close the sub-window
	         }
	     });
	     hl.addComponent(btnCancel);
	     
	     Button btnSave = new Button("Save");
	     btnSave.addStyleName(ValoTheme.BUTTON_FRIENDLY);
	     btnSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1100027470906277593L;

			public void buttonClick(ClickEvent event) {
				if(validateForm()) {
					// Save
					Patient selectedPatient = (Patient)select.getValue();
					long selectedDate = date.getValue().getTime();
					String descr = tfDescr.getValue();
					
					AppointmentModel am = new AppointmentModel();
					am.addAppointmentForPatient(selectedDate, descr, selectedPatient.getId());
					
					//Invalidate
					CreateEditModal.this.invalidator.invalidate();
					// Close the sub-window
					close(); 
				} else {
					Notification.show("Error", "Please check the form", Notification.Type.ERROR_MESSAGE);
				}
	         }
	     });
	     hl.addComponent(btnSave);
	     hl.setComponentAlignment(btnSave, Alignment.MIDDLE_RIGHT);
	     
	     content.addComponent(hl);
	 }
	 
	protected Label getStyledLabel(String title) {
		Label label = new Label(title);
		label.addStyleName(ValoTheme.LABEL_H4);
		return label;
	}
	 
	 public boolean validateForm() {
		 return select.isValid();
	 }
	 
	 public Invalidator getInvalidator() {
		return invalidator;
	}

	public void setInvalidator(Invalidator invalidator) {
		this.invalidator = invalidator;
	}

	public interface Invalidator {
		 public void invalidate();
	 }
}