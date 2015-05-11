package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultFooter extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7111617239445051605L;
	
	//Page
	private Page page = null;
		
	public DefaultFooter(Page page) {
		this.page = page;
		
		this.addComponent(new Label("<hr/>", ContentMode.HTML));
		Label copyright = new Label("2015 \u00a9 SoED: Team Green");
		this.addComponent(copyright);
		this.setComponentAlignment(copyright, Alignment.TOP_CENTER);
	}
	
}
