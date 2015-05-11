package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DefaultTemplate {
	//Default Layout
	private VerticalLayout body = null;
	//Web Page Sections
	//-- Header
	private DefaultHeader header = null;
	//-- Content
	private AbstractLayout content = null;
	//-- Footer
	private AbstractLayout footer = null;
	
	public DefaultTemplate() {
		this.body = new VerticalLayout();
	}
	
	public Window renderTemplate(String windowTitle, String pageName) {
		Window mainWindow = new Window(pageName + " | " + windowTitle);
		mainWindow.setSizeFull();
		
		//Load default (predefined header) if new has not been set
		if(this.header == null) {
			this.body.addComponent((new DefaultHeader()).renderHeader());
		} else {
			this.body.addComponent(this.header.renderHeader());
		}//test
		
		//Load content if set
		if(this.content != null) {
			this.body.addComponent(this.content);
		}
		
		//Load default footer if new is not set
		if(this.footer == null) {
			this.body.addComponent(new DefaultFooter());
		} else {
			this.body.addComponent(this.footer);
		}
		//Put everything to main body
		mainWindow.setContent(this.body);
		//Render to window
		return mainWindow;
	}

	public DefaultHeader getHeader() {
		return header;
	}

	public void setHeader(DefaultHeader header) {
		this.header = header;
	}

	public AbstractLayout getContent() {
		return content;
	}

	public void setContent(AbstractLayout content) {
		this.content = content;
	}

	public AbstractLayout getFooter() {
		return footer;
	}

	public void setFooter(AbstractLayout footer) {
		this.footer = footer;
	}

}
