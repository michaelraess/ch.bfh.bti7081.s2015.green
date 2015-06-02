package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class AppointmentsView extends VerticalLayout implements View {

	public static final String NAME = "Appointments";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3085702648286504902L;

	public AppointmentsView() {
		this.setSizeUndefined();
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
		        
		        // Add it to the root component
		        UI.getCurrent().addWindow(sub);
		    }
		});
		
		this.addComponent(btnCreate);
		
		addRandomAppointments("Today", 5);
		addRandomAppointments("Tomorrow", 2);
		addRandomAppointments("28. May", 3);

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
		table.addContainerProperty("Options",  Button.class, null);
		
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
		// TODO Auto-generated method stub
		
	}

}


//Define a sub-window by inheritance
class CreateEditModal extends Window {
	 public CreateEditModal() {
	     super("Appointment"); // Set window caption
	     center();
	
	     // Some basic content for the window
	     VerticalLayout content = new VerticalLayout();
	
		// title.addStyleName(ValoTheme.LABEL_H2);
     	content.addComponent(getStyledLabel("Patient"));
     	ComboBox select = new ComboBox();
        
		  // Add some items (the given ID is used as item caption)
		  select.addItem("Melanie");
		  select.addItem("Hans");
		  select.addItem("Fritz");
		          
		  // User may not select a "null" item
		  select.setNullSelectionAllowed(false);
		 content.addComponent(select);
		  
	     // Date & Time
	     content.addComponent(getStyledLabel("Date & Time"));
	     InlineDateField date = new InlineDateField();
	     date.setResolution(Resolution.SECOND);
	     // Set the date and time to present
	     date.setValue(new java.util.Date());
	     content.addComponent(date);
		  
	     // Description
	     content.addComponent(getStyledLabel("Description"));
	     content.addComponent(new TextField());
	     
	     content.setMargin(true);
	     setContent(content);
	     
	     // Disable the close button
	     setClosable(false);
	
	     // Trivial logic for closing the sub-window
	     Button cancel = new Button("Cancel");
	     cancel.addClickListener(new ClickListener() {
	         public void buttonClick(ClickEvent event) {
	             close(); // Close the sub-window
	         }
	     });
	     content.addComponent(cancel);
	     
	     Button ok = new Button("Save");
	     ok.addClickListener(new ClickListener() {
	         public void buttonClick(ClickEvent event) {
	             close(); // Close the sub-window
	         }
	     });
	     content.addComponent(ok);
	 }
	 
	 protected Label getStyledLabel(String title) {
		 Label label = new Label(title);
		 label.addStyleName(ValoTheme.LABEL_H4);
		 return label;
	 }
}