package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.UserModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
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
		this.setSizeFull();
		
		Label title = new Label("Graphs");
		
		title.addStyleName(ValoTheme.LABEL_H2);
		this.addComponent(title);

		Button newUser = new Button("new User");
		
		newUser.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				UI.getCurrent().addWindow(new CreateUserWindow());
			}
		});
		
		this.addComponent(newUser);
		
		Button deleteUser = new Button("delete User");
		
		deleteUser.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(userTable.getValue() != null) {
					UI.getCurrent().addWindow(new DeleteUserWindow());
				}
			}
		});;
		
		this.addComponent(deleteUser);
		
		

		
		userTable.setContainerDataSource(new BeanItemContainer<>(User.class));
		userTable.setSelectable(true);
		
		refreshTable();
		this.addComponent(userTable);
	}
	
	void refreshTable() {
		ArrayList<User> allUser = um.getAllUsers(0);
		userTable.setContainerDataSource(new BeanItemContainer<>(User.class, allUser));
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	class DeleteUserWindow extends Window {
	    public DeleteUserWindow() {
	        super("Delete User Window"); // Set window caption
	        center();
	        
	        setHeight("200px");
	        setWidth("400px");


	        center();

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        content.addComponent(new Label("Do you want do delete: " + userTable.getValue()));
	        content.setMargin(true);
	        setContent(content);
	        
	        // Disable the close button
	        setClosable(false);

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
	        content.addComponent(ok);
	        
	        Button cancel = new Button("Cancel");
	        cancel.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	                close(); // Close the sub-window
	            }
	        });
	        content.addComponent(cancel);
	    }
	}
	
	class CreateUserWindow extends Window {
		
		
        TextField eMail = new TextField("E-Mail");
        PasswordField password = new PasswordField("Password");
        
	    public CreateUserWindow() {
	        super("Create User Window"); // Set window caption
	        center();
	        
	        setHeight("200px");
	        setWidth("400px");


	        center();

	        // Some basic content for the window
	        VerticalLayout content = new VerticalLayout();
	        

	        
	        
	        content.addComponent(new Label("Create new User"));
	        content.addComponent(eMail);
	        content.addComponent(password);
	        content.setMargin(true);
	        setContent(content);
	        
	        // Disable the close button
	        setClosable(false);

	        // Trivial logic for closing the sub-window
	        Button ok = new Button("OK");
	        ok.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            	um.addUser(eMail.getValue(), password.getValue());
	            	refreshTable();
	                close(); // Close the sub-window
	            }
	        });
	        content.addComponent(ok);
	        Button cancel = new Button("Cancel");
	        cancel.addClickListener(new ClickListener() {
	            public void buttonClick(ClickEvent event) {
	                close(); // Close the sub-window
	            }
	        });
	        content.addComponent(cancel);
	    }
	}

}
