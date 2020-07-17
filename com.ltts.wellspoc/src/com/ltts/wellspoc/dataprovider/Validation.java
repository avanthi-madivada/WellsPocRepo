package com.ltts.wellspoc.dataprovider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellModelMgr;
import org.eclipse.nebula.widgets.nattable.data.validate.DataValidator;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellModelMgr;

public class Validation extends DataValidator {

	private List<Well> wellList = new ArrayList<Well>();
	
	@Override
	public boolean validate(int columnIndex, int rowIndex, Object newValue) {
		double d1 = 0.50;
		double d2 = d1 % 1;
		DecimalFormat df = new DecimalFormat("#.0");

		int decimalLength = (df.format(d2).length() - 1);
		if (columnIndex == 3) {
			if ((((Double) newValue).doubleValue() <= 360) && ((((Double) newValue).doubleValue() >= 0))) {
				return true;
			} else {
				return false;
			}
		} else {
			if ((newValue instanceof Double) && (((Double) newValue).doubleValue() >= 0) && (decimalLength == 1)) {
				return true;

			} else if ((AddNewWellModelMgr.INSTANCE.isValidWellName())) {
				System.out.println("comimg insiide true");
				return true;
			} else {
				return false;
			}
		}
	}

}
