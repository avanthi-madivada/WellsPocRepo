package com.ltts.wellspoc.ui.wizard;

import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.wizard.WellsWizard;

/**
 * Info about this class
 * 
 * @author
 *
 */
public class WellsPage extends WizardPage {

	private Composite container;
	private TableViewer viewer;
	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();

	protected WellsPage(String pageName) {
		super(pageName);

	}

	@Override
	public void createControl(Composite parent) {
		setTitle("Wells Selection Page");

		container = new Composite(parent, SWT.FILL);
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = createTable(container);
		viewer.setInput(wellData);

		
	/*
		for (TableItem item : table.getItems()) {
			Well wellData = (Well) item.getData();
			item.setChecked(wellData.isChecked());
			if(wellData.isChecked()) {
            System.out.println("12345 "+wellData.isChecked());
            System.out.println("12345 "+wellData.getWellPlanName());

			}
		} 
    */
		
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.detail == SWT.CHECK) {
					TableItem item = (TableItem) e.item;
					Well wellData = (Well) item.getData();
					wellData.setChecked(item.getChecked());
					System.out.println(item.getChecked());
				}

			}
		});

		setControl(container);
	}

	private Table createTable(Composite parent) {
		viewer = new TableViewer(parent, SWT.BORDER | SWT.CHECK | SWT.H_SCROLL | SWT.V_SCROLL);

		Table table = viewer.getTable();
		createColumns(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
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

		return table;
	}

	private void createColumns(Table table) {
		TableLayout layout = new TableLayout();

		layout.addColumnData(new ColumnWeightData(60, true));
		layout.addColumnData(new ColumnWeightData(250, true));
		table.setLayout(layout);
		TableViewerColumn column = createTableViewerColumn("Well Selection");

		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Well) element).getWellId();
			}
		});

		column = createTableViewerColumn("Well Name");
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Well) element).getWellPlanName();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(String name) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.CENTER);
		TableColumn column = viewerColumn.getColumn();
		column.setText(name);
		column.setMoveable(true);
		return viewerColumn;
	}

	/**
	 * Disables the back button in Well Selection Page.
	 */
	@Override
	public IWizardPage getPreviousPage() {
		return null;
	}

}
