package com.ltts.wellspoc;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of the
 * actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction introAction;
	private IWorkbenchAction exitAction;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		exitAction = ActionFactory.QUIT.create(window);
        register(exitAction);
		introAction = ActionFactory.INTRO.create(window);
        register(introAction);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		super.fillMenuBar(menuBar);
		MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
		menuBar.add(fileMenu);
		//adding a group marker to specify the location of sub menus
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		fileMenu.add(exitAction);
		menuBar.add(helpMenu);
		helpMenu.add(introAction);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		super.fillCoolBar(coolBar);
	}

}

