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

import com.ltts.wellspoc.ui.util.MessagesUtil;


/**
 * Class that authenticates the user.
 * 
 * @author
 *
 */
public class LoginPage extends WizardPage {

	private Composite container;
	public static Text userName;
	public static Text password;
	
	protected LoginPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		
		container = new Composite(parent, SWT.CENTER);
		GridLayout layout = new GridLayout(2, false );	
		layout.marginTop = 55;
		container.setLayout(layout);		
		container.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		// swt widgets
		new Label(container, SWT.NULL).setText("Username");
		Text textUsername = new Text(container,  SWT.BORDER);		
		
		textUsername.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
//		textUsername.setTextLimit(25);
		
		
		new Label(container, SWT.NULL).setText("Password");
		Text textPassword = new Text(container, SWT.PASSWORD | SWT.BORDER);
		textPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));	
//		textPassword.setTextLimit(25);
		
		textUsername.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {				
				userName = (Text) e.getSource();	
				update();
			}
		});
			
		textPassword.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {

				password = (Text) e.getSource();
				update();				
			}
		});
		
		
		
		setPageComplete(false);		
		setControl(container);
	}
	

	private void update() {
		// TODO Auto-generated method stub
	  try {
		if(!(userName.getText().isEmpty())) {			
			if(!(password.getText().isEmpty())){				
				setPageComplete(true);
				}
			}
		
		else {
			setPageComplete(false);
		}
		
		
	  }
	  catch (Exception e) {

		  MessagesUtil.logError(LoginPage.class.getName(), "NPE");
	  }
		}

	
	
}
