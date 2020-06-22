package com.ltts.wellspoc;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ltts.wellspoc.ui.wizard.WellsWizard;

/**
 * Class that handles the sequence of actions to be taken on occurrence of the
 * event associated with the command of this handler.
 * 
 * @author Avanthi Madivada
 *
 */
public class WellsWizardHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), new WellsWizard());
		wizardDialog.setPageSize(600, 400); 
		wizardDialog.setShellStyle(SWT.CLOSE |SWT.TITLE | SWT.BORDER );
		wizardDialog.open();
		return null;
	}
	

}


