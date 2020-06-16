package com.ltts.wellspoc.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DummyBodyDataProvider;
import org.eclipse.swt.graphics.Point;

import com.ltts.wellspoc.models.Well;

public class DataProvider extends DummyBodyDataProvider{

	
	private String[] properties = new String[7];
	List<Well> wellList = new ArrayList<Well>();
	
	Well well = new Well();
    public DataProvider(int columnCount, int rowCount) {
        super(columnCount, rowCount);
    }

    @Override
    public int getColumnCount() {
        return properties.length;
    }

    @Override
    public Object getDataValue(int columnIndex, int rowIndex) {
    	well.setWellPlanName("WellPlan");
    	well.setEasting("420107.6");
    	well.setField("7244305.1");
    	well.setAzimuth("180");
    	well.setReservoir("Ghawar");
    	well.setType("Not Fm. 2 HD Top");
    	well.setNorthing("Not Fm. 2 HD Top");
    	wellList.add(well);
    	
    	String[] wellDetails = {well.getWellPlanName(),well.getEasting(),well.getNorthing(),well.getAzimuth(),well.getField(),well.getReservoir(),well.getType()};
    	 DefaultColumnHeaderDataProvider columnData = new DefaultColumnHeaderDataProvider(wellDetails);
         ColumnHeaderLayerStack columnlayer = new ColumnHeaderLayerStack(columnData);
    	return columnlayer.getDataValueByPosition(columnIndex, rowIndex);

    }

    @Override
    public int getRowCount() {
    	return 1;
       
    }

    @Override
    public void setDataValue(int arg0, int arg1, Object arg2) {
    	//String[] wellDetails = {"Well plan1","420107.6","7244305.1","1000.0","Ghawar","Not Fm. 2 HD Top","Horizontal"};
    	
    	
    	
    }
}