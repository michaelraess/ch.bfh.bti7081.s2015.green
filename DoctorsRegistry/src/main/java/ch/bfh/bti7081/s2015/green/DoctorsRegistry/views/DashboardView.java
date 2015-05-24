package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DashboardView extends HorizontalLayout implements View {
	
	public static final String NAME = "Dashboard";

    /**
	 * 
	 */
	private static final long serialVersionUID = -8781446538751439955L;

	public DashboardView() {
		this.setSizeFull();
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
		
		this.addComponent(leftLayout);
		
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
		
		this.addComponent(rightLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
