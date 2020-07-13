package com.ltts.wellspoc.dataprovider;

import org.eclipse.nebula.widgets.nattable.data.validate.DataValidator;

public class Validation extends DataValidator {

	@Override
	public boolean validate(int columnIndex, int rowIndex, Object newValue) {
		if(columnIndex==3) {
			if((((Double) newValue).doubleValue() <= 360)) {
				return true;
			}else {
				return false;
			}
		}else {
			if ((newValue instanceof Double) && (((Double) newValue).doubleValue() > 10000)) {
				return true;
				
			} else if(newValue instanceof String) {
				return true;
			}else {
				return false;
			}
		}
		}
		
}
