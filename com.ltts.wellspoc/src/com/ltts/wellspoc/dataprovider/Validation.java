package com.ltts.wellspoc.dataprovider;

import java.util.List;

import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.validate.DataValidator;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.models.WellDataProvider;
import com.ltts.wellspoc.ui.addnewwell.AddNewWellModelMgr;
import com.ltts.wellspoc.ui.util.MessagesUtil;

public class Validation extends DataValidator {

	private List<Well> wellData = WellDataProvider.wellDataProvider.getWell();

	private String cellData;
	private static final Device device = Display.getCurrent();
	private static final Color red = new Color(device, 255, 0, 0);
	private final static Color black = new Color(device, 0, 0, 0);

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

				
				

		// for easting validation

				if (columnIndex == 1) {

					if (MessagesUtil.restrictEnteredChars(newValue.toString(), Double.MIN_VALUE, Double.MAX_VALUE)) {
						System.out.println("" + newValue.toString());
					
						return true;
					} else {

						return false;
					}

				}
				return false;

			}

			// for testing northing

			public void CheckValue( Text text, Double minValue, Double maxValue) {
				if (MessagesUtil.restrictEnteredChars(text.getText().toString(), minValue, maxValue)) {
				
					text.setForeground(black);

				} else {
					text.setForeground(red);
				}

			}
}