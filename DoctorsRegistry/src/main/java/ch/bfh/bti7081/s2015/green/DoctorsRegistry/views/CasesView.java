package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CasesView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3085702648286504902L;

	public CasesView() {
		// Adding content
		this.setSizeFull();
		Label zbf = new Label("Zuletzt bearbeitete Fälle");
		zbf.setStyleName("dashboard-title");
		zbf.setSizeUndefined();
		this.addComponent(zbf);
		this.setComponentAlignment(zbf, Alignment.MIDDLE_CENTER);
		
		for(int i=0; i<4; i++) {
			LastCasePlaceholder lcp = new LastCasePlaceholder("12.05.2015 08:00", "Melanie Rindiger", "f", "69", "0123456789");
			this.addComponent(lcp);
			this.setComponentAlignment(lcp, Alignment.MIDDLE_CENTER);
		}
	}

}
