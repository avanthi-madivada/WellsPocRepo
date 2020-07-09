package com.ltts.wellspoc.ui.wellselection;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.TableItem;

import com.ltts.wellspoc.models.Well;

/**
 * Creates well Model instance.
 * 
 * @author Ranjith D
 *
 */
public enum WellSelectionModelMgr {

	INSTANCE;

	Well wellModel;
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

	public void changeModelFromUI(TableItem item) {
		Well wellData = (Well) item.getData();
		wellData.setChecked(item.getChecked());
		notifyListeners(this, "", "", "");
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : wellModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}
}
