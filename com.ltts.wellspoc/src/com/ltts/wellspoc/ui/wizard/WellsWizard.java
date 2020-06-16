package com.ltts.wellspoc.ui.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;

import com.ltts.wellspoc.ui.util.MessagesUtil;

/**
 *
 * @author
 *
 */
public class WellsWizard extends Wizard {

	LoginPage loginPage;
	WellsPage wellsPage;

	private static final String USERNAME = "admin1234";
	private static final String PASSWORD = "admin1234";

	/**
	 * Provides the title for the wizard.
	 */
	@Override
	public String getWindowTitle() {

		return "Well Selection Wizard";

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
	 * Enables Finish button only if the current page is the last page.
	 */

	@Override
	public boolean canFinish() {

		if (getContainer().getCurrentPage() == wellsPage)
			return true;
		else
			return false;

	}

	/**
	 * Validates the user name and password on the click of next button and returns
	 * the next page if both user name and password are valid if not it displays the
	 * error dialog accordingly.
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {

		if (getContainer().getCurrentPage() == wellsPage)
			return null;

		try {
			String userNameCheck = LoginPage.userName.getText();
			String passwordCheck = LoginPage.password.getText();

			if (userNameCheck.contentEquals(USERNAME) && passwordCheck.contentEquals(PASSWORD)) {
				return wellsPage;
			} else if (!userNameCheck.equals(USERNAME) && !passwordCheck.equals(PASSWORD)) {
				MessagesUtil.displayErrorDialog("Incorrect username and password");

			} else if (!userNameCheck.equals(USERNAME)) {
				MessagesUtil.displayErrorDialog("Incorrect username");

			} else if (!passwordCheck.equals(PASSWORD)) {
				MessagesUtil.displayErrorDialog("Incorrect password");

			}

		} catch (Exception e) {

			MessagesUtil.logError(LoginPage.class.getName(), e.getMessage());
		}

		return null;

	}

}