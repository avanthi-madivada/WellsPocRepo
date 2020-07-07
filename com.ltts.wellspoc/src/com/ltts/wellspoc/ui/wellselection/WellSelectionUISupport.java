package com.ltts.wellspoc.ui.wellselection;

import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;

public class WellSelectionUISupport {
	
	WellSelectionUI wellSelectionUI;
	Well wellModel;
	private TableViewer viewer;
	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();
	Table wellTable;
	
	public WellSelectionUISupport(WellSelectionUI wellSelectionUI, Well wellModel) {
		this.wellSelectionUI = wellSelectionUI;
		wellTable =  createTable(wellSelectionUI.wellSelectionContainer);
		viewer.setInput(wellData);
		
		
		//need to create separate fn for listener
		wellTable.addSelectionListener(new SelectionAdapter() {
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
		
		
//		addModifyListener();
	}

//	private void addModifyListener() {
//		wellSelectionUI.wellTable.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e)  {
//				if (e.detail == SWT.CHECK) {
//					TableItem item = (TableItem) e.item;
////					Well wellData = (Well) item.getData();
////					wellData.setChecked(item.getChecked());
//					WellSelectionModelMgr.INSTANCE.changeModelFromUI(item);
//				}
//			}
//		});
//		
//	}
//	
	
	/**
	 * Creates the table in Well Selection Page.
	 * 
	 * @param parent
	 * @param viewer 
	 * @return
	 */
	protected Table createTable(Composite parent) {
		viewer = new TableViewer(parent, SWT.BORDER | SWT.CHECK | SWT.H_SCROLL | SWT.V_SCROLL);

		Table wellTable = viewer.getTable();
		createColumns(wellTable);
		wellTable.setHeaderVisible(true);
		wellTable.setLinesVisible(true);
		// Get the content for the viewer.
		viewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				return wellData.toArray();
			}
		});

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.heightHint = 100;
		viewer.getControl().setLayoutData(gridData);

		return wellTable;
	}

	/**
	 * Creates the column for wellTable with two columns Well Selection and Well
	 * Name.
	 * 
	 * @param wellTable
	 * @param viewer 
	 */
	private void createColumns(Table wellTable) {
		TableLayout layout = new TableLayout();

		layout.addColumnData(new ColumnWeightData(45, true));
		layout.addColumnData(new ColumnWeightData(250, true));
		wellTable.setLayout(layout);

		// First column - Well Selection
		TableViewerColumn tableViewerColumn = createTableViewerColumn("Well Selection");

		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return " ";
			}
		});
		// Second column - Well Name
		tableViewerColumn = createTableViewerColumn("Well Name");
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Well) element).getWellPlanName();
			}
		});
	}

	/**
	 * creates the table columns and makes the columns re-sizable.
	 * 
	 * @param name
	 * @param viewer 
	 * @return
	 */
	private TableViewerColumn createTableViewerColumn(String name) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer, SWT.CENTER);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setText(name);
		tableColumn.setMoveable(true);

		return tableViewerColumn;
	}


}
