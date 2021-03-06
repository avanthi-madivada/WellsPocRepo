package com.ltts.wellspoc.dataprovider;

import java.util.List;

import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.validate.DataValidator;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellModelMgr;
import com.ltts.wellspoc.ui.util.MessagesUtil;

public class Validation extends DataValidator {

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();

	private String cellData;

	@Override
	public boolean validate(ILayerCell cell, IConfigRegistry configRegistry, Object newValue) {
		cellData = cell.getDataValue().toString();
		return validate(cell.getColumnIndex(), cell.getRowIndex(), newValue);
	}

	@Override
	public boolean validate(int columnIndex, int rowIndex, Object newValue) {

		// Validates the well name.
		if (columnIndex == 0) {
			for (int i = 0; i < wellData.size(); i++) {
				if (AddNewWellModelMgr.INSTANCE.isValidWellName(wellData.get(i).getWellPlanName(),
						newValue.toString())) {
					if (cellData.contentEquals(newValue.toString())) {
						return true;
					} else {
						return false;
					}

				}
			}
		}

		// for azimuth validation
		if (columnIndex == 3) {
			if ((((Double) newValue).doubleValue() < 0) || (((Double) newValue).doubleValue() > 360)) {
				return false;
			} else {

				return true;
			}
		}

		// for easting and northing validation
		if (columnIndex == 1 || columnIndex == 2) {
			if ((newValue instanceof Double)) {
				return MessagesUtil.restrictEnteredChars(newValue.toString(), Double.MIN_VALUE, Double.MAX_VALUE);
			}
		}
		return false;
	}
}