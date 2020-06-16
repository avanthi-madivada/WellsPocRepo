package com.ltts.wellspoc.ui.views;

import java.util.*;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.ConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.DefaultNatTableStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultCornerDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultRowHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.CornerLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.LabelStack;
import org.eclipse.nebula.widgets.nattable.layer.cell.IConfigLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

public class WellDetailsView extends ViewPart {

	static BodyLayerStack bodyLayer ;
	private String[] properties = new String[7];
	private int statusColumn;
	private int statusRejected;
	private int statusInProgress;
	private boolean check = false;
	private NatTable nattable;
	private static final String FOO_LABEL = "FOO";
	private static final String CELL_LABEL = "DEMO";
	@Override
	public void createPartControl(Composite parent) {
		
	    
		GridData gridData = new GridData();	
		gridData.heightHint = (int) 24;
        gridData.widthHint = (int) 110;
        IConfigRegistry configRegistry = new ConfigRegistry();
        
        ArrayList columnNames = new ArrayList();
        columnNames.add("Well Name");
        columnNames.add("Easting");
        columnNames.add("Northing");
        columnNames.add("Azimuth");
        columnNames.add("Field");
        columnNames.add("Reservoir");
        columnNames.add("Type");

        Iterator it = columnNames.iterator();
        while(it.hasNext()) {
        //Body Data Provider
        for(int i=0;i<properties.length;i++){

            properties[i]=(String) it.next();   
        }
        }
        IDataProvider dataProvider = new DataProvider(properties.length,5);
        bodyLayer= new BodyLayerStack(dataProvider);
        //datalayer.addConfiguration(new 

        //Column Data Provider
        DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(properties);
        ColumnHeaderLayerStack columnlayer = new ColumnHeaderLayerStack(columnData);

        //Row Data Provider
        
        DefaultRowHeaderDataProvider rowdata = new DefaultRowHeaderDataProvider(dataProvider);
        RowHeaderLayerStack rowlayer = new RowHeaderLayerStack(rowdata);

        //Corner Data Provider
        DefaultCornerDataProvider cornerdata = new DefaultCornerDataProvider(columnData, rowdata);
        DataLayer cornerDataLayer = new DataLayer(cornerdata);
        CornerLayer cornerLayer = new CornerLayer(cornerDataLayer, rowlayer, columnlayer);

        
        GridLayer gridlayer = new GridLayer(bodyLayer, columnlayer, rowlayer, cornerLayer);
        nattable = new NatTable(parent,gridlayer,false);

        // Change for paint
        IConfigLabelAccumulator cellLabelAccumulator = new IConfigLabelAccumulator() {
            //@Override
            public void accumulateConfigLabels(LabelStack configLabels,int columnPosition, int rowPosition) {

                int columnIndex = bodyLayer.getColumnIndexByPosition(columnPosition);
                int rowIndex = bodyLayer.getRowIndexByPosition(rowPosition);
                if (columnIndex == 2 && rowIndex == 45) {
                    configLabels.addLabel(FOO_LABEL);
                }
                else if ((columnIndex == statusColumn) && (rowIndex == statusRejected) && (check ==true)) {
                        configLabels.addLabel(CELL_LABEL);
                }
            }
        };
        bodyLayer.setConfigLabelAccumulator(cellLabelAccumulator);

        nattable.addConfiguration(new DefaultNatTableStyleConfiguration());
        nattable.addConfiguration(new AbstractRegistryConfiguration() {
            //@Override
            public void configureRegistry(IConfigRegistry configRegistry) {
                Style cellStyle = new Style();
                cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR,GUIHelper.COLOR_YELLOW);
                configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle,DisplayMode.NORMAL, FOO_LABEL);

                cellStyle = new Style();
                cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR,GUIHelper.COLOR_RED);
                configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle,DisplayMode.NORMAL, CELL_LABEL);
            }
        });

        nattable.setLayoutData(gridData);
        nattable.setConfigRegistry(configRegistry);
        nattable.configure();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}/*
	public static final String ID = "WellPOCProj.view";

	@Inject IWorkbench workbench;
	
	private TableViewer viewer;
	
	private class StringLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(Object element) {
			return super.getText(element);
		}

		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}

	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.getTable().setLinesVisible(true);

		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.setLabelProvider(new StringLabelProvider());

		viewer.getTable().getColumn(0).setWidth(200);
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		
		// Provide the input to the ContentProvider
		viewer.setInput(createInitialDataModel());
	}


	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private List<String> createInitialDataModel() {
		return Arrays.asList("One", "Two", "Three");
	}
*/}