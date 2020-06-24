package com.ltts.wellspoc.dataprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.ExtendedReflectiveColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IColumnAccessor;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DummyBodyDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.DefaultGridLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ColumnOverrideLabelAccumulator;
import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.ui.wizard.WellsWizard;

/**
 * @author Deepika KS
 * This class has methods which is used to populate data for coloumns and rows in nattable
 *
 */
public class DataProvider extends DummyBodyDataProvider implements IColumnAccessor<List<Well>>{

	
	private String[] properties = new String[7];
	List<Well> wellList = new ArrayList<Well>();
	
	static int columnCount = 0;
	public static int rowCount= 0;
	Well well = new Well();
	
	public DataProvider() {
		super(columnCount, rowCount);
	}
    public DataProvider(int columnCount, int rowCount) {
        super(columnCount, rowCount);
    }

    

	/**
     *@return number of columns 
     */
    @Override
    public int getColumnCount() {
        return properties.length;
    }

    /**
     * @param columnIndex, rowIndex
     * @return Object
     */
    @Override
    public Object getDataValue(int columnIndex, int rowIndex) {
    	well.setWellPlanName("WellPlan");
    	well.setEasting(420107.6);
    	well.setField("Ghawar");
    	well.setAzimuth(180.0);
    	well.setReservoir("Not Fm. 2 HD Top");
    	well.setType("Horizontal");
    	well.setNorthing(7244305.1);
    	wellList.add(well);
    	
    	Map<String, String> map = new HashMap<>();
    	String[] wellColumnNames = {"Well Name","Easting","Northing","Azimuth","Field","Reservoir","Type"};
		
    	for(Well well : wellList) {
    		map.put("Well Name", well.getWellPlanName());
    		map.put("Easting", well.getEasting().toString());
    		map.put("Northing", well.getNorthing().toString());
    		map.put("Azimuth", well.getAzimuth().toString());
    		map.put("Field", well.getField());
    		map.put("Reservoir", well.getReservoir());
    		map.put("Type", well.getType());
    	}
    	
    	//DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(wellDetails);
    	DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(wellColumnNames,map);
         ColumnHeaderLayerStack columnlayer = new ColumnHeaderLayerStack(columnData);
    	return columnlayer.getDataValueByPosition(columnIndex, rowIndex);

    }

    /**
     * @return number of rows
     */
    @Override
    public int getRowCount() {
    	return 1;
       
    }

    
    /**
     *@param arg0, arg1, arg2
     */
    @Override
    public void setDataValue(int arg0, int arg1, Object arg2) {
    	//String[] wellDetails = {"Well plan1","420107.6","7244305.1","1000.0","Ghawar","Not Fm. 2 HD Top","Horizontal"};
    	
    	
    	
    }

	/*
	 * @Override public Object getDataValue(Well well, int columnIndex) {
	 * well.setWellPlanName("WellPlan"); well.setEasting(420107.6);
	 * well.setField("Ghawar"); well.setAzimuth(180.0);
	 * well.setReservoir("Not Fm. 2 HD Top"); well.setType("Horizontal");
	 * well.setNorthing(7244305.1); wellList.add(well);
	 * 
	 * Map<String, String> map = new HashMap<>(); String[] wellColumnNames =
	 * {"Well Name","Easting","Northing","Azimuth","Field","Reservoir","Type"};
	 * 
	 * for(Well wells : wellList) { map.put("Well Name", wells.getWellPlanName());
	 * map.put("Easting", wells.getEasting().toString()); map.put("Northing",
	 * wells.getNorthing().toString()); map.put("Azimuth",
	 * wells.getAzimuth().toString()); map.put("Field", wells.getField());
	 * map.put("Reservoir", wells.getReservoir()); map.put("Type", wells.getType());
	 * }
	 * 
	 * //DefaultColumnHeaderDataProvider columnData = new
	 * DefaultColumnHeaderDataProvider(wellDetails); DefaultColumnHeaderDataProvider
	 * columnData = new DefaultColumnHeaderDataProvider(wellColumnNames,map);
	 * ColumnHeaderLayerStack columnlayer = new ColumnHeaderLayerStack(columnData);
	 * return columnlayer.getDataValueByPosition(columnIndex, columnIndex);}
	 */

	/*
	 * @Override public void setDataValue(Well rowObject, int columnIndex, Object
	 * newValue) { // TODO Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	public Object getDataValue(List<Well> rowObject, int columnIndex) {
		Map<String, String> map = new HashMap<>();
    	String[] wellColumnNames = {"Well Name","Easting","Northing","Azimuth","Field","Reservoir","Type"};
		
    	for(Well well : rowObject) {
    		System.out.println("Inside for loop : "+well.getWellPlanName());
    		map.put("Well Name", well.getWellPlanName());
    		map.put("Easting", well.getEasting().toString());
    		map.put("Northing", well.getNorthing().toString());
    		map.put("Azimuth", well.getAzimuth().toString());
    		map.put("Field", well.getField());
    		map.put("Reservoir", well.getReservoir());
    		map.put("Type", well.getType());
    	}
		/*
		 * IDataProvider bodyDataProvider = new ListDataProvider<Well>( rowObject,
		 * (IColumnAccessor<Well>) new
		 * ExtendedReflectiveColumnPropertyAccessor<Well>(wellColumnNames));
		 * //DefaultColumnHeaderDataProvider columnData = new
		 * DefaultColumnHeaderDataProvider(wellDetails); DefaultGridLayer gridLayer =
		 * new DefaultGridLayer(bodyDataProvider, new
		 * DefaultColumnHeaderDataProvider(wellColumnNames, map));
		 * 
		 * final DataLayer bodyDataLayer = (DataLayer) gridLayer.getBodyDataLayer();
		 * 
		 * final ColumnOverrideLabelAccumulator columnLabelAccumulator = new
		 * ColumnOverrideLabelAccumulator(bodyDataLayer);
		 * bodyDataLayer.setConfigLabelAccumulator(columnLabelAccumulator); return
		 * columnLabelAccumulator;
		 */
		
		  IColumnPropertyAccessor<Well> columnPropertyAccessor = new ExtendedReflectiveColumnPropertyAccessor<Well>(wellColumnNames); 
		  IDataProvider listDataProvider = new ListOfDataProvider<Well>(rowObject,columnPropertyAccessor); 
		  ILayer layer = new DataLayer(listDataProvider);
		  System.out.println("@183 : "+layer.getColumnCount());
		  
		 // IDataProvider dataProvider = new DataProvider(properties.length,5);
		return layer.getDataValueByPosition(columnIndex, 5);
		 
    	

	}

	@Override
	public void setDataValue(List<Well> rowObject, int columnIndex, Object newValue) {
		// TODO Auto-generated method stub
		
	}
}