package com.ltts.wellspoc.ui.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import com.ltts.wellspoc.ui.util.MessagesUtil;

/**
 * 
 * @author
 *
 */
public class WellsWizard extends Wizard  {

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

	/**
	 *  Enables Finish button only if the current page is the last page.	
	 */

    @Override
	public boolean canFinish() {
				
		if(getContainer().getCurrentPage() == wellsPage)
			return true;
		else
			return false;
		
	}
   
    /**
	 * Validates the user name and password on the click of next button.
	 */
    
    @Override
    public IWizardPage getNextPage(IWizardPage page) {
    	
    	if(getContainer().getCurrentPage() == wellsPage)
			return null;
    	
    	 try {
    		  String username_check = LoginPage.userName.getText();
    		    String password_check = LoginPage.password.getText();
    		    if (username_check.contentEquals("admin1234")  && password_check.contentEquals("admin1234") )  {	   
    		        return wellsPage;
    		    }    		  
    		    else if (!username_check.equals("admin1234") && !password_check.equals("admin1234"))   {	   
     			   MessagesUtil.displayErrorDialog("Incorrect username and password");
     			  return null;
    		    }	
    		    else if(!username_check.equals("admin1234")) {
    		    	 MessagesUtil.displayErrorDialog("Incorrect username");
    		    	 return null;
    		    }
    		    else if(!password_check.equals("admin1234")) {
    		    	 MessagesUtil.displayErrorDialog("Incorrect password");
    		    	 return null;
    		    }
    		   
    		    }
    		 catch (Exception e) {
    			  MessagesUtil.logError(LoginPage.class.getName(), "NPE");
    		  }
    	 return null;
    	 
    }

	
	
    
}