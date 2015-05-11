package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultTemplate extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1125228132782581160L;
	//Web Page Sections
	//-- Header
	private DefaultHeader header = null;
	//-- Content
	private DefaultContent content = null;
	//-- Footer
	private DefaultFooter footer = null;
	
	public DefaultTemplate(Page page, String pageName) {
		page.setTitle(pageName + " | Doctors Registry");
		
		//Adding Header
		this.header = new DefaultHeader(page);
		this.addComponent(this.header);
		//Adding hr
		this.addComponent(new Label("<hr/>", ContentMode.HTML));
		//Adding Content
		this.content = new DefaultContent(page);
		this.addComponent(this.content);
		//Adding Footer
		this.footer = new DefaultFooter(page);
		this.addComponent(this.footer);
	}

	public DefaultHeader getHeader() {
		return header;
	}

	public void setHeader(DefaultHeader header) {
		this.header = header;
	}

	public DefaultContent getContent() {
		return content;
	}

	public void setContent(DefaultContent content) {
		this.content = content;
	}

	public DefaultFooter getFooter() {
		return footer;
	}

	public void setFooter(DefaultFooter footer) {
		this.footer = footer;
	}

}
