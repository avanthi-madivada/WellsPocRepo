package com.ltts.wellspoc;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Simple perspective to display views.
 * 
 * @author
 *
 */
public class Perspective implements IPerspectiveFactory {

	public static final String ID = "com.ltts.wellspoc.perspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		//layout.setEditorAreaVisible(true);
		//layout.setFixed(true);
	}
}
