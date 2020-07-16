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
	public List<Well> selectedWellsList = new ArrayList<Well>();

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

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.wellNameText.getText() != null) {
				wellModel.setWellPlanName(AddNewWellViewMgr.INSTANCE.addNewWellUI.wellNameText.getText());
				notifyListeners(this, "", "", "");
			}

			if (Double.parseDouble(AddNewWellViewMgr.INSTANCE.addNewWellUI.northingText.getText()) != 0.0) {
				wellModel.setNorthing(
						Double.parseDouble((AddNewWellViewMgr.INSTANCE.addNewWellUI.northingText.getText())));
				notifyListeners(this, "", "", "");
			}

			if (Double.parseDouble(AddNewWellViewMgr.INSTANCE.addNewWellUI.eastingText.getText()) != 0.0) {
				wellModel.setEasting(
						Double.parseDouble((AddNewWellViewMgr.INSTANCE.addNewWellUI.eastingText.getText())));
				notifyListeners(this, "", "", "");
			}

			if (Double.parseDouble(AddNewWellViewMgr.INSTANCE.addNewWellUI.azimuthText.getText()) != 0.0) {
				wellModel.setAzimuth(
						Double.parseDouble((AddNewWellViewMgr.INSTANCE.addNewWellUI.azimuthText.getText())));
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.fieldCombo.getText() != null) {
				wellModel.setField(AddNewWellViewMgr.INSTANCE.addNewWellUI.fieldCombo.getText());
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.reservoirCombo.getText() != null) {
				wellModel.setReservoir(AddNewWellViewMgr.INSTANCE.addNewWellUI.reservoirCombo.getText());
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeHorizontalRadio.getSelection() == true) {
				wellModel.setType(AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeHorizontalRadio.getText());
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeVerticalRadio.getSelection() == true) {
				wellModel.setType(AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeVerticalRadio.getText());
				notifyListeners(this, "", "", "");
			}
			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeDeviatedRadio.getSelection() == true) {
				wellModel.setType(AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeDeviatedRadio.getText());
				notifyListeners(this, "", "", "");
			}
			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeSWellRadio.getSelection() == true) {
				wellModel.setType(AddNewWellViewMgr.INSTANCE.addNewWellUI.wellTypeSWellRadio.getText());
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
					|| (addNewWellUI.getAzimuthText().getText().isEmpty())
					|| addNewWellUI.fieldCombo.getText().isEmpty() || addNewWellUI.reservoirCombo.getText().isEmpty()
					|| MessagesUtil.isValid == false) {

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

			well.setWellPlanName(addNewWellUI.wellNameText.getText());
			well.setEasting(Double.parseDouble(addNewWellUI.eastingText.getText()));
			well.setNorthing(Double.parseDouble(addNewWellUI.northingText.getText()));
			well.setAzimuth(Double.parseDouble(addNewWellUI.azimuthText.getText()));
			well.setField(addNewWellUI.fieldCombo.getText());
			well.setReservoir(addNewWellUI.reservoirCombo.getText());
			well.setType(setWellType());
			well.setChecked(true);

			wellData.add(well);
			selectedWellsList.add(well);
			isFinishEnabled = true;

		}
	}

	/**
	 * checks for the selected radio button and returns the appropriate text.
	 * 
	 * @return
	 */
	private String setWellType() {
		if (addNewWellUI.wellTypeHorizontalRadio.getSelection() == true) {
			return addNewWellUI.wellTypeHorizontalRadio.getText();
		}
		if (addNewWellUI.wellTypeVerticalRadio.getSelection() == true) {
			return addNewWellUI.wellTypeVerticalRadio.getText();
		}
		if (addNewWellUI.wellTypeDeviatedRadio.getSelection() == true) {
			return addNewWellUI.wellTypeDeviatedRadio.getText();
		}
		if (addNewWellUI.wellTypeSWellRadio.getSelection() == true) {
			return addNewWellUI.wellTypeSWellRadio.getText();
		}
		return null;
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
					MessagesUtil.displayErrorDialog(addNewWellUI.wellNameText.getText() + " already exists");
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
			if (wellData.get(i).getWellPlanName().equals(addNewWellUI.wellNameText.getText())) {
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
