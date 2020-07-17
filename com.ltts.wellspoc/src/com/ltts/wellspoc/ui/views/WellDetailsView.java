package com.ltts.wellspoc.ui.views;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.NatTable;
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
import org.eclipse.nebula.widgets.nattable.layer.cell.ColumnLabelAccumulator;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.ltts.wellspoc.dataprovider.BodyLayerStack;
import com.ltts.wellspoc.dataprovider.ColumnHeaderLayerStack;
import com.ltts.wellspoc.dataprovider.RowHeaderLayerStack;
import com.ltts.wellspoc.dataprovider.WellEditConfiguration;
import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;

/**
 * @author Deepika KS WellDetailsView is a subclass of ViewPart which is used to
 *         create new views for nattable
 *
 */
public class WellDetailsView extends ViewPart {

	private static BodyLayerStack bodyLayer;
	public static BodyLayerStack getBodyLayer() {
		return bodyLayer;
	}

	public static void setBodyLayer(BodyLayerStack bodyLayer) {
		WellDetailsView.bodyLayer = bodyLayer;
	}

	private static NatTable natTable;
	private List<Well> wellList = new ArrayList<Well>();
	IDataProvider bodyDataProvider = null;

	public WellDetailsView() {
		wellList = WellDataProvider.wellDataProvider.getWellDetailsData();
	}

	/**
	 * This method helps to create nattable view with required data
	 */
	@Override
	public void createPartControl(Composite parent) {

		GridData gridData = new GridData();
		gridData.heightHint = (int) 24;
		gridData.widthHint = (int) 110;
		IConfigRegistry configRegistry = new ConfigRegistry();

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

		// Column Data Provider ..column labels-propertytolabelmap.values
		DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(
				propertyToLabelMap.values().toArray(new String[propertyToLabelMap.size()]));
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

		// Apply a ColumnLabelAccumulator to address the columns in the
		// EditConfiguration class
		ColumnLabelAccumulator columnLabelAccumulator = new ColumnLabelAccumulator(bodyDataProvider);
		bodyLayer.setConfigLabelAccumulator(columnLabelAccumulator);

		natTable.addConfiguration(new DefaultNatTableStyleConfiguration());
		// add the EditConfiguration to enable editing support
		natTable.addConfiguration(new WellEditConfiguration());

		natTable.setLayoutData(gridData);
		natTable.setConfigRegistry(configRegistry);
		natTable.configure();
	}

	@Override
	public void setFocus() {
	}

	/**
	 * @param List of Well with updated data
	 */
	public void setWellData(List<Well> selectedWells) {
		WellDataProvider.wellDataProvider.getUpdateWell(selectedWells);
		natTable.refresh();
	}
}