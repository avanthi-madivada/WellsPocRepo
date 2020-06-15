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

/**
 * Class that authenticates the user.
 * 
 * @author Ranjith D
 *
 */
public class LoginPage extends WizardPage {

	private Composite container;
	public static Text userName;
	public static Text password;

	protected LoginPage(String pageName) {
		super(pageName);
		
	}

	@Override
	public void createControl(Composite parent) {
		 
		container = new Composite(parent,SWT.NULL | SWT.BORDER);
		GridLayout layout = new GridLayout(2, true);
		layout.marginHeight = 50;
		layout.marginRight = 25;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		
		// User Name
		Label userNameLabel = new Label(container, SWT.NONE);
		userNameLabel.setText("User Name");
		GridData gridDataUserNameLabel = new GridData(GridData.HORIZONTAL_ALIGN_END );
		gridDataUserNameLabel.widthHint = 65;
		userNameLabel.setLayoutData(gridDataUserNameLabel);

		Text userNameText = new Text(container, SWT.BORDER);
		GridData gridDataUserNameText = new GridData(GridData.GRAB_HORIZONTAL);
		gridDataUserNameText.widthHint = 100;
		userNameText.setLayoutData(gridDataUserNameText);
		userNameText.setTextLimit(15);
		
		// Password
		Label passwardLabel = new Label(container, SWT.NONE );
		passwardLabel.setText("Password");
		GridData gridDataPasswordLabel = new GridData(GridData.HORIZONTAL_ALIGN_END );
		gridDataPasswordLabel.widthHint = 65;
		passwardLabel.setLayoutData(gridDataPasswordLabel);

		Text passWordText = new Text(container,  SWT.PASSWORD | SWT.BORDER  );
		GridData gridDataPasswordText = new GridData(GridData.GRAB_HORIZONTAL);
		gridDataPasswordText.widthHint = 100;
		passWordText.setLayoutData(gridDataPasswordText);
		passWordText.setTextLimit(15);
		
		userNameText.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {				
				userName = (Text) e.getSource();
				
			}
			
		});
			
		passWordText.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {
				password = (Text) e.getSource();
				
			}
			
		});
					
		setControl(container);
		
	}
		
  @Override
  public boolean canFlipToNextPage() {
	return true;
	
  }
  
}
