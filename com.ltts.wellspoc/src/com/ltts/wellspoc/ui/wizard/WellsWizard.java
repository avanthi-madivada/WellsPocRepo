package com.ltts.wellspoc.ui.wizard;

import org.eclipse.jface.wizard.Wizard;

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
		// TODO Auto-generated method stub
		return false;
	}

}
