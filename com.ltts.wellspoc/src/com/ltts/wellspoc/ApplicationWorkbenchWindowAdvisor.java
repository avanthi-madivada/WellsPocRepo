package com.ltts.wellspoc;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
//import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * Class to configure the workbench window.
 *
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }
    
    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    @Override
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        //configurer.setInitialSize(new Point(5000, 5000));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(false);
        configurer.setTitle("Wells POC Application");
    }
    
    public void postWindowOpen() {
       

        // remove unwanted ui component open file
        IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();

        for (int i = 0; i < windows.length; ++i) {
            IWorkbenchPage page = windows[i].getActivePage();
            if (page != null) {
                // hide generic 'File' commands
                page.hideActionSet("org.eclipse.ui.actionSet.openFiles");
      
            }
        }
    }
}
