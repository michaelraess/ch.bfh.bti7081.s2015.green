package ch.bfh.bti7081.s2015.green.DoctorsRegistry;

import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.LoginHandler;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.AppointmentPlaceholder;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.DefaultTemplate;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.LastCasePlaceholder;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.LoginView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("drtheme")
@Widgetset("ch.bfh.bti7081.s2015.green.DoctorsRegistry.DrAppWidgetset")
public class DashboardUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8781446538751439955L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		new Navigator(this, this);

        //
        // The initial log view where the user can login to the application
        //
        getNavigator().addView(LoginView.NAME, LoginView.class);//

        LoginHandler.setUI(this);
		
		if(LoginHandler.isLoggedIn()) {
			createMainView();
		}else{
			LoginHandler.setNavigator(getNavigator());
			getNavigator().navigateTo(LoginView.NAME);
		}
		
		
    }

	public void createMainView() {
		DefaultTemplate dt = new DefaultTemplate(this.getPage(), "Dashboard");
		
		//Adding Menu
		dt.getHeader().addMenuItem("Dashboard", "/");
		dt.getHeader().addMenuItem("Fälle", "/cases");
		dt.getHeader().addMenuItem("Termine", "/appointments");
		dt.getHeader().addMenuItem("Patienten", "/patients");
		
		//Adding content
		//--Left Column
		VerticalLayout leftLayout = new VerticalLayout();
		leftLayout.setSizeFull();
		Label nt = new Label("Nächste Termine");
		nt.setStyleName("dashboard-title");
		nt.setSizeUndefined();
		leftLayout.addComponent(nt);
		leftLayout.setComponentAlignment(nt, Alignment.MIDDLE_CENTER);
		
		for(int i=0; i<3; i++) {
			AppointmentPlaceholder ap = new AppointmentPlaceholder("12.05.2015 08:00", "Melanie Rindiger", "f", "69", "0123456789");
			leftLayout.addComponent(ap);
			leftLayout.setComponentAlignment(ap, Alignment.MIDDLE_CENTER);
		}
		
		dt.getContent().addComponent(leftLayout);
		
		//--Right Column
		VerticalLayout rightLayout = new VerticalLayout();
		rightLayout.setSizeFull();
		Label zbf = new Label("Zuletzt bearbeitete Fälle");
		zbf.setStyleName("dashboard-title");
		zbf.setSizeUndefined();
		rightLayout.addComponent(zbf);
		rightLayout.setComponentAlignment(zbf, Alignment.MIDDLE_CENTER);
		
		for(int i=0; i<4; i++) {
			LastCasePlaceholder lcp = new LastCasePlaceholder("12.05.2015 08:00", "Melanie Rindiger", "f", "69", "0123456789");
			rightLayout.addComponent(lcp);
			rightLayout.setComponentAlignment(lcp, Alignment.MIDDLE_CENTER);
		}
		
		dt.getContent().addComponent(rightLayout);
		
		//Rendering page
		this.setContent(dt);
	}

    @WebServlet(urlPatterns = "/*", name = "DashboardUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DashboardUI.class, productionMode = false)
    public static class DashboardUIServlet extends VaadinServlet {
    }
}
