package com.ltts.wellspoc.ui.views;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

/**
 * 
 * @author
 *
 */
public class WellDetailsView extends ViewPart {
	public static final String ID = "com.ltts.wellspoc.welldetailsview";
	@Inject
	IWorkbench workbench;
	Text dummyText;

	@Override
	public void createPartControl(Composite parent) {
		// Nattable should come here
		dummyText = new Text(parent, SWT.BORDER);
		dummyText.setText("Dummy text box.. to be removed");

	}

	@Override
	public void setFocus() {
		// TODO set focus on nattable
		dummyText.setFocus();
	}

}
