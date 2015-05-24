package ch.bfh.bti7081.s2015.green.DoctorsRegistry;

import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.AppointmentsView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.CasesView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.DashboardView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.Menu;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.PatientsView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@Theme("drtheme")
@Widgetset("ch.bfh.bti7081.s2015.green.DoctorsRegistry.DrAppWidgetset")
public class GeneralController extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5390254949054698917L;
	
	//Variables
	Menu menu = null;

	@Override
	protected void init(VaadinRequest request) {
		//Set Session timeout to 3000s
		VaadinSession.getCurrent().getSession().setMaxInactiveInterval(3000);
		//Change Page title
		this.getPage().setTitle("Doctors Registry");
		//Prepare the template page
		createMainView();
		
		
	}
	
	public void createMainView() {
		//Create Main Container for Views
		HorizontalLayout mainVl = new HorizontalLayout();
		mainVl.setSizeFull();
		this.setContent(mainVl);
		
		CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();
		
		//Init Navigator
		final Navigator navigator = new Navigator(this, viewContainer);
		
		//Menu
		menu = new Menu(navigator);
		menu.addView(new DashboardView(), "", DashboardView.NAME, FontAwesome.EDIT);
		menu.addView(new CasesView(), CasesView.NAME, CasesView.NAME, FontAwesome.EDIT);
		menu.addView(new PatientsView(), PatientsView.NAME, PatientsView.NAME, FontAwesome.EDIT);
		menu.addView(new AppointmentsView(), AppointmentsView.NAME, AppointmentsView.NAME, FontAwesome.EDIT);
		
		navigator.addViewChangeListener(viewChangeListener);
		
		mainVl.addComponent(menu);
		mainVl.addComponent(viewContainer);
		mainVl.setExpandRatio(viewContainer, 1);
		/*
		
		
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
		
		//Patients
		PatientsView patients = new PatientsView();
		tabsheet.addTab(patients, "Patienten", new ThemeResource("images/patient_icon_30.png"));
		
		//Footer
		mainVl.addComponent(new Label("<hr/>", ContentMode.HTML));
		Label copyright = new Label("2015 \u00a9 SoED: Team Green");
		copyright.setSizeUndefined();
		mainVl.addComponent(copyright);
		mainVl.setComponentAlignment(copyright, Alignment.TOP_CENTER);
		
		//GraphDatabaseService graphDb = new RestGraphDatabase(DATABASE_ENDPOINT, DATABASE_USERNAME, DATABASE_PASSWORD);
		*/
		/*Node n = graphDb.createNode();
		n.setProperty("Email", "sergii.bilousov@gmail.com");*/
		
		//UserModel um = new UserModel(DATABASE_ENDPOINT, DATABASE_USERNAME, DATABASE_PASSWORD);
	}
	
	// notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
	
    public static final String DATABASE_ENDPOINT = "http://178.62.254.192:7474/db/data";
    public static final String DATABASE_USERNAME = "neo4j";
    public static final String DATABASE_PASSWORD = "qwerty1";
 
	
	@WebServlet(urlPatterns = "/*", name = "GeneralUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GeneralController.class, productionMode = false)
    public static class GeneralUIServlet extends VaadinServlet {
    }

}
