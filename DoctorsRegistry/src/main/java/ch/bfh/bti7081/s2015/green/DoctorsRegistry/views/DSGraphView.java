package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class DSGraphView extends HorizontalLayout {
	private static final long serialVersionUID = 5805050756129734841L;
	
	public DSGraphView() {
		this.setHeight(100, Sizeable.Unit.PIXELS);
		Label graphHeader = new Label("Graphs");
		graphHeader.addStyleName(ValoTheme.LABEL_H2);
		this.addComponent(graphHeader);
	}

}
