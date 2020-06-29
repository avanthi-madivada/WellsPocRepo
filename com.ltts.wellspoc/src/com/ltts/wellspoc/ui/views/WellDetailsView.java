package com.ltts.wellspoc.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.ConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.DefaultNatTableStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.ltts.wellspoc.dataprovider.BodyLayerStack;
import com.ltts.wellspoc.dataprovider.ColumnHeaderLayerStack;
import com.ltts.wellspoc.dataprovider.RowHeaderLayerStack;
import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.util.MessagesUtil;

/**
 * @author Deepika KS WellDetailsView is a subclass of ViewPart which is used to
 *         create new views for nattable
 *
 */
public class WellDetailsView extends ViewPart {

	public static BodyLayerStack bodyLayer;
	// private String[] properties = new String[7];
	private int statusColumn;
	private int statusRejected;
	private int statusInProgress;
	private boolean check = false;
	private static NatTable natTable;
	private static final String FOO_LABEL = "FOO";
	private static final String CELL_LABEL = "DEMO";
	public static Composite compositeParent;
	private List<Well> wellList = new ArrayList<Well>();
	IDataProvider bodyDataProvider = null;

	public WellDetailsView() {
		wellList = WellDataProvider.wellDataProvider.getWellDetailsData();
	}

//	/**
//	 *
//	 */
//	@Override
//	public void createPartControl(Composite parent) {
//		compositeParent = parent;
//
//		GridData gridData = new GridData();
//		gridData.heightHint = (int) 24;
//		gridData.widthHint = (int) 110;
//		IConfigRegistry configRegistry = new ConfigRegistry();
//
//		List<String> columnNames = new ArrayList<>();
//		columnNames.add("Well Name");
//		columnNames.add("Easting");
//		columnNames.add("Northing");
//		columnNames.add("Azimuth");
//		columnNames.add("Field");
//		columnNames.add("Reservoir");
//		columnNames.add("Type");
//
//		Iterator<String> it = columnNames.iterator();
//		while (it.hasNext()) {
//			// Body Data Provider
//			for (int i = 0; i < properties.length; i++) {
//
//				properties[i] = (String) it.next();
//			}
//		}
//		IDataProvider dataProvider = new DataProvider(properties.length, WellsWizard.getSelectedWellsList.size());
//		bodyLayer = new BodyLayerStack(dataProvider);
//		// datalayer.addConfiguration(new
//
//		// IDataProvider listDataProvider = new ListDataProvider(
//		// WellsWizard.getSelectedWellsList, new
//		// ReflectiveColumnPropertyAccessor(properties));
//
//		// Column Data Provider
//		DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(properties);
//		ColumnHeaderLayerStack columnlayer = new ColumnHeaderLayerStack(columnData);
//
//		// Row Data Provider
//
//		DefaultRowHeaderDataProvider rowdata = new DefaultRowHeaderDataProvider(dataProvider);
//		RowHeaderLayerStack rowlayer = new RowHeaderLayerStack(rowdata);
//
//		// Corner Data Provider
//		DefaultCornerDataProvider cornerdata = new DefaultCornerDataProvider(columnData, rowdata);
//		DataLayer cornerDataLayer = new DataLayer(cornerdata);
//		CornerLayer cornerLayer = new CornerLayer(cornerDataLayer, rowlayer, columnlayer);
//
//		GridLayer gridlayer = new GridLayer(bodyLayer, columnlayer, rowlayer, cornerLayer);
//		nattable = new NatTable(parent, gridlayer, false);
//		System.out.println("parent : " + parent);
//		// Change for paint
//		IConfigLabelAccumulator cellLabelAccumulator = new IConfigLabelAccumulator() {
//
//			@Override
//			public void accumulateConfigLabels(LabelStack configLabels, int columnPosition, int rowPosition) {
//				// TODO Auto-generated method stub
//
//			}
//		};
//		bodyLayer.setConfigLabelAccumulator(cellLabelAccumulator);
//
//		nattable.addConfiguration(new DefaultNatTableStyleConfiguration());
//		nattable.addConfiguration(new AbstractRegistryConfiguration() {
//			// @Override
//			public void configureRegistry(IConfigRegistry configRegistry) {
//				Style cellStyle = new Style();
//				cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_YELLOW);
//				configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL,
//						FOO_LABEL);
//
//				cellStyle = new Style();
//				cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_RED);
//				configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL,
//						CELL_LABEL);
//			}
//		});
//
//		nattable.setLayoutData(gridData);
//		nattable.setConfigRegistry(configRegistry);
//		nattable.configure();
//	}

//	@Override
//	public void createPartControl(Composite parent) {
//		parent.setLayout(new GridLayout());
//
//		// property names of the Person class
//		String[] propertyNames = { "wellPlanName", "easting", "northing", "azimuth", "field", "reservoir", "type" };
//
//		// mapping from property to label, needed for column header labels
//		Map<String, String> propertyToLabelMap = new HashMap<String, String>();
//		propertyToLabelMap.put("wellPlanName", "Well Name");
//		propertyToLabelMap.put("easting", "Easting");
//		propertyToLabelMap.put("northing", "Northing");
//		propertyToLabelMap.put("azimuth", "Azimuth");
//		propertyToLabelMap.put("field", "Field");
//		propertyToLabelMap.put("reservoir", "Reservoir");
//		propertyToLabelMap.put("type", "Type");
//
//		IColumnPropertyAccessor<Well> columnPropertyAccessor = new ReflectiveColumnPropertyAccessor<Well>(
//				propertyNames);
//
//		// build the body layer stack
//		IDataProvider bodyDataProvider = new ListDataProvider<Well>(wellList,
//				columnPropertyAccessor);
//		DataLayer bodyDataLayer = new DataLayer(bodyDataProvider);
//		SelectionLayer selectionLayer = new SelectionLayer(bodyDataLayer);
//		ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);
//
//		// build the column header layer stack
//		IDataProvider columnHeaderDataProvider = new DefaultColumnHeaderDataProvider(propertyNames, propertyToLabelMap);
//		DataLayer columnHeaderDataLayer = new DataLayer(columnHeaderDataProvider);
//		ILayer columnHeaderLayer = new ColumnHeaderLayer(columnHeaderDataLayer, viewportLayer, selectionLayer);
//
//		// build the row header layer stack
//		IDataProvider rowHeaderDataProvider = new DefaultRowHeaderDataProvider(bodyDataProvider);
//		DataLayer rowHeaderDataLayer = new DataLayer(rowHeaderDataProvider, 40, 20);
//		ILayer rowHeaderLayer = new RowHeaderLayer(rowHeaderDataLayer, viewportLayer, selectionLayer);
//
//		// build the corner layer stack
//		ILayer cornerLayer = new CornerLayer(
//				new DataLayer(new DefaultCornerDataProvider(columnHeaderDataProvider, rowHeaderDataProvider)),
//				rowHeaderLayer, columnHeaderLayer);
//
//		CompositeLayer compositeLayer = new CompositeLayer(1, 2);
//		compositeLayer.setChildLayer(GridRegion.COLUMN_HEADER, columnHeaderLayer, 0, 0);
//		compositeLayer.setChildLayer(GridRegion.BODY, viewportLayer, 0, 1);
//		natTable = new NatTable(parent, compositeLayer);
//		GridDataFactory.fillDefaults().grab(true, true).applyTo(natTable);
//	}

	/**
	 *
	 */
	@Override
	public void createPartControl(Composite parent) {
		compositeParent = parent;

		GridData gridData = new GridData();
		gridData.heightHint = (int) 24;
		gridData.widthHint = (int) 110;
		IConfigRegistry configRegistry = new ConfigRegistry();

		// property names of the Person class
		String[] propertyNames = { "wellPlanName", "easting", "northing", "azimuth", "field", "reservoir", "type" };

		// mapping from property to label, needed for column header labels
		Map<String, String> propertyToLabelMap = new HashMap<String, String>();
		propertyToLabelMap.put("wellPlanName", "Well Name");
		propertyToLabelMap.put("easting", "Easting");
		propertyToLabelMap.put("northing", "Northing");
		propertyToLabelMap.put("azimuth", "Azimuth");
		propertyToLabelMap.put("field", "Field");
		propertyToLabelMap.put("reservoir", "Reservoir");
		propertyToLabelMap.put("type", "Type");

		IColumnPropertyAccessor<Well> columnPropertyAccessor = new ReflectiveColumnPropertyAccessor<Well>(
				propertyNames);

		// IDataProvider dataProvider = new DataProvider(propertyNames.length,
		// WellsWizard.getSelectedWellsList.size());
		bodyDataProvider = new ListDataProvider<Well>(wellList, columnPropertyAccessor);
		bodyLayer = new BodyLayerStack(bodyDataProvider);
		// datalayer.addConfiguration(new

		// IDataProvider listDataProvider = new ListDataProvider(
		// WellsWizard.getSelectedWellsList, new
		// ReflectiveColumnPropertyAccessor(properties));

		// Column Data Provider
		DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(propertyNames);
		ColumnHeaderLayerStack columnlayer = new ColumnHeaderLayerStack(columnData);

		// Row Data Provider

		DefaultRowHeaderDataProvider rowdata = new DefaultRowHeaderDataProvider(bodyDataProvider);
		RowHeaderLayerStack rowlayer = new RowHeaderLayerStack(rowdata);

		// Corner Data Provider
		DefaultCornerDataProvider cornerdata = new DefaultCornerDataProvider(columnData, rowdata);
		DataLayer cornerDataLayer = new DataLayer(cornerdata);
		CornerLayer cornerLayer = new CornerLayer(cornerDataLayer, rowlayer, columnlayer);

		GridLayer gridlayer = new GridLayer(bodyLayer, columnlayer, rowlayer, cornerLayer);
		natTable = new NatTable(parent, gridlayer, false);
		System.out.println("parent : " + parent);
		// Change for paint
		IConfigLabelAccumulator cellLabelAccumulator = new IConfigLabelAccumulator() {

			@Override
			public void accumulateConfigLabels(LabelStack configLabels, int columnPosition, int rowPosition) {
				// TODO Auto-generated method stub

			}
		};
		bodyLayer.setConfigLabelAccumulator(cellLabelAccumulator);

		natTable.addConfiguration(new DefaultNatTableStyleConfiguration());
		natTable.addConfiguration(new AbstractRegistryConfiguration() {
			// @Override
			public void configureRegistry(IConfigRegistry configRegistry) {
				Style cellStyle = new Style();
				cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_YELLOW);
				configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL,
						FOO_LABEL);

				cellStyle = new Style();
				cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_RED);
				configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL,
						CELL_LABEL);
			}
		});

		natTable.setLayoutData(gridData);
		natTable.setConfigRegistry(configRegistry);
		natTable.configure();
	}

//	public void refreshNatTable() {
//		//WellDataProvider.wellDataProvider.getWell().clear();
//		WellDataProvider.wellDataProvider.getWellData();
//		natTable.refresh();
//		MessagesUtil.displayInformationDialog("Select anyone of the Wells");
//		//natTable.refresh();
//	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public void setWellData(List<Well> selectedWells) {
		WellDataProvider.wellDataProvider.getUpdateWell(selectedWells);
		natTable.refresh();
	}
}