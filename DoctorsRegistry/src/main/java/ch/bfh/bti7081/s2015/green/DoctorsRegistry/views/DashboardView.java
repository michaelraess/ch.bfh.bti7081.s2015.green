package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import java.util.ArrayList;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Case;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.CaseModel;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.placeholders.CasePlaceHolder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class DashboardView extends VerticalLayout implements View {
	private static final long serialVersionUID = -8781446538751439955L;
	public static final String NAME = "Dashboard";
	private ControlActions controlActions = new ControlActions() {
		
		@Override
		public void createNewCase(ClickEvent event) {
			
		}
	};

	public DashboardView() {
		//this.setSizeFull();
		this.setSizeUndefined();
		this.setStyleName("dr-wrapper");
		
		/*DSGraphView dsGraph = new DSGraphView();
		this.addComponent(dsGraph);*/
		
		//Add Control Panel
		this.createControlPanel();
		
		//Adding content
		this.createMainContent();
	}

	private void createMainContent() {
		HorizontalLayout hlMain = new HorizontalLayout();
		hlMain.setWidth("100%");
		this.addComponent(hlMain);
		
		//--Cases that we have
		VerticalLayout rightLayout = new VerticalLayout();
		Label zbf = new Label("Last Cases");
		zbf.addStyleName(ValoTheme.LABEL_H3);
		rightLayout.addComponent(zbf);
		
		/*CssLayout patients = new CssLayout();
		patients.setSizeUndefined();
		patients.setStyleName("patients-boxes");*/
		
		try {
			CaseModel cm = new CaseModel();
			ArrayList<Case> alCases = cm.getAllCases(10);
			
			for(Case c : alCases) {
				CasePlaceHolder cph = new CasePlaceHolder(c);
				rightLayout.addComponent(cph);
			}
		} catch (Exception e) {
			
		}
		
		hlMain.addComponent(rightLayout);
		hlMain.setExpandRatio(rightLayout, 1);
	}

	private void createControlPanel() {
		Label actionsLabel = new Label("Quick Actions");
		actionsLabel.addStyleName(ValoTheme.LABEL_H3);
		this.addComponent(actionsLabel);
		
		HorizontalLayout hl = new HorizontalLayout();
		this.addComponent(hl);
		
		Button btnCreate = new Button();
		btnCreate.setCaption("New Case");
		btnCreate.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		btnCreate.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 8780929602616467769L;

			@Override
			public void buttonClick(ClickEvent event) {
				controlActions.createNewCase(event);
			}
		});
		
		hl.addComponent(btnCreate);
	}
	
	public interface ControlActions {
		void createNewCase(ClickEvent event);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	public ControlActions getControlActions() {
		return controlActions;
	}

	public void setControlActions(ControlActions controlActions) {
		this.controlActions = controlActions;
	}
}
