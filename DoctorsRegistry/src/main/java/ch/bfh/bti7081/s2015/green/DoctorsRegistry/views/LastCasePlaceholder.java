package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class LastCasePlaceholder extends CssLayout {
	
	private static final long serialVersionUID = 5466465250521151036L;

	public LastCasePlaceholder(String name, String gender, String age, String phoneNumber) {

		this.setSizeUndefined();
		this.addStyleName(ValoTheme.PANEL_WELL + " patients-box patients-box-full");
		this.addComponent(new Label(name + " ("+gender+") "+ age));
		this.addComponent(new Label(phoneNumber));
		Button btnEdit = new Button();
		btnEdit.setStyleName("patients-button-edit");
		btnEdit.setIcon(FontAwesome.EDIT);
		btnEdit.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -3441780767428890308L;
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("Patients/1");
			}
		});
		this.addComponent(btnEdit);
	}
}
