package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views.stateful;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class StateLogicView extends VerticalLayout implements View {
	private static final long serialVersionUID = -2519141530202292949L;
	public static final String NAME = "New Case";
	
	public final StateContext sc = new StateContext();
	public VerticalLayout vlMain = new VerticalLayout();
	
	public StateLogicView() {
		//TODO Breadcrumbs
		
		//Replaceable content
		vlMain.setWidth("100%");
		this.addComponent(vlMain);
		
		sc.invokeView(vlMain);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
