package com.ltts.wellspoc.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ltts.wellspoc.ui.login.LoginUI;
import com.ltts.wellspoc.ui.login.LoginViewMgr;
import com.ltts.wellspoc.ui.util.MessagesUtil;
import com.ltts.wellspoc.ui.util.PropertiesCache;

/**
 * The class is used for User Authentication.
 * 
 * @author Ranjith D
 */
public class LoginPage extends WizardPage {
	
	LoginPage loginPage;
	PropertiesCache prop = PropertiesCache.getInstance();	 
	//read the title from property file
	String PAGE_TITLE = prop.getProperty("LoginPage_page_title");

	/**
	 * Constructor for Login
	 * 
	 * @param pageName
	 */
	protected LoginPage(String pageName) {
		super(pageName);
	}
	
	/**
	 * This method is used to create UI for login page.
	 */
	@Override
	public void createControl(Composite parent) {
		setTitle(PAGE_TITLE);
		LoginViewMgr.INSTANCE.createLoginViewUI(parent, loginPage);
		setControl(parent);
	}

	/**
	 * Enable/disable the Next Button in Login Page.
	 */
	@Override
	public boolean canFlipToNextPage() {
		if (LoginUI.userNameText.getText().isEmpty() || LoginUI.passwordText.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
}