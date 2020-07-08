package com.ltts.wellspoc.ui.wellselection;

import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.Well;

public enum WellSelectionViewMgr {

	INSTANCE;

	WellSelectionUI wellSelectionUI;
	Well wellModel;

	public Composite createWellSelectionViewUI(Composite parent) {

		wellSelectionUI = new WellSelectionUI(parent);
		wellModel = WellSelectionModelMgr.INSTANCE.getWellModel();
		new WellSelectionUISupport(wellSelectionUI, wellModel);
		
		return wellSelectionUI.getWellSelectionContainer();
	}

	public WellSelectionUI getWellSelectionUI() {
		return wellSelectionUI;
	}

}
