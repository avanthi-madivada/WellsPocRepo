package com.ltts.wellspoc.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.Wizard;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellModelMgr;
import com.ltts.wellspoc.ui.util.MessagesUtil;

/**
 * Class that re-implements the methods to perform special processing for the
 * wizard.
 * 
 * @author Ranjith D
 *
 */

public class WellsWizard extends Wizard {

	LoginPage loginPage;
	static WellsPage wellsPage;
	static AddNewWellPage addNewWellPage;

	public static List<Well> getSelectedWellsList = new ArrayList<Well>();

	boolean isFinishEnabled;
	boolean isValid;

	/**
	 * Provides the title for the wizard.
	 */
	@Override
	public String getWindowTitle() {
		return "Well Selection Wizard";
	}

	/**
	 * Adds the pages before the wizard opens.
	 */
	@Override
	public void addPages() {
		loginPage = new LoginPage("Login Page");
		wellsPage = new WellsPage("Well Selection");
		addNewWellPage = new AddNewWellPage("Add New Well Page");
		addPage(loginPage);
		addPage(wellsPage);
		addPage(addNewWellPage);
	}

	/**
	 * Checks for the selected wells and stores the corresponding well objects in
	 * the selectedWellsList on click of Finish button.
	 * 
	 */
	@Override
	public boolean performFinish() {
		isFinishEnabled = false;
		if (getContainer().getCurrentPage().isPageComplete()) {
			if (AddNewWellModelMgr.INSTANCE.finishPressed()) {
				isFinishEnabled = true;
			}
		}

		return isFinishEnabled;
	}

	/**
	 * Enable/disable the finish button in Add New Well Page.
	 */
	@Override
	public boolean canFinish() {
		isValid = false;

		if (getContainer().getCurrentPage().isPageComplete()) {
			try {
				if (AddNewWellModelMgr.INSTANCE.isValid()) {
					isValid = true;
				}

			} catch (Exception e) {
				MessagesUtil.logError(AddNewWellPage.class.getName(), e.getMessage());
			}
		}
		return isValid;
	}

}