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
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;

public class WellEditConfiguration extends AbstractRegistryConfiguration {

	@Override
	public void configureRegistry(IConfigRegistry configRegistry) {
		registerWellNameEditor(configRegistry, 0);
		registerEastingsEditor(configRegistry, 1);
		registerNorthingsEditor(configRegistry, 2);
		registerAzimuthEditor(configRegistry, 3);
		registerWellTypeEditor(configRegistry, 6);
	}

	private void registerNorthingsEditor(IConfigRegistry configRegistry, int columnIndex) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultDoubleDisplayConverter(), DisplayMode.NORMAL,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

	}

	private void registerEastingsEditor(IConfigRegistry configRegistry, int columnIndex) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultDoubleDisplayConverter(), DisplayMode.NORMAL,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
	}

	private void registerWellNameEditor(IConfigRegistry configRegistry, int columnIndex) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
	}

	private void registerAzimuthEditor(IConfigRegistry configRegistry, int columnIndex) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultDoubleDisplayConverter(), DisplayMode.NORMAL,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
	}

	private void registerWellTypeEditor(IConfigRegistry configRegistry, int columnIndex) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR,
				new ComboBoxCellEditor(Arrays.asList(new String[] { "Horizontal", "Vertical", "Deviated", "S-Well" })),
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
	}
}
