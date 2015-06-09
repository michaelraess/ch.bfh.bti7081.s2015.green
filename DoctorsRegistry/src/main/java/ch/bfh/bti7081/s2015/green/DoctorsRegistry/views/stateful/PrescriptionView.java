package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class PrescriptionView extends VerticalLayout implements DRStates, View {
	private static final long serialVersionUID = 3690001988507985358L;

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	@Override
	public void selectView(final StateContext context, final AbstractOrderedLayout v) {
		Label l = new Label("Print Meeting Summary");
		l.addStyleName(ValoTheme.LABEL_H2);
		v.addComponent(l);
		
		//Main Layout
		FormLayout fl = new FormLayout();
		fl.setWidth("100%");
		
		
		
		
		//Buttons
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%");
		fl.addComponent(buttonPanel);
		
		Button btnFinish = new Button("Finish");
		btnFinish.setId("btnFinish");
		btnFinish.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		buttonPanel.addComponent(btnFinish);
		buttonPanel.setComponentAlignment(btnFinish, Alignment.MIDDLE_RIGHT);
		btnFinish.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				Page.getCurrent().setLocation("");
			}
		});
		
		v.addComponent(fl);
	}

}
