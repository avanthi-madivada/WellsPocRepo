package com.ltts.wellspoc.ui.addnewwell;

import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.Well;

public enum AddNewWellViewMgr {

	INSTANCE;

	AddNewWellUI addNewWellUI;
	Well wellModel;
	AddNewWellUISupport addNewWellUISupport;
	
	public Composite createAddNewViewMgr(Composite parent) {
		addNewWellUI = new AddNewWellUI(parent);
		wellModel = AddNewWellModelMgr.INSTANCE.getWellModel();
		new AddNewWellUISupport(addNewWellUI, wellModel);
		
		return addNewWellUI.getAddWellPageContainer();
	}

	public AddNewWellUI getAddNewWellUI() {
		return addNewWellUI;
	}

}
