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

import com.ltts.wellspoc.dataprovider.DataProvider;
import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellModelMgr;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellUI;
import com.ltts.wellspoc.ui.login.LoginUI;
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

	WellDetailsView wellView = new WellDetailsView();

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();

	List<Well> selectedWellsList = new ArrayList<Well>();
	Well well = new Well();

	public static List<Well> getSelectedWellsList = new ArrayList<Well>();

	DataProvider dataProvider = new DataProvider();
	boolean isFinishEnabled;
	boolean isEntered;
	LoginUI loginUI;
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
//		if (AddNewWellModelMgr.INSTANCE.isChecked == true) {     // need to find alternate way.
			updateWellDetails();
//			}
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
//				if (AddNewWellModelMgr.INSTANCE.isChecked == true) {  // need to find alternate way.
				if (AddNewWellModelMgr.INSTANCE.getWellModel().getWellPlanName().isEmpty()
						|| (AddNewWellModelMgr.INSTANCE.getWellModel().getNorthing()) == 0.0
						|| (AddNewWellModelMgr.INSTANCE.getWellModel().getEasting()) == 0.0
						|| (AddNewWellModelMgr.INSTANCE.getWellModel().getAzimuth()) == 0.0
						|| (AddNewWellModelMgr.INSTANCE.getWellModel().getField().isEmpty())
						|| (AddNewWellModelMgr.INSTANCE.getWellModel().getReservoir().isEmpty())
						|| MessagesUtil.isValid == false) {

					return isFinishEnabled;

				} else {
					isFinishEnabled = true;
				}
			} else {
				isFinishEnabled = true;
			}
//		} 
		} catch (Exception e) {
			MessagesUtil.logError(AddNewWellUI.class.getName(), e.getMessage());
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
			return wellsPage;
		}
		return null;
	}

	/**
	 * Updates model instance with data from UI.
	 */
	private void updateWellDetails() {
//		well.setWellPlanName(addNewWellUI.wellNameText.getText());
//		well.setEasting(Double.parseDouble(addNewWellUI.eastingText.getText()));
//		well.setNorthing(Double.parseDouble(addNewWellUI.northingText.getText()));
//		well.setAzimuth(Double.parseDouble(addNewWellUI.azimuthText.getText()));
//		well.setField(addNewWellUI.selectedField);
//		well.setReservoir(addNewWellUI.selectedReservoir);
//		well.setType(addNewWellUI.selectedRadio);

		well.setWellPlanName(AddNewWellModelMgr.INSTANCE.getWellModel().getWellPlanName());
		well.setEasting(AddNewWellModelMgr.INSTANCE.getWellModel().getNorthing());
		well.setNorthing(AddNewWellModelMgr.INSTANCE.getWellModel().getEasting());
		well.setAzimuth(AddNewWellModelMgr.INSTANCE.getWellModel().getAzimuth());
		well.setField(AddNewWellModelMgr.INSTANCE.getWellModel().getField());
		well.setReservoir(AddNewWellModelMgr.INSTANCE.getWellModel().getReservoir());
		well.setType(AddNewWellModelMgr.INSTANCE.getWellModel().getType());

		wellData.add(well);
		selectedWellsList.add(well);
		isFinishEnabled = true;
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