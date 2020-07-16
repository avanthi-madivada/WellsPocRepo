package com.ltts.wellspoc;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.intro.IIntroPart;

public class WellDetailsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		 
		// handler to show welldetails view page by passing view id
		try {
			
			//close the welcome page if its open		
			IIntroPart intro = PlatformUI.getWorkbench().getIntroManager().getIntro();
	    	 if (intro != null) 
	    	 {
	             PlatformUI.getWorkbench().getIntroManager().closeIntro(intro);	            
	         } 
			    HandlerUtil.getActiveWorkbenchWindow(event).getActivePage()
			    .showView("com.ltts.wellspoc.welldetailsview");
			    } 
		 catch (PartInitException e) 
		 		{
			        throw new ExecutionException("Error while opening view", e);
			    }
			    return null;
	}

}
