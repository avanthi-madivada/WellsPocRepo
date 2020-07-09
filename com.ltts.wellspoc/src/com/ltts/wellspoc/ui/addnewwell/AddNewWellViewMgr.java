package com.ltts.wellspoc.ui.addnewwell;

import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.Well;

/**
 * Instantiates UI for AddNewWellPage.
 * @author Ranjith D
 *
 */
public enum AddNewWellViewMgr {

	INSTANCE;

	AddNewWellUI addNewWellUI;
	Well wellModel;
	
	/**
	 * instantiates model instance and updates UI with the values.
	 * 
	 * @param parent
	 * @return
	 */
	public Composite createAddNewViewMgr(Composite parent) {
		addNewWellUI = new AddNewWellUI(parent);
		wellModel = AddNewWellModelMgr.INSTANCE.getWellModel();
		new AddNewWellUISupport(addNewWellUI, wellModel);
		
		return addNewWellUI.getAddWellPageContainer();
	}

	/**
	 * get AddNewWellUI
	 * @return
	 */
	public AddNewWellUI getAddNewWellUI() {
		return addNewWellUI;
	}

}
