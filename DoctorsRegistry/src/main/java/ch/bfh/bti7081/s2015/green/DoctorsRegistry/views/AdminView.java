package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.UserModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class AdminView extends VerticalLayout implements View {
	private static final long serialVersionUID = -8781446538751439955L;
	public static final String NAME = "Admin";
	
	final Table userTable = new Table();
	UserModel um = new UserModel();
	
	public AdminView() {

		userTable.setSelectable(true);

		this.setStyleName("dr-wrapper");
		Label title = new Label("Admin");
		
		title.addStyleName(ValoTheme.LABEL_H2);
		this.addComponent(title);

		Button newUser = new Button("new User");
		newUser.addStyleName(ValoTheme.BUTTON_FRIENDLY +" dr-adminbutton");
		
		newUser.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				UI.getCurrent().addWindow(new CreateUserWindow());
			}
		});
		
		
		Button deleteUser = new Button("delete User");
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
		});

		refreshTable();
		
		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout layouthor = new HorizontalLayout();
		layouthor.addComponent(userTable,0);
		layout.addComponent(deleteUser,0);
		layout.addComponent(newUser,0);
		layouthor.addComponent(layout);
		this.addComponent(layouthor);

	}
	
	void refreshTable() {
		ArrayList<User> allUser = um.getAllUsers(0);
		userTable.setContainerDataSource(new BeanItemContainer<User>(User.class, allUser));
		userTable.setVisibleColumns(new Object[] {"id", "email", "firstName", "lastName", "isDoctorString"} );
		userTable.setColumnHeaders( new String[] {"ID", "eMail", "First Name", "Last Name", "Is Doctor"} );
		userTable.setPageLength(userTable.size());
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
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
	            	um.deleteUser(toDeleteUser);
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
	
	class CreateUserWindow extends Window {
		
        TextField eMail = new TextField("E-Mail");
        PasswordField password = new PasswordField("Password");
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        CheckBox isDoctor = new CheckBox("Is Doctor"); 
        
	    public CreateUserWindow() {
	        super("Create User/Doctor"); // Set window caption

	        center();

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        
	        //content.addComponent(new Label("Create new User"));
	        content.addComponent(eMail);
	        content.addComponent(password);
	        content.addComponent(firstName);
	        content.addComponent(lastName);
	        content.addComponent(isDoctor);
	        content.setMargin(true);
	        setContent(content);
	        
	        // Trivial logic for closing the sub-window
	        Button ok = new Button("OK");
	        ok.addStyleName("dr-window-adminbutton");
	        ok.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            	um.addUser(eMail.getValue(), password.getValue(), firstName.getValue(), lastName.getValue(), isDoctor.getValue());
	            	refreshTable();
	                close(); // Close the sub-window
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
	}

}
