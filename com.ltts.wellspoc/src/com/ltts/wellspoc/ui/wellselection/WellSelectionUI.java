package com.ltts.wellspoc.ui.wellselection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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

		Label welltabelLabel = new Label(wellSelectionContainer, SWT.NONE);
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
	 * @return wellSelectionContainer
	 */
	public Composite getWellSelectionContainer() {
		return wellSelectionContainer;
	}
}
