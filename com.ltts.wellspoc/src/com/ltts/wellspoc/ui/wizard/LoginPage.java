package com.ltts.wellspoc.ui.wizard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ltts.wellspoc.ui.login.LoginModelMgr;
import com.ltts.wellspoc.ui.login.LoginViewMgr;
import com.ltts.wellspoc.ui.util.PropertiesCache;

/**
 * The class is used for User Authentication.
 * 
 * @author Ranjith D
 */
public class LoginPage extends WizardPage implements PropertyChangeListener {

	// accessing user name and password
	PropertiesCache prop = PropertiesCache.getInstance();
	private final String USERNAME = prop.getProperty("LoginPage_username");
	private final String PASSWORD = prop.getProperty("LoginPage_password");

	String PAGE_TITLE = prop.getProperty("LoginPage_page_title");
	public Text userNameText = null;
	public Text passwordText = null;

	/**
	 * Constructor for Login
	 * 
	 * @param pageName
	 */
	protected LoginPage(String pageName) {
		super(pageName);
		LoginModelMgr.INSTANCE.addChangeListener(this);
	}

	/**
	 * This method is used to create UI for login page.
	 */
	@Override
	public void createControl(Composite parent) {
		setTitle(PAGE_TITLE);
		LoginViewMgr.INSTANCE.createLoginViewUI(parent);
		setControl(parent);
	}

	/**
	 * Enable/disable the Next Button in Login Page.
	 */
	@Override
	public boolean canFlipToNextPage() {
		if (LoginModelMgr.INSTANCE.getUserModel().getUserName() != null
				&& LoginModelMgr.INSTANCE.getUserModel().getPassword() != null) {
			if (LoginModelMgr.INSTANCE.getUserModel().getUserName().equals(USERNAME)
					&& LoginModelMgr.INSTANCE.getUserModel().getPassword().equals(PASSWORD)) {

				return true;
			}
		}
		return false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		this.getWizard().getContainer().updateButtons();
	}

}