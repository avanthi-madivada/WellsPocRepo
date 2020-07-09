package com.ltts.wellspoc.ui.login;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Class to create UI widgets for login page.
 * 
 * @author Ranjith D
 *
 */
public class LoginUI {
	public Text userNameText;
	public Text passwordText;
	public Composite userAuthenticationContainer;

	/**
	 * creates UI for login page.
	 */
	public LoginUI(Composite parent) {

		userAuthenticationContainer = new Composite(parent, SWT.NULL | SWT.BORDER);
		GridLayout layout = new GridLayout(2, true);
		layout.marginHeight = 150;
		userAuthenticationContainer.setLayout(layout);
		userAuthenticationContainer.setLayoutData(new GridData(GridData.FILL_BOTH));

		// User Name
		Label userNameLabel = new Label(userAuthenticationContainer, SWT.NONE);
		userNameLabel.setText("Username");
		GridData gridDataUserNameLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridDataUserNameLabel.widthHint = 65;
		userNameLabel.setLayoutData(gridDataUserNameLabel);

		userNameText = new Text(userAuthenticationContainer, SWT.BORDER);
		GridData gridDataUserNameText = new GridData(GridData.GRAB_HORIZONTAL);
		gridDataUserNameText.widthHint = 100;
		userNameText.setLayoutData(gridDataUserNameText);
		userNameText.setTextLimit(15);
		userNameText.setToolTipText("Default Username is 'admin'");

		// Password
		Label passwordLabel = new Label(userAuthenticationContainer, SWT.NONE);
		passwordLabel.setText("Password");
		GridData gridDataPasswordLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridDataPasswordLabel.widthHint = 65;
		passwordLabel.setLayoutData(gridDataPasswordLabel);

		passwordText = new Text(userAuthenticationContainer, SWT.PASSWORD | SWT.BORDER);
		GridData gridDataPasswordText = new GridData(GridData.GRAB_HORIZONTAL);
		gridDataPasswordText.widthHint = 100;
		passwordText.setLayoutData(gridDataPasswordText);
		passwordText.setTextLimit(15);
		passwordText.setToolTipText("Default Password is 'admin'");

	}

	/**
	 * @return userAuthenticationContainer
	 */
	public Composite getUserAuthenticationContainer() {
		return userAuthenticationContainer;
	}

}