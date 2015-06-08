package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.PatientModel;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PatientForm extends FormLayout {

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    TextField street = new TextField("Street");
    TextField zip = new TextField("ZIP");
    TextField place = new TextField("Place");
    TextField phone = new TextField("Phone");
    TextField mobile = new TextField("Mobile");
    TextField email = new TextField("Email");
    TextField height = new TextField("Height");
    TextField weight = new TextField("Weight");
    CheckBox vegetarian = new CheckBox("Vegetarian");
    CheckBox smoker = new CheckBox("Smoker");
    TextField bloodType = new TextField("Blood type");
    TextArea allergies = new TextArea("Allergies");
    TextArea intolerances = new TextArea("Intolerances");
    TextArea generalNotes = new TextArea("General notes");
    TextArea biography = new TextArea("Biography");
    
    Button save;
    
    PatientModel pm = new PatientModel();
    Patient patient;

    BeanFieldGroup<Patient> formFieldBindings;

    public PatientForm() {
    	this.setMargin(true);
    	
    	addComponents(firstname, lastname, street, zip, place, phone,
				mobile, email, height, weight, vegetarian, smoker,
				bloodType, allergies, intolerances, generalNotes);
    }
    
    public PatientForm(Patient patient) {
    	this.patient = patient;
    	save = new Button("Save", this::save);
    	
    	save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        
        addComponents(firstname, lastname, street, zip, place, phone,
				mobile, email, height, weight, vegetarian, smoker,
				bloodType, allergies, intolerances, generalNotes, save);
        
        formFieldBindings = BeanFieldGroup.bindFieldsBuffered(this.patient, this);
    }
    
    public PatientForm(Patient patient, Boolean onlyBiography) {
    	this.patient = patient;
    	
    	biography.setWidth("500px");
		biography.setHeight("300px");
    	
    	addComponents(biography);
    }
    
    private void save(Button.ClickEvent event) {
        try {
            formFieldBindings.commit();

            pm.update(patient);

            String msg = String.format("Updated '%s %s'.",
            		patient.getFirstname(),
            		patient.getLastname());
            
            Notification.show(msg, Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {}
    }

}
