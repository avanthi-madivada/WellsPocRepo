package com.ltts.wellspoc.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ltts.wellspoc.dataprovider.DataProvider;
import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.util.MessagesUtil;
import com.ltts.wellspoc.ui.views.WellDetailsView;
import com.ltts.wellspoc.ui.util.PropertiesCache;

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
	Composite parent;
	
	WellDetailsView wellView = new WellDetailsView();

	//accessing user name and password
	PropertiesCache prop = PropertiesCache.getInstance();
	private final String USERNAME=prop.getProperty("LoginPage_username");
	private final String PASSWORD=prop.getProperty("LoginPage_password");

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();

	List<Well> selectedWellsList = new ArrayList<Well>();
	Well well = new Well();

	public static List<Well> getSelectedWellsList = new ArrayList<Well>();
	
	DataProvider dataProvider = new DataProvider();
	int flag = 0;
	boolean isFinishEnabled;

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
			for (int j = 0; j < selectedWellsList.size(); j++) {
				System.out.println(selectedWellsList.size());
				System.out.println(selectedWellsList.get(j).getWellPlanName());
				System.out.println(selectedWellsList.get(j).getAzimuth());
				System.out.println(selectedWellsList.get(j).getEasting());
				System.out.println(selectedWellsList.get(j).getNorthing());
				System.out.println("reservoir : "+selectedWellsList.get(j).getReservoir());
				System.out.println(selectedWellsList.get(j).getType());
				System.out.println(selectedWellsList.get(j).getField());
			}
		}
		
		IViewPart wellDetailsViewInstance = getWellDetailsViewInstance();
		if (wellDetailsViewInstance instanceof WellDetailsView) {
			((WellDetailsView)wellDetailsViewInstance).setWellData(selectedWellsList);
		}
		
		return isFinishEnabled;
	}

	/**
	 * Enable/disable the finish button in Add New Well Page.
	 */

	@Override
	public boolean canFinish() {
		isFinishEnabled = false;
		try {
			if (getContainer().getCurrentPage() == wellsPage) {
				return isFinishEnabled;
			}
		} catch (Exception e) {
			MessagesUtil.logError(WellsPage.class.getName(), e.getMessage());

		}
		if (AddNewWellPage.checkBoxButton.getSelection() == true) {
			try {
				if (AddNewWellPage.wellNameText.getText().isEmpty() || AddNewWellPage.northingText.getValue() == 0.0
						|| AddNewWellPage.eastingText.getValue() == 0.0 || AddNewWellPage.azimuthText.getValue() == 0.0
						|| AddNewWellPage.selectedField.isEmpty() || AddNewWellPage.selectedReservoir.isEmpty()) {
					return isFinishEnabled;

				} else {
					isFinishEnabled = true;
				}
			}

			catch (Exception e) {
				MessagesUtil.logError(AddNewWellPage.class.getName(), e.getMessage());

			}
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
			try {
				String userNameCheck = LoginPage.userNameText.getText();
				String passwordCheck = LoginPage.passWordText.getText();

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
		}
		return null;
	}
	
	/**
	 * Updates model instance with data from UI.
	 */
	public void updateWellDetails() {

		well.setWellPlanName(AddNewWellPage.wellNameText.getText());
		well.setEasting(AddNewWellPage.eastingText.getValue());
		well.setNorthing(AddNewWellPage.northingText.getValue());
		well.setAzimuth(AddNewWellPage.azimuthText.getValue());
		well.setField(AddNewWellPage.selectedField);
		well.setReservoir(AddNewWellPage.selectedReservoir);
		well.setType(AddNewWellPage.selectedRadio);
		
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
            String message = "Could not show view " + "Well Details View";
//            LOG.warn(message, e);
            return null;
        }
	}
	

}