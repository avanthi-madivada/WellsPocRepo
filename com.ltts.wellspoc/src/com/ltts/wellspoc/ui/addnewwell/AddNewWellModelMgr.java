package com.ltts.wellspoc.ui.addnewwell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.util.MessagesUtil;
import com.ltts.wellspoc.ui.views.WellDetailsView;

/**
 * creates well Model instance for AddNewWellPage.
 * 
 * @author Ranjith D
 *
 */
public enum AddNewWellModelMgr {

	INSTANCE;

	Well wellModel;
	AddNewWellUI addNewWellUI;
	boolean isValid;
	boolean isFinishEnabled;
	boolean isValidWellName;
	private List<PropertyChangeListener> wellModelChangeisteners = new ArrayList<PropertyChangeListener>();

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();
	private List<Well> selectedWellsList = new ArrayList<Well>();

	/**
	 * provides well Model instance.
	 * 
	 * @return
	 */
	public Well getWellModel() {
		if (wellModel == null) {
			this.createWellModel();
		}
		return wellModel;
	}

	/**
	 * creates well Model instance.
	 */
	public void createWellModel() {
		if (wellModel == null) {
			wellModel = new Well();
		}

	}

	/**
	 * adds the current instance listeners to wellModelChangeisteners.
	 * 
	 * @param newListener
	 */
	public void addChangeListener(PropertyChangeListener newListener) {
		wellModelChangeisteners.add(newListener);
	}

	/**
	 * updates the model values from UI.
	 */
	public void changeModelFromUI() {

		try {

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.getWellNameText().getText() != null) {
				wellModel.setWellPlanName(AddNewWellViewMgr.INSTANCE.addNewWellUI.getWellNameText().getText());
				notifyListeners(this, "", "", "");
			}

			if (Double.parseDouble(AddNewWellViewMgr.INSTANCE.addNewWellUI.getNorthingText().getText()) != 0.0) {
				wellModel.setNorthing(
						Double.parseDouble((AddNewWellViewMgr.INSTANCE.addNewWellUI.getNorthingText().getText())));
				notifyListeners(this, "", "", "");
			}

			if (Double.parseDouble(AddNewWellViewMgr.INSTANCE.addNewWellUI.getEastingText().getText()) != 0.0) {
				wellModel.setEasting(
						Double.parseDouble((AddNewWellViewMgr.INSTANCE.addNewWellUI.getEastingText().getText())));
				notifyListeners(this, "", "", "");
			}

			if (Double.parseDouble(AddNewWellViewMgr.INSTANCE.addNewWellUI.getAzimuthText().getText()) != 0.0) {
				wellModel.setAzimuth(
						Double.parseDouble((AddNewWellViewMgr.INSTANCE.addNewWellUI.getAzimuthText().getText())));
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.getSelectedField() != null) {
				wellModel.setField(AddNewWellViewMgr.INSTANCE.addNewWellUI.getSelectedField());
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.getSelectedReservoir() != null) {
				wellModel.setReservoir(AddNewWellViewMgr.INSTANCE.addNewWellUI.getSelectedReservoir());
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.getSelectedRadio() != null) {
				wellModel.setType(AddNewWellViewMgr.INSTANCE.addNewWellUI.getSelectedRadio());
				notifyListeners(this, "", "", "");
			}
		} catch (Exception e) {
			MessagesUtil.logError(AddNewWellModelMgr.class.getName(), e.getMessage());
		}
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : wellModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	/**
	 * validates the UI components.
	 * 
	 */
	public boolean isValid() {

		isValid = true;
		addNewWellUI = AddNewWellViewMgr.INSTANCE.getAddNewWellUI();

		if (addNewWellUI.getCheckBoxButton().getSelection() == true) {

			if (addNewWellUI.getWellNameText().getText().isEmpty()
					|| Double.parseDouble((addNewWellUI.getNorthingText().getText())) == 0.0
					|| Double.parseDouble((addNewWellUI.getEastingText().getText())) == 0.0
					|| (addNewWellUI.getAzimuthText().getText().isEmpty()) || addNewWellUI.getSelectedField().isEmpty()
					|| addNewWellUI.getSelectedReservoir().isEmpty() || MessagesUtil.isValid == false) {

				isValid = false;
			}
		}
		return isValid;
	}

	/**
	 * updates the selected wells to the list.
	 */
	private void updateSelectedWells() {

		AddNewWellModelMgr.INSTANCE.selectedWellsList.clear();

		for (int i = 0; i < wellData.size(); i++) {
			if (wellData.get(i).isChecked()) {
				isFinishEnabled = true;
				selectedWellsList.add(wellData.get(i));
			}
		}
	}

	/**
	 * Updates new well details to the selected list and model.
	 */
	private void updateWellDetails() {

		addNewWellUI = AddNewWellViewMgr.INSTANCE.getAddNewWellUI();
		Well well = new Well();

		if (addNewWellUI.getCheckBoxButton().getSelection() == true) {

			well.setWellPlanName(addNewWellUI.getWellNameText().getText());
			well.setEasting(Double.parseDouble(addNewWellUI.getEastingText().getText()));
			well.setNorthing(Double.parseDouble(addNewWellUI.getNorthingText().getText()));
			well.setAzimuth(Double.parseDouble(addNewWellUI.getAzimuthText().getText()));
			well.setField(addNewWellUI.getSelectedField());
			well.setReservoir(addNewWellUI.getSelectedReservoir());
			well.setType(addNewWellUI.getSelectedRadio());
			well.setChecked(true);

			wellData.add(well);
			selectedWellsList.add(well);
			isFinishEnabled = true;

		}
	}

	/**
	 * checks the Well Name's uniqueness and if it is valid updates the well details
	 * and selected wells.
	 * 
	 * @return
	 */
	public boolean finishPressed() {
		isValidWellName = true;
		isFinishEnabled = false;
		if (addNewWellUI.getCheckBoxButton().getSelection() == true) {

			for (int i = 0; i < wellData.size(); i++) {

				if (isValidWellName()) {
					MessagesUtil.displayErrorDialog(addNewWellUI.getWellNameText().getText() + " already exists");
					isValidWellName = false;
					break;
				}
			}
			if (isValidWellName) {

				updateSelectedWells();
				updateWellDetails();
			} else {
				isFinishEnabled = false;
			}
		} else {

			updateSelectedWells();
		}

		IViewPart wellDetailsViewInstance = getWellDetailsViewInstance();
		if (wellDetailsViewInstance instanceof WellDetailsView) {
			((WellDetailsView) wellDetailsViewInstance).setWellData(selectedWellsList);

		}

		return isFinishEnabled;
	}

	/**
	 * return true if the well name is unique.
	 * 
	 * @return
	 */
	public boolean isValidWellName() {
		for (int i = 0; i < wellData.size(); i++) {
			if (wellData.get(i).getWellPlanName().equals(addNewWellUI.getWellNameText().getText())) {
				return true;
			}
		}
		return false;
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
