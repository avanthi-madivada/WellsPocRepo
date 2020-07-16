package com.ltts.wellspoc.ui.wizard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.util.PropertiesCache;
import com.ltts.wellspoc.ui.wellselection.WellSelectionModelMgr;
import com.ltts.wellspoc.ui.wellselection.WellSelectionViewMgr;

/**
 * The class is used for Well Selection.
 * 
 * @author Ranjith D
 *
 */
public class WellsPage extends WizardPage implements PropertyChangeListener {
	PropertiesCache prop = PropertiesCache.getInstance();
	// read the title from property file
	String PAGE_TITLE = prop.getProperty("WellsPage_title");
	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();
	boolean isNextEnabled;

	/**
	 * Constructor for Well Selection Page.
	 * 
	 * @param pageName
	 */
	protected WellsPage(String pageName) {
		super(pageName);
		WellSelectionModelMgr.INSTANCE.addChangeListener(this);
	}

	/**
	 * This method is used to create UI for Well Selection Page.
	 */
	@Override
	public void createControl(Composite parent) {
		setTitle(PAGE_TITLE);
		parent = WellSelectionViewMgr.INSTANCE.createWellSelectionViewUI(parent);
		setControl(parent);
	}

	/**
	 * Disables the back button in Well Selection Page.
	 */
	@Override
	public IWizardPage getPreviousPage() {
		return null;
	}

	/**
	 * Enable/disable the Next Button in Well Selection Page.
	 */
	@Override
	public boolean canFlipToNextPage() {
		isNextEnabled = false;
		for (int i = 0; i < wellData.size(); i++) {
			if (wellData.get(i).isChecked()) {
				isNextEnabled = true;
				break;
			}
		}
		return isNextEnabled;
	}

	/**
	 * updates the wizard buttons.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (this.getWizard().getContainer() != null) {
			this.getWizard().getContainer().updateButtons();
		}
	}

	/**
	 * returns addNewWellPage.
	 */
	@Override
	public IWizardPage getNextPage() {
		return WellsWizard.addNewWellPage;

	}

	/**
	 * @return false.
	 */
	@Override
	public boolean isPageComplete() {
		return false;
	}
}