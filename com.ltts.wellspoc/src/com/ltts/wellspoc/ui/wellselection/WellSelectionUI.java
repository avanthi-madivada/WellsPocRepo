package com.ltts.wellspoc.ui.wellselection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Class to create UI widgets for Well Selection Page.
 * 
 * @author Ranjith D
 *
 */
public class WellSelectionUI {

	Composite wellSelectionContainer;
	Composite selectionContainer;
	Button checkBoxButton;

	/**
	 * Creates UI widgets
	 * 
	 * @param parent
	 */
	public WellSelectionUI(Composite parent) {

		wellSelectionContainer = new Composite(parent, SWT.FILL | SWT.BORDER);
		GridLayout layout = new GridLayout(1, false);
		wellSelectionContainer.setLayout(layout);
		wellSelectionContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		selectionContainer = new Composite(wellSelectionContainer, SWT.FILL);
		GridLayout selectionLayout = new GridLayout(2, false);
		selectionContainer.setLayout(selectionLayout);

		checkBoxButton = new Button(selectionContainer, SWT.CHECK);

		Label welltabelLabel = new Label(selectionContainer, SWT.NONE);
		welltabelLabel.setText("Select the wells to view the details:");

	}

	public GridData getGridData() {
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.heightHint = 100;
		return gridData;
	}

	/**
	 * @return checkBoxButton
	 */
	public Button getCheckBoxButton() {
		return checkBoxButton;
	}

	/**
	 * @return wellSelectionContainer
	 */
	public Composite getWellSelectionContainer() {
		return wellSelectionContainer;
	}

}
