package com.ltts.wellspoc.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ltts.wellspoc.ui.util.PropertiesCache;

/**
 * The class is used for User Authentication.
 * 
 * @author Ranjith D
 */
public class LoginPage extends WizardPage {
	
	PropertiesCache prop = PropertiesCache.getInstance();	 
	//read the title from property file
	String PAGE_TITLE = prop.getProperty("LoginPage_page_title");
	protected static Text userNameText = null;
	protected static Text passwordText = null;

	/**
	 * Constructor for Login
	 * 
	 * @param pageName
	 */
	protected LoginPage(String pageName) {
		super(pageName);
	}
	
	/**
	 * This method is used to create UI for login page.
	 */
	@Override
	public void createControl(Composite parent) {
		setTitle(PAGE_TITLE);

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

		userNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				canFlipToNextPage();
				getWizard().getContainer().updateButtons();
			}
		});
		passwordText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				canFlipToNextPage();
				getWizard().getContainer().updateButtons();
			}
		});
		setControl(userAuthenticationContainer);
	}

	/**
	 * Enable/disable the Next Button in Login Page.
	 */
	@Override
	public boolean canFlipToNextPage() {
		if (userNameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}