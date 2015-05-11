package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class DefaultFooter extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7111617239445051605L;

	public DefaultFooter() {
		this.addComponent(new Label("<hr />", ContentMode.TEXT));
		
		Label copyright = new Label("2015 \u00a9 SoED: Team Green");
		this.addComponent(copyright);
		this.setComponentAlignment(copyright, Alignment.MIDDLE_CENTER);
		
	}
	
}
