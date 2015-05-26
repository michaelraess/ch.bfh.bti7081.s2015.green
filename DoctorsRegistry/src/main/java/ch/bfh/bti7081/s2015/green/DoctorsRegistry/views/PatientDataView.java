package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;

import com.google.gwt.thirdparty.streamhtmlparser.HtmlParser.Mode;
import com.vaadin.client.ui.VFormLayout.Caption;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class PatientDataView extends CssLayout implements View {
	
	public static final String NAME = "PatientData";
	private static final long serialVersionUID = 3085702648286504902L;
	private TabSheet t;

	@SuppressWarnings("serial")
	public PatientDataView() {
		this.setSizeUndefined();
		this.setStyleName("patients-wrapper");
		
		Label title = new Label("Melanie Rindiger");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName("patients-title");
		this.addComponent(title);
		

        t = new TabSheet();

        t.setWidth("100%");

        t.addTab(getDatasTab(), "Datas", FontAwesome.EDIT);
        t.addTab(getCasesTab(), "Cases", FontAwesome.EDIT);
        t.addTab(getMedicationTab(), "Medication", FontAwesome.EDIT);
        t.addTab(getBiographyTab(), "Biography", FontAwesome.EDIT);
        t.addTab(getCaregiversTab(), "Caregivers", FontAwesome.EDIT);

        this.addComponent(t);
	}
	
	private VerticalLayout getDatasTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
		Panel pnlContact = new Panel();
		
		final Patient dataModel = new Patient();
		Button peekDataModelState = new Button("Speichern",
                new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
                        Notification.show(dataModel.getLastname());
                    }
                });

        // Example form
        final PatientForm form = new PatientForm("Patient Informationen");
        form.setDataSource(dataModel);
        form.setDescription("Please enter valid name and address. Fields marked with * are required. "
                        + "If you try to commit with invalid values, a form error message is displayed. ");

		
		vl.addComponent(form);
		vl.addComponent(pnlContact);
		vl.addComponent(peekDataModelState);
		
		
		return vl;
	}

	private VerticalLayout getCasesTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		
		RichTextArea editor = new RichTextArea();
		vl.addComponent(editor);
		
		
		Button btnDrug = new Button("Add Drug");
        //b.addListener(this); // react to clicks
        vl.addComponent(btnDrug);
        
		Button btnAppointment = new Button("Add Appointment");
        //b.addListener(this); // react to clicks
        vl.addComponent(btnAppointment);
        
		Button btnSave = new Button("Save");
        //b.addListener(this); // react to clicks
        vl.addComponent(btnSave);
        
        
		return vl;
	}
	
	private VerticalLayout getMedicationTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		vl.addComponent(new Label("Medication"));
		return vl;
	}
	
	private VerticalLayout getBiographyTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		vl.addComponent(new Label("Biography"));
		return vl;
	}
	
	private VerticalLayout getCaregiversTab() {
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		vl.addComponent(new Label("Caregivers"));
		return vl;
	}

	@SuppressWarnings({ "deprecation", "serial" })
	public static class PatientForm extends Form {
        public PatientForm(String caption) {
            // Use custom field factory to modify the defaults on how the
            // components are created
            setFormFieldFactory(new MyFieldFactory());
        }

        @SuppressWarnings("deprecation")
		public void setDataSource(Patient dataModel) {

            // Set the form to edit given datamodel by converting pojo used as
            // the datamodel to Item
            setItemDataSource(new BeanItem(dataModel));
        }
    }
	
	@SuppressWarnings({ "serial", "unchecked" })
	static class MyFieldFactory extends DefaultFieldFactory {
		
        @SuppressWarnings("rawtypes")
		public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);

            if ("postalCode".equals(propertyId)) {
                ((TextField) field).setColumns(5);
                field.addValidator(new PostalCodeValidator());
            }

            return field;
        }

    }
	
	@SuppressWarnings("serial")
	static class PostalCodeValidator implements Validator {

        public boolean isValid(Object value) {
            if (value == null || !(value instanceof String)) {
                return false;
            }

            return ((String) value).matches("[0-9]{5}");
        }

        public void validate(Object value) throws InvalidValueException {
            if (!isValid(value)) {
                throw new InvalidValueException(
                        "Postal code must be a five digit number.");
            }
        }
    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
        // w/e may save?
    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
