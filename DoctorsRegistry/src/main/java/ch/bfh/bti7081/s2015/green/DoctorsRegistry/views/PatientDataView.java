package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientModel;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class PatientDataView extends CssLayout implements View {
	
	public static final String NAME = "PatientData";
	private static final long serialVersionUID = 3085702648286504902L;
	private TabSheet t;
	
	private PatientModel pm = new PatientModel();
	private Patient patient;
	private BeanFieldGroup<Patient> biographyBinding;

	public PatientDataView(int id) {
		patient = pm.get(id);
		
		setSizeUndefined();
		setStyleName("patients-wrapper");
		
		Label title = new Label(patient.getFirstname() + " " + patient.getLastname());
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName("patients-title");
		addComponent(title);

        t = new TabSheet();
        t.setWidth("100%");
        t.addTab(getDataTab(), "Data", FontAwesome.EDIT);
        t.addTab(getBiographyTab(), "Biography", FontAwesome.EDIT);

        addComponent(t);
	}
	
	public PatientDataView getContent() {
		return this;
	}
	
	private VerticalLayout getDataTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
        PatientForm form = new PatientForm(patient);
		
		vl.addComponent(form);
		
		return vl;
	}

	@SuppressWarnings("serial")
	private VerticalLayout getBiographyTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
		PatientForm form = new PatientForm(patient, true);
		
		Button save = new Button("Save");
		save.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	try {
		            biographyBinding.commit();

		            pm.update(patient);

		            Notification.show("Saved biography.", Type.TRAY_NOTIFICATION);
		        } catch (FieldGroup.CommitException e) {}
		    }
		});
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		
		biographyBinding = BeanFieldGroup.bindFieldsBuffered(patient, form);
		form.addComponent(save);
		
		vl.addComponent(form);
		
		return vl;
	}

    public void selectedTabChange(SelectedTabChangeEvent event) {

    }
	
	public void enter(ViewChangeEvent event) {

	}

}
