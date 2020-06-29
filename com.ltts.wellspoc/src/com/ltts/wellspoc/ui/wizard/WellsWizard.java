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
	Composite parent;
	
	WellDetailsView wellView = new WellDetailsView();

	//accessing username and password
	PropertiesCache prop = PropertiesCache.getInstance();
	private final String USERNAME=prop.getProperty("LoginPage_username");
	private final String PASSWORD=prop.getProperty("LoginPage_password");

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();

	List<Well> selectedWellsList = new ArrayList<Well>();
	
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
		isFinishEnabled = false;
		if (getContainer().getCurrentPage() == wellsPage) {
			for (int i = 0; i < wellData.size(); i++) {
				if (wellData.get(i).isChecked()) {
					flag = 1;
					selectedWellsList.add(wellData.get(i)); 
					wellData.get(i).setChecked(false);
				}
			}
			for (int j = 0; j < wellData.size(); j++) {
				wellData.get(j).setChecked(false);
			}
		}
		
		IViewPart wellDetailsViewInstance = getWellDetailsViewInstance();
		if (wellDetailsViewInstance instanceof WellDetailsView) {
			((WellDetailsView)wellDetailsViewInstance).setWellData(selectedWellsList);
		}
		
		if (flag == 1) {
			return true;
		} else {
			MessagesUtil.displayInformationDialog("Select anyone of the Wells");
			return false;
		}
	}

	/**
	 * Enables Finish button when any one of the wells is selected.
	 */

	@Override
	public boolean canFinish() {
		isFinishEnabled = false;
		for (int i = 0; i < wellData.size(); i++) {
			if (wellData.get(i).isChecked()) {
				isFinishEnabled = true;
				break;
			}
		}
		return isFinishEnabled;

	}

	/**
	 * Validates the user name and password on the click of next button and returns
	 * the next page if both user name and password are valid if not it displays the
	 * error dialog accordingly.
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (getContainer().getCurrentPage() == wellsPage) {
			return null;

		}
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
		return null;
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