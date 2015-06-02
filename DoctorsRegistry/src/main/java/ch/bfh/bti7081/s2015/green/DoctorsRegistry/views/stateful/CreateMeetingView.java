package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import java.util.Date;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class CreateMeetingView extends VerticalLayout implements View, DRStates {
	private static final long serialVersionUID = -5954883873210700124L;

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	@Override
	public void selectView(final StateContext context, final AbstractOrderedLayout v) {
		Label l = new Label("New Appointment");
		l.addStyleName(ValoTheme.LABEL_H2);
		v.addComponent(l);
		
		//Main Layout
		FormLayout fl = new FormLayout();
		fl.setWidth("100%");
		
		// Create a DateField with the default style
		DateField dateField = new DateField("Date");
		dateField.setRequired(true);
		dateField.setDateFormat("dd.MM.yyyy");
		dateField.setWidth("50%");
		// Set the date and time to present
		dateField.setValue(new Date());
		fl.addComponent(dateField);
		
		//Time
		HorizontalLayout timeLayout = new HorizontalLayout();
		timeLayout.setWidth("50%");
		timeLayout.setCaption("Time");
		//Hours box
		ComboBox timeHours = new ComboBox();
		for(int i=0; i<24; i++) {
			timeHours.addItem(i);
		}
		timeLayout.addComponent(timeHours);
		//Minutes box
		ComboBox timeMinutes = new ComboBox();
		for(int i=0; i<60; i+=5) {
			timeMinutes.addItem(i);
		}
		timeLayout.addComponent(timeMinutes);
		timeLayout.setComponentAlignment(timeMinutes, Alignment.MIDDLE_RIGHT);
		
		fl.addComponent(timeLayout);
		
		//Buttons
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%");
		fl.addComponent(buttonPanel);
		
		Button btnPrintPrescription = new Button("Print Prescription");
		btnPrintPrescription.setId("btnPrintPrescription");
		btnPrintPrescription.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		buttonPanel.addComponent(btnPrintPrescription);
		buttonPanel.setComponentAlignment(btnPrintPrescription, Alignment.MIDDLE_RIGHT);
		btnPrintPrescription.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				context.setState(new PrescriptionView());
				context.invokeView(v);
			}
		});
		
		v.addComponent(fl);
	}

}
