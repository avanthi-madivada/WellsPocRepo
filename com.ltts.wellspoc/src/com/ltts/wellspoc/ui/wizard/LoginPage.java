package com.ltts.wellspoc.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Info about this class
 * 
 * @author
 *
 */
public class LoginPage extends WizardPage {

	private Composite container;

	protected LoginPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.FILL | SWT.TAB);
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// swt widgets
		Label dummyLabel = new Label(container, SWT.NONE);
		dummyLabel.setText("dummy label. can remove");
		GridData dummyLabelData = new GridData(SWT.CENTER, SWT.FILL, true, false);
		dummyLabel.setLayoutData(dummyLabelData);

		setControl(container);

	}

}
