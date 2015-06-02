package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class PatientsView extends CssLayout implements View {
	
	public static final String NAME = "Patients";
	private static final long serialVersionUID = 3085702648286504902L;

	public PatientsView() {
		this.setSizeUndefined();
		this.setStyleName("patients-wrapper");
		
		Label title = new Label("Patients Overview");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName("patients-title");
		this.addComponent(title);
		
		Button btnCreate = new Button();
		btnCreate.setCaption("Add Patient");
		btnCreate.addStyleName(ValoTheme.BUTTON_FRIENDLY + " " + ValoTheme.BUTTON_SMALL + " patients-button-add");
		this.addComponent(btnCreate);
		
		CssLayout patients = new CssLayout();
		patients.setSizeUndefined();
		patients.setStyleName("patients-boxes");
		for (int i = 0; i < 9; i++) {
			CssLayout patient = new CssLayout();
			patient.setSizeUndefined();
			patient.addStyleName(ValoTheme.PANEL_WELL + " patients-box");
			patient.addComponent(new Label("Melanie Rindiger I (f) 69"));
			patient.addComponent(new Label("076 638 64 66"));
			Button btnEdit = new Button();
			btnEdit.setStyleName("patients-button-edit");
			btnEdit.setIcon(FontAwesome.EDIT);
			btnEdit.addClickListener(new ClickListener() {
				private static final long serialVersionUID = -3441780767428890308L;
				public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo("Patients/1");
				}
			});
			patient.addComponent(btnEdit);
			patients.addComponent(patient);
		}
		this.addComponent(patients);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
