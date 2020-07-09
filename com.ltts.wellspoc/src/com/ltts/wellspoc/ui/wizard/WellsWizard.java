package com.ltts.wellspoc.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellUI;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellViewMgr;
import com.ltts.wellspoc.ui.util.MessagesUtil;
import com.ltts.wellspoc.ui.views.WellDetailsView;

/**
 * Class that re-implements the methods to perform special processing for the
 * wizard.
 * 
 * @author Ranjith D
 *
 */

public class WellsWizard extends Wizard {

	LoginPage loginPage;
	WellsPage wellsPage;
	AddNewWellPage addNewWellPage;

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();
	List<Well> selectedWellsList = new ArrayList<Well>();
	Well well = new Well();
	public static List<Well> getSelectedWellsList = new ArrayList<Well>();

	boolean isFinishEnabled;
	AddNewWellUI addNewWellUI;

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
		isFinishEnabled = false;
		if (getContainer().getCurrentPage() == addNewWellPage) {
			for (int i = 0; i < wellData.size(); i++) {
				if (wellData.get(i).isChecked()) {
					isFinishEnabled = true;
					selectedWellsList.add(wellData.get(i));
					wellData.get(i).setChecked(false);
				}
			}
			updateWellDetails();
		}

		IViewPart wellDetailsViewInstance = getWellDetailsViewInstance();
		if (wellDetailsViewInstance instanceof WellDetailsView) {
			((WellDetailsView) wellDetailsViewInstance).setWellData(selectedWellsList);
		}

		return isFinishEnabled;
	}

	/**
	 * Enable/disable the finish button in Add New Well Page.
	 */
	@Override
	public boolean canFinish() {
		isFinishEnabled = false;
		if (getContainer().getCurrentPage() == loginPage || getContainer().getCurrentPage() == wellsPage) {
			return isFinishEnabled;
		}
		try {
			if (getContainer().getCurrentPage() == addNewWellPage) {
				if (addNewWellPage.isValid()) {

					isFinishEnabled = true;
				}
			}
		} catch (Exception e) {
			MessagesUtil.logError(AddNewWellPage.class.getName(), e.getMessage());
		}
		return isFinishEnabled;
	}

	/**
	 * Validates the user name and password and returns the next page accordingly.
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (getContainer().getCurrentPage() == wellsPage) {
			return addNewWellPage;
		}
		if (getContainer().getCurrentPage() == loginPage) {
			if (loginPage.isValid()) {
				return wellsPage;
			}
		}
		return null;
	}

	/**
	 * Updates model instance with data from UI.
	 */
	protected void updateWellDetails() {

		addNewWellUI = AddNewWellViewMgr.INSTANCE.getAddNewWellUI();

		if (addNewWellUI.getCheckBoxButton().getSelection() == true) {
			well.setWellPlanName(addNewWellUI.wellNameText.getText());
			well.setEasting(Double.parseDouble(addNewWellUI.eastingText.getText()));
			well.setNorthing(Double.parseDouble(addNewWellUI.northingText.getText()));
			well.setAzimuth(Double.parseDouble(addNewWellUI.azimuthText.getText()));
			well.setField(addNewWellUI.selectedField);
			well.setReservoir(addNewWellUI.selectedReservoir);
			well.setType(addNewWellUI.selectedRadio);

			wellData.add(well);
			selectedWellsList.add(well);
			isFinishEnabled = true;
		}
	}

	private IViewPart getWellDetailsViewInstance() {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = workbenchWindow.getActivePage();

		try {
			IViewPart viewPart = activePage.showView("com.ltts.wellspoc.welldetailsview");
			return viewPart;
		} catch (PartInitException e) {
			return null;
		}
	}
}