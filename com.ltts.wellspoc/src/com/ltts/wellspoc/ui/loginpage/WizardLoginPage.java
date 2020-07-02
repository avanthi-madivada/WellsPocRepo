package com.ltts.wellspoc.ui.loginpage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.ui.util.PropertiesCache;

public class WizardLoginPage extends WizardPage {
	
	PropertiesCache prop = PropertiesCache.getInstance();	 
	//read the title from property file
	String PAGE_TITLE = prop.getProperty("LoginPage_page_title");
	
	public WizardLoginPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {	
		
		LoginPageViewMgr.INSTANCE.createLoginPageViewUI(parent, this);
		setControl(parent);
		setTitle(PAGE_TITLE);
	}

	/**
	 * Enable/disable the Next Button in Login Page.
	 */
	@Override
	public boolean canFlipToNextPage() {
//		if (LoginPageUI.userNameText.getText().isEmpty() || LoginPageUI.passwordText.getText().isEmpty()) {
//			return false;
//		} else {
//			return true;
//		}
//  Enabled the next button to check. Must be replaced with the above commented statements.
		return true;
	}
	
}
