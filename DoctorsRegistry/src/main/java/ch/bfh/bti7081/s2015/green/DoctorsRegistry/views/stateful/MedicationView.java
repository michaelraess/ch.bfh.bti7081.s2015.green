package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class MedicationView extends VerticalLayout implements View, DRStates {
	private static final long serialVersionUID = -7655765125538105893L;

	@Override
	public void selectView(final StateContext context, final AbstractOrderedLayout v) {
		Label l = new Label("Medication");
		l.addStyleName(ValoTheme.LABEL_H2);
		v.addComponent(l);
		
		//Main Layout
		FormLayout fl = new FormLayout();
		fl.setWidth("100%");
		
		
		
		
		//Buttons
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.setWidth("100%");
		fl.addComponent(buttonPanel);
		
		Button btnNewAppointment = new Button("Create Next Appointment");
		btnNewAppointment.setId("btnNewAppointment");
		buttonPanel.addComponent(btnNewAppointment);
		buttonPanel.setComponentAlignment(btnNewAppointment, Alignment.MIDDLE_LEFT);
		btnNewAppointment.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6815336151930979397L;

			@Override
			public void buttonClick(ClickEvent event) {
				context.setState(new CreateMeetingView());
				context.invokeView(v);
			}
		});
		
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

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
