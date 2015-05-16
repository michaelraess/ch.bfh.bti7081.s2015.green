package ch.bfh.bti7081.s2015.green.DoctorsRegistry.components;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@DesignRoot
public class Footer extends VerticalLayout {
	public Footer() {
		//Design.read(this);
		this.addComponent(new Label("<hr/>", ContentMode.HTML));
		Label copyright = new Label("2015 \u00a9 SoED: Team Green");
		this.addComponent(copyright);
		this.setComponentAlignment(copyright, Alignment.TOP_CENTER);
	}
}
