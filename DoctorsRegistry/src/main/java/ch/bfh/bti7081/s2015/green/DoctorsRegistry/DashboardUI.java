package ch.bfh.bti7081.s2015.green.DoctorsRegistry;

import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.DefaultTemplate;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

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
		DefaultTemplate dt = new DefaultTemplate();
        dt.renderTemplate("DoctorsRegistry", "Dashboard");
    }

    @WebServlet(urlPatterns = "/*", name = "DashboardUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DashboardUI.class, productionMode = false)
    public static class DashboardUIServlet extends VaadinServlet {
    }
}
