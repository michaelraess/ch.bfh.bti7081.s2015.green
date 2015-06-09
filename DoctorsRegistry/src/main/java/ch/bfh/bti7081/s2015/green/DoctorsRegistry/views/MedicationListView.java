package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Medication;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.MedicationModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class MedicationListView extends VerticalLayout implements View {
	private static final long serialVersionUID = -7879560299120597738L;
	public static final String NAME = "Medication List";

	private final Table userTable = new Table();
	private MedicationModel mm = new MedicationModel();
	
	public MedicationListView() {
		this.setStyleName("dr-wrapper");
		Label title = new Label(NAME);
		
		title.addStyleName(ValoTheme.LABEL_H2);
		this.addComponent(title);

		Button newUser = new Button("new Medication");
		newUser.addStyleName(ValoTheme.BUTTON_FRIENDLY +" dr-adminbutton");
		
		newUser.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				UI.getCurrent().addWindow(new CreateMedicationWindow());
			}
		});
		
		/*
		Button deleteUser = new Button("delete Medication");
		deleteUser.addStyleName(ValoTheme.BUTTON_DANGER +" dr-adminbutton");

		
		deleteUser.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(userTable.getValue() != null) {
					UI.getCurrent().addWindow(new DeleteUserWindow());
				}
				else
				{
					Notification.show("Warning", "Please select a user to delete!", Notification.Type.ERROR_MESSAGE);
				}
			}
		});*/

		refreshTable();
		
		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout layouthor = new HorizontalLayout();
		layouthor.addComponent(userTable,0);
		//layout.addComponent(deleteUser,0);
		layout.addComponent(newUser,0);
		layouthor.addComponent(layout);
		this.addComponent(layouthor);
	}
	
	void refreshTable() {
		ArrayList<Medication> allMedic = mm.getAllMedications(100);
		userTable.setContainerDataSource(new BeanItemContainer<Medication>(Medication.class, allMedic));
		userTable.setVisibleColumns(new Object[] {"id", "name", "descr", "dayDose"} );
		userTable.setColumnHeaders( new String[] {"ID", "Name", "Description", "Daily Dose"} );
		userTable.setPageLength(userTable.size());
	}
	
	class DeleteUserWindow extends Window {
	    public DeleteUserWindow() {
	        super("Delete User"); // Set window caption
	        center();
	        

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        User usr = (User) userTable.getValue();
	        Label message = new Label("Do you want do delete: <br /> <br /> id: " +
	        		usr.getId() + 
	        		"<br />eMail: "+
	        		usr.getEmail()+
	        		"<br /><br />This action can not be undone!");
	        message.setContentMode(ContentMode.HTML);
	        content.addComponent(message);
	        content.setMargin(true);
	        setContent(content);
	        

	        // Trivial logic for closing the sub-window
	        Button ok = new Button("OK");
	        ok.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            	User toDeleteUser = (User) userTable.getValue();
	            	//um.deleteUser(toDeleteUser);
	                close(); // Close the sub-window
	                refreshTable();
	                
	            }
	        });
	        ok.addStyleName("dr-window-adminbutton");

	        Button cancel = new Button("Cancel");
	        cancel.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	                close(); // Close the sub-window
	            }
	        });
	        cancel.addStyleName("dr-window-adminbutton");

	        content.addComponent(new HorizontalLayout(ok, cancel));
	    }
	}
	
	class CreateMedicationWindow extends Window {
		
        TextField name = new TextField("Name");
        TextField descr = new TextField("Description");
        TextField dayDose = new TextField("Daily Dose");
        
	    public CreateMedicationWindow() {
	        super("Create Medication"); // Set window caption
	        this.setWidthUndefined();

	        center();

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        
	        name.setRequired(true);
	        descr.setRequired(true);
	        dayDose.setRequired(true);
	        dayDose.setConverter(Integer.class);
	        dayDose.setValue("1");
	        dayDose.setNullSettingAllowed(false);
	        
	        //
	        content.addComponent(name);
	        content.addComponent(descr);
	        content.addComponent(dayDose);
	        content.setMargin(true);
	        setContent(content);
	        
	        // Trivial logic for closing the sub-window
	        Button ok = new Button("OK");
	        ok.addStyleName("dr-window-adminbutton");
	        ok.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            	if(validate()) {
	            		mm.addMedication(name.getValue(), descr.getValue(), (Integer)dayDose.getConvertedValue());
	            		refreshTable();
	            		close(); // Close the sub-window
	            	} else {
	            		Notification.show("Error", "Please check the form", Notification.Type.ERROR_MESSAGE);
	            	}
	            }
	        });
	        Button cancel = new Button("Cancel");
	        cancel.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	                close(); // Close the sub-window
	            }
	        });
	        cancel.addStyleName("dr-window-adminbutton");
	        content.addComponent(new HorizontalLayout(ok, cancel));
	    }
	    
	    public boolean validate() {
	    	return name.isValid() && descr.isValid() && dayDose.isValid();
	    }
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
