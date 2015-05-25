package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.google.gwt.thirdparty.streamhtmlparser.HtmlParser.Mode;
import com.vaadin.client.ui.VFormLayout.Caption;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

public class PatientDataView extends CssLayout implements View {
	
	public static final String NAME = "PatientData";

	/**
	 * 
	 */
	private static final long serialVersionUID = 3085702648286504902L;

	public PatientDataView() {
		this.setSizeUndefined();
		this.setStyleName("patients-wrapper");
		
		Label title = new Label("Melanie Rindiger");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H1);
		title.addStyleName("patients-title");
		this.addComponent(title);
		
		Panel pnlContact = new Panel("Panel caption");
		this.addComponent(pnlContact);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
