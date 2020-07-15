package com.ltts.wellspoc.ui.wellselection;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.util.MessagesUtil;

/**
 * Synchronize UI and model instance.
 * 
 * @author Ranjith D
 *
 */
public class WellSelectionUISupport {

	WellSelectionUI wellSelectionUI;
	private TableViewer viewer;
	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();
	Table wellTable;
	Well wellModel;

	String[] headers = { "Well Selection", "Well Name", "Well Type" };
	String[] methodNames = { "isChecked", "WellPlanName", "Type" };
	int[] width = { 90, 280, 280 };

	public WellSelectionUISupport(WellSelectionUI wellSelectionUI, Well wellModel) {
		this.wellSelectionUI = wellSelectionUI;
		this.wellModel = wellModel;
		wellTable = createTable(wellSelectionUI.wellSelectionContainer);

		viewer.setInput(wellData);
		changeUIFromModel();
		addModifyListener();
	}

	/**
	 * Method to create listeners.
	 */
	private void addModifyListener() {

		wellSelectionUI.checkBoxButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wellSelectionUI.getCheckBoxButton().getSelection() == true) {
					WellSelectionModelMgr.INSTANCE.updateModelForCheckBox(true);
				} else {
					WellSelectionModelMgr.INSTANCE.updateModelForCheckBox(false);
				}
				changeUIFromModel();
			}
		});

		wellTable.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.detail == SWT.CHECK) {
					TableItem item = (TableItem) e.item;
					WellSelectionModelMgr.INSTANCE.changeModelFromUI(item);
					WellSelectionModelMgr.INSTANCE.changeCheckboxState();
				}
			}
		});
	}

	/**
	 * Creates the table in Well Selection Page.
	 * 
	 * @param parent
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
		viewer.getControl().setLayoutData(wellSelectionUI.getGridData());
		return wellTable;
	}

	/**
	 * provides the data for wellTable.
	 * 
	 * @param wellTable
	 */
	private void createColumns(Table wellTable) {
		for (int i = 0; i < headers.length; i++) {
			TableViewerColumn column = createTableViewerColumn(headers[i], width[i]);

			column.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					Well well = (Well) element;
					for (int i = 1; i < headers.length; i++) {
						try {

							if ((column.getColumn().toString()).equals("TableColumn {" + headers[i] + "}")) {
								Method getterMethod = well.getClass().getMethod("get" + methodNames[i]);
								return (String) getterMethod.invoke(well);
							}
						} catch (Exception e) {
							MessagesUtil.logError(WellSelectionUISupport.class.getName(), e.getMessage());
						}
					}
					return null;
				}
			});
		}

	}

	/**
	 * creates the table columns and makes the columns re-sizable.
	 * 
	 * @param name
	 * @return
	 */
	private TableViewerColumn createTableViewerColumn(String name, int width) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer, SWT.CENTER);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setText(name);
		tableColumn.setWidth(width);
		tableColumn.setMoveable(true);

		return tableViewerColumn;
	}

	/**
	 * changes the state of check box in UI.
	 */
	private void changeUIFromModel() {
		wellSelectionUI = WellSelectionViewMgr.INSTANCE.getWellSelectionUI();

		for (TableItem item : wellTable.getItems()) {
			Well wellData = (Well) item.getData();
			item.setChecked(wellData.isChecked());
		}

		wellSelectionUI.checkBoxButton.setSelection(wellModel.checkBoxState());
	}

}
