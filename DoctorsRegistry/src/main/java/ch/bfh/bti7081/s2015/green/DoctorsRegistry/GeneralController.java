package ch.bfh.bti7081.s2015.green.DoctorsRegistry;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestGraphDatabase;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.UserModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.AppointmentsView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.CasesView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.DashboardView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.PatientsView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("drtheme")
@Widgetset("ch.bfh.bti7081.s2015.green.DoctorsRegistry.DrAppWidgetset")
public class GeneralController extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5390254949054698917L;

	@Override
	protected void init(VaadinRequest request) {
		createMainView();
	}
	
	public void createMainView() {
		this.getPage().setTitle("Doctors Registry");
		VerticalLayout mainVl = new VerticalLayout();
		mainVl.setStyleName("main");
		this.setContent(mainVl);	
		
		//Put logo
		Image logo = new Image();
		logo.setAlternateText("Logo");
		logo.setSource(new ThemeResource("images/logo_75_60.jpg"));
		logo.setWidth(50f, Unit.PIXELS);
		mainVl.addComponent(logo);
		mainVl.addComponent(new Label("<hr/>", ContentMode.HTML));
		
		TabSheet tabsheet = new TabSheet();
		mainVl.addComponent(tabsheet);
		
		// Dashboard Tab
		DashboardView dashboardTab = new DashboardView();
		tabsheet.addTab(dashboardTab, "Dashboard", new ThemeResource("images/dashboard_icon_30.png"));
		
		//Cases
		CasesView cases = new CasesView();
		tabsheet.addTab(cases, "Fälle", new ThemeResource("images/case_icon_30.jpg"));
		
		//Appointments
		AppointmentsView appointments = new AppointmentsView();
		tabsheet.addTab(appointments, "Termine", new ThemeResource("images/appointments_icon_30.jpg"));
		
		//Cases
		PatientsView patients = new PatientsView();
		tabsheet.addTab(patients, "Patienten", new ThemeResource("images/patient_icon_30.png"));
		
		//Footer
		mainVl.addComponent(new Label("<hr/>", ContentMode.HTML));
		Label copyright = new Label("2015 \u00a9 SoED: Team Green");
		copyright.setSizeUndefined();
		mainVl.addComponent(copyright);
		mainVl.setComponentAlignment(copyright, Alignment.TOP_CENTER);
		
		//GraphDatabaseService graphDb = new RestGraphDatabase(DATABASE_ENDPOINT, DATABASE_USERNAME, DATABASE_PASSWORD);
		
		/*Node n = graphDb.createNode();
		n.setProperty("Email", "sergii.bilousov@gmail.com");*/
		
		UserModel um = new UserModel(DATABASE_ENDPOINT, DATABASE_USERNAME, DATABASE_PASSWORD);
		
		ArrayList<User> allUsers = um.getAllUsers(25);
		for(User u:allUsers) {
			Label l = new Label(u.getEmail() + " " + u.getPassword());
			mainVl.addComponent(l);
		}
		
		
	}
	
    public static final String DATABASE_ENDPOINT = "http://178.62.254.192:7474/db/data";
    public static final String DATABASE_USERNAME = "neo4j";
    public static final String DATABASE_PASSWORD = "qwerty1";
 
	
	@WebServlet(urlPatterns = "/*", name = "GeneralUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GeneralController.class, productionMode = false)
    public static class GeneralUIServlet extends VaadinServlet {
    }

}
