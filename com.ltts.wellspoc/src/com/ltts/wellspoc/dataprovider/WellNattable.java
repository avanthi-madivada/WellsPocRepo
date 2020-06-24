package com.ltts.wellspoc.dataprovider;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.columnChooser.command.DisplayColumnChooserCommandHandler;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.ConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.DefaultNatTableStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.freeze.config.DefaultFreezeGridBindings;
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
import org.eclipse.nebula.widgets.nattable.ui.menu.HeaderMenuConfiguration;
import org.eclipse.nebula.widgets.nattable.ui.menu.PopupMenuBuilder;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.Well;

public class WellNattable {
	private BodyLayerStack bodyLayer;
	 int columnIndex=0;
	 int rowIndex =0;
	List<Well> lisFault = new LinkedList<Well>();
	 IConfigRegistry configRegistry = new ConfigRegistry();
	public NatTable createNatTable(Composite parent, List<Well> lisFault) {

		addAllLisFault(lisFault);
		String[] wellColumnNames = { "Well Name", "Easting", "Northing", "Azimuth", "Field", "Reservoir", "Type" };
		IDataProvider iDataProvider = new ListDataProvider<Well>(this.lisFault,
				new ReflectiveColumnPropertyAccessor<Well>(wellColumnNames));

		System.out.println("Column count: " + iDataProvider.getColumnCount());
		System.out.println("Row count: " + iDataProvider.getRowCount());

		bodyLayer = new BodyLayerStack(iDataProvider);

		DefaultColumnHeaderDataProvider colHeaderDataProvider = new DefaultColumnHeaderDataProvider(wellColumnNames);
		DefaultRowHeaderDataProvider rowHeaderDataProvider = new DefaultRowHeaderDataProvider(iDataProvider);
		ColumnHeaderLayerStack columnHeaderLayer = new ColumnHeaderLayerStack(colHeaderDataProvider);
		RowHeaderLayerStack rowHeaderLayer = new RowHeaderLayerStack(rowHeaderDataProvider);
		DefaultCornerDataProvider cornerDataProvider = new DefaultCornerDataProvider(colHeaderDataProvider,
				rowHeaderDataProvider);
		CornerLayer cornerLayer = new CornerLayer(new DataLayer(cornerDataProvider), rowHeaderLayer, columnHeaderLayer);

		GridLayer gridLayer = new GridLayer(bodyLayer, columnHeaderLayer, rowHeaderLayer, cornerLayer);
		NatTable natTable = new NatTable(parent, gridLayer, false);

		GridData gridData = new GridData();	
		gridData.heightHint = (int) 24;
        gridData.widthHint = (int) 110;
		/*
		 * // Configuration of the NatTable natTable.addConfiguration(new
		 * DefaultNatTableStyleConfiguration()); natTable.addConfiguration(new
		 * DefaultFreezeGridBindings());
		 * 
		 * 
		 * //Creation of the dialogue of choice of columns
		 * 
		 * DisplayColumnChooserCommandHandler columnChooserCommandHandler = new
		 * DisplayColumnChooserCommandHandler ( bodyLayer.getSelectionLayer(), null,
		 * null, null, null, null );
		 * 
		 * cornerLayer.registerCommandHandler (columnChooserCommandHandler);
		 * 
		 * 
		 * natTable. addConfiguration ( new HeaderMenuConfiguration (natTable) {
		 * 
		 * @ Override protected PopupMenuBuilder createColumnHeaderMenu (NatTable
		 * natTable) { return super . createColumnHeaderMenu (natTable).
		 * withColumnChooserMenuItem (); } } );
		 * 
		 * natTable.configure(); return natTable;
		 */
		
		 IConfigLabelAccumulator cellLabelAccumulator = new IConfigLabelAccumulator() {
	            @Override
	            public void accumulateConfigLabels(LabelStack configLabels,int columnPosition, int rowPosition) {

	                 columnIndex = bodyLayer.getColumnIndexByPosition(columnPosition);
	                 rowIndex = bodyLayer.getRowIndexByPosition(rowPosition);
	               
	            }
	        };
	        System.out.println("idataprovider data : "+columnIndex);
	        bodyLayer.setConfigLabelAccumulator(cellLabelAccumulator);

	        natTable.addConfiguration(new DefaultNatTableStyleConfiguration());
	        natTable.addConfiguration(new AbstractRegistryConfiguration() {
	            //@Override
	            public void configureRegistry(IConfigRegistry configRegistry) {
	                Style cellStyle = new Style();
	                cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR,GUIHelper.COLOR_YELLOW);
	                configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle,DisplayMode.NORMAL);

	                cellStyle = new Style();
	                cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR,GUIHelper.COLOR_RED);
	                configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle,DisplayMode.NORMAL);
	            }
	        });

	        natTable.setLayoutData(gridData);
	        natTable.setConfigRegistry(configRegistry);
	        natTable.configure();
	        return natTable;
	}

	/*
	 * public void clearLisFault(){ while(this.lisFault.size()>0){
	 * lisFault.remove(lisFault.size()-1); } }
	 */

	public void addAllLisFault(List<Well> lisFault) {
		for (int i = 0; i < lisFault.size(); i++) {
			this.lisFault.add(lisFault.get(i));
		}
	}
}
