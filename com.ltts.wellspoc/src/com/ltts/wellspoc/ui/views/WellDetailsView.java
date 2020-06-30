package com.ltts.wellspoc.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
		//String[] propertyNames = { "Well Name", "Easting", "Northing", "Azimuth", "Field", "Reservoir", "Type" };

		// mapping from property to label, needed for column header labels
		Map<String, String> propertyToLabelMap = new LinkedHashMap<String, String>();
		propertyToLabelMap.put("wellPlanName", "Well Name");
		propertyToLabelMap.put("easting", "Easting");
		propertyToLabelMap.put("northing", "Northing");
		propertyToLabelMap.put("azimuth", "Azimuth");
		propertyToLabelMap.put("field", "Field");
		propertyToLabelMap.put("reservoir", "Reservoir");
		propertyToLabelMap.put("type", "Type");

		IColumnPropertyAccessor<Well> columnPropertyAccessor = new ReflectiveColumnPropertyAccessor<Well>(
				propertyToLabelMap.keySet().toArray(new String[propertyToLabelMap.size()]));

		bodyDataProvider = new ListDataProvider<Well>(wellList, columnPropertyAccessor);
		bodyLayer = new BodyLayerStack(bodyDataProvider);


		// Column Data Provider
		DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(propertyToLabelMap.values().toArray(new String[propertyToLabelMap.size()]));
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


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public void setWellData(List<Well> selectedWells) {
		WellDataProvider.wellDataProvider.getUpdateWell(selectedWells);
		natTable.refresh();
	}
}