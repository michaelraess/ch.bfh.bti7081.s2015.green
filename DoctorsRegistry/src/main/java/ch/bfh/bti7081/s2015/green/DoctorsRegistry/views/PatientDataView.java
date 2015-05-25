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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class PatientDataView extends CssLayout implements View {
	
	public static final String NAME = "PatientData";
	private static final long serialVersionUID = 3085702648286504902L;

	@SuppressWarnings("serial")
	public PatientDataView() {
		this.setSizeUndefined();
		this.setStyleName("patients-wrapper");
		
		Label title = new Label("Patient Informationen");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H1);
		title.addStyleName("patients-title");
		this.addComponent(title);
		
		Panel pnlContact = new Panel("Melanie Rindiger");
		
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

        // Layout the example
        VerticalLayout pnlContactContent1 = new VerticalLayout();
        pnlContactContent1.setMargin(true);
        pnlContactContent1.setSpacing(true);
        pnlContactContent1.addComponent(form);
        pnlContactContent1.addComponent(peekDataModelState);
		
		pnlContact.setContent(pnlContactContent1);
		pnlContact.setHeight("600px");
		this.addComponent(pnlContact);
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
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
