package com.ltts.wellspoc.ui.wizard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.wizard.IWizardPage;
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
		parent = LoginViewMgr.INSTANCE.createLoginViewUI(parent);
		setControl(parent);

	}

	/**
	 * Enable/disable the Next Button in Login Page.
	 */
	@Override
	public boolean canFlipToNextPage() {
		if (LoginModelMgr.INSTANCE.getUserModel().getUserName() != null
				&& LoginModelMgr.INSTANCE.getUserModel().getPassword() != null) {
			if (!LoginModelMgr.INSTANCE.getUserModel().getUserName().isEmpty()
					&& !LoginModelMgr.INSTANCE.getUserModel().getPassword().isEmpty()) {

				return true;
			}
		}
		return false;
	}

	/**
	 * updates the wizard buttons.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (this.getWizard().getContainer() != null) {
			this.getWizard().getContainer().updateButtons();
		}
	}

	/**
	 * Validates the user and returns the next page.
	 */
	@Override
	public IWizardPage getNextPage() {
		if (LoginModelMgr.INSTANCE.isValid(LoginModelMgr.INSTANCE.getUserModel().getUserName(),
				LoginModelMgr.INSTANCE.getUserModel().getPassword())) {
			return WellsWizard.wellsPage;
		}
		return null;
	}

	/**
	 * @return false
	 */
	@Override
	public boolean isPageComplete() {
		return false;
	}
}