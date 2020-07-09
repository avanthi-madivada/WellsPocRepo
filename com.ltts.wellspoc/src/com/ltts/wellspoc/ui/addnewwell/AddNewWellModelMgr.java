package com.ltts.wellspoc.ui.addnewwell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.ui.util.MessagesUtil;

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
	private List<PropertyChangeListener> wellModelChangeisteners = new ArrayList<PropertyChangeListener>();

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

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.selectedField != null) {
				wellModel.setField(AddNewWellViewMgr.INSTANCE.addNewWellUI.selectedField);
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.selectedReservoir != null) {
				wellModel.setReservoir(AddNewWellViewMgr.INSTANCE.addNewWellUI.selectedReservoir);
				notifyListeners(this, "", "", "");
			}

			if (AddNewWellViewMgr.INSTANCE.addNewWellUI.selectedRadio != null) {
				wellModel.setType(AddNewWellViewMgr.INSTANCE.addNewWellUI.selectedRadio);
				notifyListeners(this, "", "", "");
			}
		} catch (Exception e) {
			MessagesUtil.logError(AddNewWellUISupport.class.getName(), e.getMessage());
		}
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : wellModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

}
