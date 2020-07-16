package com.ltts.wellspoc.ui.wellselection;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.TableItem;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;

/**
 * Creates well Model instance.
 * 
 * @author Ranjith D
 *
 */
public enum WellSelectionModelMgr {

	INSTANCE;

	Well wellModel;
	WellSelectionUI wellSelectionUI;
	WellSelectionUISupport wellSelectionUISupport;
	private List<PropertyChangeListener> wellModelChangeisteners = new ArrayList<PropertyChangeListener>();
	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();

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
	 * updates the isChecked value in the model as per the check box state in UI.
	 * 
	 * @param item
	 */
	public void changeModelFromUI(TableItem item) {
		Well wellData = (Well) item.getData();
		wellData.setChecked(item.getChecked());
		notifyListeners(this, "", "", "");
	}

	/**
	 * updates the checkBoxState value in the model from UI.
	 * 
	 * @param isChecked
	 */
	public void updateModelForCheckBox(boolean isChecked) {
		wellModel.setcheckBoxState(isChecked);
		for (int i = 0; i < wellData.size(); i++) {
			wellData.get(i).setChecked(isChecked);
			notifyListeners(this, "", "", "");
		}
	}

	/**
	 * changes the checkBoxState value to false if any one of the check box is
	 * unchecked in well table and vice-versa.
	 */
	public void changeCheckboxState() {
		wellSelectionUISupport = WellSelectionViewMgr.INSTANCE.getWellSelectionUISupport();

		boolean checkBoxState = true;
		for (int i = 0; i < wellData.size(); i++) {
			if (!(wellData.get(i).isChecked())) {
				checkBoxState = false;
				break;
			}
		}
		wellModel.setcheckBoxState(checkBoxState);
		if (!checkBoxState) {
			wellSelectionUISupport.viewer.getTable().getColumn(0).setImage(wellSelectionUISupport.imageUnChecked);
		} else {
			wellSelectionUISupport.viewer.getTable().getColumn(0).setImage(wellSelectionUISupport.imageChecked);
		}
		notifyListeners(this, "", "", "");
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : wellModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}
}
