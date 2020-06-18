package com.ltts.wellspoc.dataprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DummyBodyDataProvider;
import org.eclipse.swt.graphics.Point;

import com.ltts.wellspoc.models.Well;

/**
 * @author Deepika KS
 *
 */
public class DataProvider extends DummyBodyDataProvider{

	
	private String[] properties = new String[7];
	List<Well> wellList = new ArrayList<Well>();
	
	Well well = new Well();
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
}