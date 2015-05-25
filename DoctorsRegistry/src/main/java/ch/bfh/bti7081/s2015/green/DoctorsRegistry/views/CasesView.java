package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class CasesView extends VerticalLayout implements View {
	private static final long serialVersionUID = 3085702648286504902L;
	public static final String NAME = "Cases";

	public CasesView() {
		// Adding content
		//this.setSizeFull();
		Label zbf = new Label("Fälle");
		zbf.setStyleName(ValoTheme.LABEL_H2);
		zbf.setSizeUndefined();
		this.addComponent(zbf);
		this.setComponentAlignment(zbf, Alignment.MIDDLE_CENTER);
		
		CasePlaceholder lcpHeader = new CasePlaceholder();
		this.addComponent(lcpHeader);
		
		for(int i=0; i<4; i++) {
			CasePlaceholder lcp = new CasePlaceholder("12.05.2015 08:00", "Melanie Rindiger", "f", "69", "0123456789");
			this.addComponent(lcp);
		}
		
		Button btnAddCase = new Button();
		btnAddCase.setCaption("Hinzufügen");
		btnAddCase.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7067988160091294112L;

			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
		
		this.addComponent(btnAddCase);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
