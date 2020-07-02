package com.ltts.wellspoc.ui.loginpage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ltts.wellspoc.ui.util.PropertiesCache;

public class LoginPageUI {
	
	PropertiesCache prop = PropertiesCache.getInstance();	 
	//read the title from property file
	String PAGE_TITLE = prop.getProperty("LoginPage_page_title");
	public static Text userNameText = null;
	public static Text passwordText = null;
	/**
	 * This method is used to create UI for login page.
	 */
	public LoginPageUI(Composite parent) {
//		setTitle(PAGE_TITLE);

		Composite userAuthenticationContainer = new Composite(parent, SWT.NULL | SWT.BORDER);
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

		
//		setControl(userAuthenticationContainer);
	}


}
