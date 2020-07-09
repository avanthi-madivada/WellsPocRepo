package com.ltts.wellspoc.ui.wizard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.ui.addnewwell.AddNewWellModelMgr;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellUI;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellViewMgr;
import com.ltts.wellspoc.ui.util.MessagesUtil;
import com.ltts.wellspoc.ui.util.PropertiesCache;

/**
 * The class is used to add new well.
 * 
 * @author Ranjith D
 *
 */
public class AddNewWellPage extends WizardPage implements PropertyChangeListener {

	PropertiesCache prop = PropertiesCache.getInstance();
	// read the title from property file
	String PAGE_TITLE = prop.getProperty("AddNewWellPage_title");

	boolean isFinishEnabled;
	AddNewWellUI addNewWellUI;

	/**
	 * Constructor for AddNewWellPage
	 * 
	 * @param pageName
	 */
	protected AddNewWellPage(String pageName) {
		super(pageName);
		AddNewWellModelMgr.INSTANCE.addChangeListener(this);
	}

	/**
	 * This method is used to create UI for Add New Well Page.
	 */
	@Override
	public void createControl(Composite parent) {

		setTitle(PAGE_TITLE);
		parent = AddNewWellViewMgr.INSTANCE.createAddNewViewMgr(parent);
		setControl(parent);
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
	 * validates the UI components.
	 * 
	 */
	public boolean isValid() {

		addNewWellUI = AddNewWellViewMgr.INSTANCE.getAddNewWellUI();
		if (addNewWellUI.getCheckBoxButton().getSelection() == true) {
			if (addNewWellUI.getWellNameText().getText().isEmpty()
					|| Double.parseDouble((addNewWellUI.getNorthingText().getText())) == 0.0
					|| Double.parseDouble((addNewWellUI.getEastingText().getText())) == 0.0
					|| Double.parseDouble((addNewWellUI.getAzimuthText().getText())) == 0.0
					|| addNewWellUI.getSelectedField().isEmpty() || addNewWellUI.getSelectedReservoir().isEmpty()
					|| MessagesUtil.isValid == false) {

				isFinishEnabled = false;
			} else {
				isFinishEnabled = true;
			}
		} else {
			isFinishEnabled = true;
		}
		return isFinishEnabled;
	}

}