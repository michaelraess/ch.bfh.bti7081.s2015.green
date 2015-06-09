package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MeetingView extends VerticalLayout implements View {
	private static final long serialVersionUID = 8795567558578720165L;
	public static final String NAME = "Meeting";
	
	public MeetingView() {
		//Replaceable content
		Label l = new Label("Meeting");
		l.addStyleName(ValoTheme.LABEL_H2);
		this.addComponent(l);
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
