package com.ltts.wellspoc.ui.wellselection;

import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.Well;

/**
 * Instantiates UI for Well Selection Page.
 * 
 * @author Ranjith D
 *
 */
public enum WellSelectionViewMgr {

	INSTANCE;

	WellSelectionUI wellSelectionUI;
	Well wellModel;
	WellSelectionUISupport wellSelectionUISupport;

	/**
	 * instantiates model instance and updates UI with the values.
	 * 
	 * @param parent
	 * @return
	 */
	public Composite createWellSelectionViewUI(Composite parent) {

		wellSelectionUI = new WellSelectionUI(parent);
		wellModel = WellSelectionModelMgr.INSTANCE.getWellModel();
		wellSelectionUISupport = new WellSelectionUISupport(wellSelectionUI, wellModel);

		return wellSelectionUI.getWellSelectionContainer();
	}

	/**
	 * get WellSelectionUI
	 * 
	 * @return
	 */
	public WellSelectionUI getWellSelectionUI() {
		return wellSelectionUI;
	}

	public WellSelectionUISupport getWellSelectionUISupport() {
		return wellSelectionUISupport;
	}

}
