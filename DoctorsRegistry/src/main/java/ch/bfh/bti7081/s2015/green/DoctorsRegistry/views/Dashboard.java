package ch.bfh.bti7081.s2015.green.DoctorsRegistry.views;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot
public class Dashboard extends VerticalLayout implements View{
	
	public static final String NAME = "dashboard";
	public static final String MENUNAME = "Login";
	
	Table table;
	
	public Dashboard() {
		Design.read(this);
		
		
		// Define two columns for the built-in container
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Mag",  Float.class, null);

		// Add a row the hard way
		Object newItemId = table.addItem();
		Item row1 = table.getItem(newItemId);
		row1.getItemProperty("Name").setValue("Sirius");
		row1.getItemProperty("Mag").setValue(-1.46f);

		// Add a few other rows using shorthand addItem()
		table.addItem(new Object[]{"Canopus",        -0.72f}, 2);
		table.addItem(new Object[]{"Arcturus",       -0.04f}, 3);
		table.addItem(new Object[]{"Alpha Centauri", -0.01f}, 4);


		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
