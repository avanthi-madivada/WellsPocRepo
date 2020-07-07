package com.ltts.wellspoc.ui.wellselection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import com.ltts.wellspoc.models.Well;

public class WellSelectionUISupport {
	
	WellSelectionUI wellSelectionUI;
	Well wellModel;
	
	public WellSelectionUISupport(WellSelectionUI wellSelectionUI, Well wellModel) {
		this.wellSelectionUI = wellSelectionUI;
		addModifyListener();

	}

	private void addModifyListener() {
		wellSelectionUI.wellTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)  {
				if (e.detail == SWT.CHECK) {
					TableItem item = (TableItem) e.item;
//					Well wellData = (Well) item.getData();
//					wellData.setChecked(item.getChecked());
					WellSelectionModelMgr.INSTANCE.changeModelFromUI(item);
				}
			}
		});
		
	}

}
