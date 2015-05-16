package ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers;

public class MenuItem {
		private String viewName;
		private String viewMenuName;
		
		public MenuItem(String viewName, String viewMenuName) {
			this.viewName = viewName;
			this.viewMenuName = viewMenuName;
		}
		
		public String getViewName() {
			return viewName;
		}
		public void setViewName(String viewName) {
			this.viewName = viewName;
		}
		public String getViewMenuName() {
			return viewMenuName;
		}
		public void setViewMenuName(String viewMenuName) {
			this.viewMenuName = viewMenuName;
		}
		
		
}
