package com.ltts.wellspoc.dataprovider;

import java.util.Iterator;
import java.util.List;

import org.eclipse.nebula.widgets.nattable.data.IColumnAccessor;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import com.ltts.wellspoc.models.Well;

public class ListOfDataProvider<Well> extends ListDataProvider<Well>{

	public ListOfDataProvider(List<Well> list, IColumnAccessor<Well> columnAccessor) {
		super(list, columnAccessor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public Object getDataValue(int columnIndex, int rowIndex) {
		/*
		 * System.out.println("List of Data Provider"+list.get(0));
		 * 
		 * Well rowObj =list.get(0); System.out.println("rowObj:"+rowObj.toString());
		 * return this.columnAccessor.getDataValue(rowObj, columnIndex);
		 * 
		 */
		Well rowObj =list.get(0); System.out.println("rowObj:"+rowObj.toString());
		System.out.println("************"+rowObj);
		return this.columnAccessor.getDataValue(rowObj, 1);
    }
	
}
