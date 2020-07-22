package com.ltts.wellspoc.dataprovider;

import java.util.Arrays;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultDoubleDisplayConverter;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.edit.editor.ComboBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.layer.cell.ColumnLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;

/**
 * @author Deepika This class provides nattable to edit the changes in nattable
 *         column and registering the changes done during editing of nattable.
 *
 */
public class WellEditConfiguration extends AbstractRegistryConfiguration {

	@Override
	public void configureRegistry(IConfigRegistry configRegistry) {
		registerWellNameEditor(configRegistry, 0);
		registerEastingsEditor(configRegistry, 1);
		registerNorthingsEditor(configRegistry, 2);
		registerAzimuthEditor(configRegistry, 3);
		registerWellTypeEditor(configRegistry, 6);
	}

	/**
	 * @param configRegistry
	 * @param columnIndex    for WellName column in Nattable
	 */
	private void registerWellNameEditor(IConfigRegistry configRegistry, int columnIndex) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
	}

	/**
	 * @param configRegistry
	 * @param columnIndex    for Azimuth column in Nattable
	 */
	private void registerAzimuthEditor(IConfigRegistry configRegistry, int columnIndex) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultDoubleDisplayConverter(), DisplayMode.NORMAL,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, new Validation(), DisplayMode.EDIT);
		registerErrorHandlingStyles(configRegistry);
	}

	/**
	 * @param configRegistry
	 * @param columnIndex    for WellTypeEditor
	 */
	private void registerWellTypeEditor(IConfigRegistry configRegistry, int columnIndex) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR,
				new ComboBoxCellEditor(Arrays.asList(new String[] { "Horizontal", "Vertical", "Deviated", "S-Well" })),
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
		configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, new Validation(), DisplayMode.EDIT);
		registerErrorHandlingStyles(configRegistry);
	}

	/**
	 * @param configRegistry
	 * @param columnIndex    for Northings column in nattable
	 */
	private void registerNorthingsEditor(IConfigRegistry configRegistry, int columnIndex) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultDoubleDisplayConverter(), DisplayMode.NORMAL,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
		configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, new Validation(), DisplayMode.EDIT);
		registerErrorHandlingStyles(configRegistry);
	}

	/**
	 * @param configRegistry
	 * @param columnIndex    for Easting column in Nattable
	 */
	private void registerEastingsEditor(IConfigRegistry configRegistry, int columnIndex) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultDoubleDisplayConverter(), DisplayMode.NORMAL,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
		configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, new Validation(), DisplayMode.EDIT);
		registerErrorHandlingStyles(configRegistry);

	}

	private void registerErrorHandlingStyles(IConfigRegistry configRegistry) {
		IStyle validationErrorStyle = new Style();
		validationErrorStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_RED);
		validationErrorStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, GUIHelper.COLOR_WHITE);
		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, validationErrorStyle,
				DisplayMode.NORMAL, "INVALID");
	}
}
