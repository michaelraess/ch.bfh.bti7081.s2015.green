package ch.bfh.bti7081.s2015.green.DoctorsRegistry;

import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.AppointmentPlaceholder;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.DefaultTemplate;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.LastCasePlaceholder;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("drtheme")
@Widgetset("ch.bfh.bti7081.s2015.green.DoctorsRegistry.DrAppWidgetset")
public class CasesUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		DefaultTemplate dt = new DefaultTemplate(this.getPage(), "Cases");

		// Adding Menu
		dt.getHeader().addMenuItem("Dashboard", "/");
		dt.getHeader().addMenuItem("Fälle", "/cases/");
		dt.getHeader().addMenuItem("Termine", "/appointments/");
		dt.getHeader().addMenuItem("Patienten", "/patients/");
		
		// Adding content
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
	
	@WebServlet(urlPatterns = "/cases", name = "CasesUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CasesUI.class, productionMode = false)
    public static class CasesUIServlet extends VaadinServlet {
		
    }

}
