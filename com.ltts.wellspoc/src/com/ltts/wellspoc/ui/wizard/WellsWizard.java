package com.ltts.wellspoc.ui.wizard;





import org.eclipse.jface.wizard.Wizard;

import com.ltts.wellspoc.ui.util.MessagesUtil;




/**
 * 
 * @author
 *
 */
public class WellsWizard extends Wizard {

	LoginPage loginPage;
	WellsPage wellsPage;
	
	/**
	 * Provides the title for the wizard.
	 */
	@Override
	public String getWindowTitle() {
		return "Wells Wizard";
	}

	@Override
	public void addPages() {
		loginPage = new LoginPage("Login Page");
		wellsPage = new WellsPage("");
		addPage(loginPage);
		addPage(wellsPage);

	}

	@Override
	public boolean performFinish() {
			return true;
	}


//Enables Finish button only if the current page is the last page.	
    @Override
	public boolean canFinish() {
				
		if(getContainer().getCurrentPage() == wellsPage)
			return true;
		else
			return false;
	}
   
   
    public  boolean canNext() {	    
	    String username_check = LoginPage.userName.getText();
	    String password_check = LoginPage.password.getText();
	    if (username_check.contentEquals("admin1234")  && password_check.contentEquals("admin1234") && (!(username_check.isEmpty())) && (!(password_check.isEmpty())))  {	   
	        return true;
	    }
	    else {
	    	 MessagesUtil.logError(LoginPage.class.getName(),"NPE");
	    	 return false;
	    }
	   
    }
}
