package ch.bfh.bti7081.s2015.green.DoctorsRegistry;

import javax.servlet.SessionTrackingMode;
import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.AppointmentsView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.CasesView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.DashboardView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.LoginView;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.Menu;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.PatientsView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@Theme("drtheme")
@Widgetset("ch.bfh.bti7081.s2015.green.DoctorsRegistry.DrAppWidgetset")
public class GeneralController extends UI {
	private static final long serialVersionUID = 5390254949054698917L;
	private static final boolean isDebug = true;
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
		//Login Checker
        String currentUser = (String) getSession().getAttribute("user");
        if(currentUser != null || isDebug) {
        	showMainView();
        } else {
        	this.setContent(new LoginView(new LoginView.LoginListener() {
				private static final long serialVersionUID = -6472665895715933073L;
				
				@Override
				public void loginSuccessful() {
					Page.getCurrent().reload();
				}
			}));
        }
	}
	
	private void showMainView() {
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
		menu.addView(new DashboardView(), "", DashboardView.NAME, FontAwesome.DASHBOARD);
		menu.addView(new CasesView(), CasesView.NAME, CasesView.NAME, FontAwesome.EDIT);
		menu.addView(new PatientsView(), PatientsView.NAME, PatientsView.NAME, FontAwesome.MALE);
		menu.addView(new AppointmentsView(), AppointmentsView.NAME, AppointmentsView.NAME, FontAwesome.CALENDAR);
		
		navigator.addViewChangeListener(viewChangeListener);
		
		mainVl.addComponent(menu);
		mainVl.addComponent(viewContainer);
		mainVl.setExpandRatio(viewContainer, 1);
	}

	// notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {
		private static final long serialVersionUID = -1303877173524139079L;

		@Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
	
	@WebServlet(urlPatterns = "/*", name = "GeneralUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GeneralController.class, productionMode = false)
    public static class GeneralUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -2646649631121528587L;
    }

}
