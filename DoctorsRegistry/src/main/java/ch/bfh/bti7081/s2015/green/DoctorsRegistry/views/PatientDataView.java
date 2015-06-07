package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class PatientDataView extends CssLayout implements View {
	
	public static final String NAME = "PatientData";
	private static final long serialVersionUID = 3085702648286504902L;
	private TabSheet t;
	
	private Patient patient;
	private BeanFieldGroup<Patient> biographyBinding;

	public PatientDataView() {
		// Dummy patient
		// TODO: Get correct patient
		this.patient = new Patient();
		patient.setFirstname("Melanie");
		patient.setLastname("Rindiger");
		patient.setStreet("Street 123");
		patient.setZip("3000");
		patient.setPlace("Bern");
		patient.setPhone("033 123 45 67");
		patient.setMobile("079 123 45 67");
		patient.setEmail("melanie.rindiger@gmail.com");
		patient.setHeight(170);
		patient.setWeight(55);
		patient.setVegetarian(true);
		patient.setSmoker(false);
		patient.setBloodType("A");
		patient.setAllergies("-");
		patient.setIntolerances("Lactose intolerance");
		patient.setGeneralNotes("-");
		patient.setBiography("-");
		
		this.setSizeUndefined();
		this.setStyleName("patients-wrapper");
		
		Label title = new Label("Melanie Rindiger");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName("patients-title");
		this.addComponent(title);

        t = new TabSheet();
        t.setWidth("100%");
        t.addTab(getDataTab(), "Data", FontAwesome.EDIT);
        t.addTab(getBiographyTab(), "Biography", FontAwesome.EDIT);

        this.addComponent(t);
	}
	
	private VerticalLayout getDataTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
        PatientForm form = new PatientForm(patient);
		
		vl.addComponent(form);
		
		return vl;
	}

	private VerticalLayout getBiographyTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
		PatientForm form = new PatientForm(patient, true);
		
		Button save = new Button("Save", this::saveBiography);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		
		this.biographyBinding = BeanFieldGroup.bindFieldsBuffered(patient, form);
		form.addComponent(save);
		
		vl.addComponent(form);
		
		return vl;
	}

	private void saveBiography(Button.ClickEvent event) {
		try {
            this.biographyBinding.commit();

            // Save patient biography in database

            Notification.show("Saved biography.",Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {}
	}
	
    public void selectedTabChange(SelectedTabChangeEvent event) {
        // w/e may save?
    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
