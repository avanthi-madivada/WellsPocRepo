package com.ltts.wellspoc.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
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

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();
	Well well = new Well();

	ArrayList<Well> selectedWellsList = new ArrayList<Well>();
	int flag = 0;

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

	/**
	 * Sets the isChecked value to false for all the selected wells on click of
	 * cancel button.
	 */
	@Override
	public boolean performCancel() {
		for (int j = 0; j < wellData.size(); j++) {
			wellData.get(j).setChecked(false);

		}
		return true;
	}

	/**
	 * Checks for the selected wells and stores the corresponding well objects in
	 * the selectedWellsList on click of Finish button.
	 */
	@Override
	public boolean performFinish() {
		if (getContainer().getCurrentPage() == wellsPage) {
			for (int i = 0; i < wellData.size(); i++) {
				if (wellData.get(i).isChecked()) {
					flag = 1;
					selectedWellsList.add(wellData.get(i));

				}
			}
			// To print the values of selectedWellsList - need to be removed in future.
			for (int j = 0; j < selectedWellsList.size(); j++) {
				System.out.println("WellName : " + selectedWellsList.get(j));
			}
		}
		if (flag == 1) {
			return true;
		} else {
			MessagesUtil.displayInformationDialog("Select anyone of the Wells");
			return false;
		}
	}

	/**
	 * Enables Finish button only if the current page is the last page.
	 */

	@Override
	public boolean canFinish() {

		if (getContainer().getCurrentPage() == wellsPage) {
			return true;
		}
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