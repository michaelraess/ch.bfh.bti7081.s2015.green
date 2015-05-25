package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class DashboardView extends HorizontalLayout implements View {
	private static final long serialVersionUID = -8781446538751439955L;
	public static final String NAME = "Dashboard";

	public DashboardView() {
		
		this.setSizeFull();
		//Adding content
		//--Left Column
		VerticalLayout leftLayout = new VerticalLayout();
		Label nt = new Label("Nächste Termine");
		nt.addStyleName(ValoTheme.LABEL_H2);
		leftLayout.addComponent(nt);
		
		for(int i=0; i<3; i++) {
			AppointmentPlaceholder ap = new AppointmentPlaceholder("12.05.2015 08:00", "Melanie Rindiger", "f", "69", "0123456789");
			leftLayout.addComponent(ap);
		}
		
		this.addComponent(leftLayout);
		this.setExpandRatio(leftLayout, 5);
		
		//--Right Column
		VerticalLayout rightLayout = new VerticalLayout();
		Label zbf = new Label("Zuletzt bearbeitete Fälle");
		zbf.addStyleName(ValoTheme.LABEL_H2);
		rightLayout.addComponent(zbf);
		
		for(int i=0; i<4; i++) {
			LastCasePlaceholder lcp = new LastCasePlaceholder("12.05.2015 08:00", "Melanie Rindiger", "f", "69", "0123456789");
			rightLayout.addComponent(lcp);
		}
		
		this.addComponent(rightLayout);
		this.setExpandRatio(rightLayout, 5);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
