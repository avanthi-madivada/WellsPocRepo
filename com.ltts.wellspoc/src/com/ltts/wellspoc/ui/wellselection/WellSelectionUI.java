package com.ltts.wellspoc.ui.wellselection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.ltts.wellspoc.ui.util.PropertiesCache;

public class WellSelectionUI {
	PropertiesCache prop = PropertiesCache.getInstance();
	// read the title from property file
	String PAGE_TITLE = prop.getProperty("WellsPage_title");

	Composite wellSelectionContainer;
//	WellSelectionUISupport wellSelectionUISupport;

	public WellSelectionUI(Composite parent) {

//		setTitle(PAGE_TITLE);

		wellSelectionContainer = new Composite(parent, SWT.FILL | SWT.BORDER);
		GridLayout layout = new GridLayout(1, false);
		wellSelectionContainer.setLayout(layout);
		wellSelectionContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label welltabelLabel = new Label(wellSelectionContainer, SWT.NONE);
		welltabelLabel.setText("Select the wells to view the details:");

//		setControl(wellSelectionContainer);
	}

}
