package com.ltts.wellspoc.dataprovider;

import java.util.Arrays;

import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultIntegerDisplayConverter;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.edit.editor.ComboBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.TextCellEditor;
import org.eclipse.nebula.widgets.nattable.layer.cell.ColumnLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.painter.cell.ComboBoxPainter;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;

import com.ltts.wellspoc.models.Well;

public class EditConfiguration extends AbstractRegistryConfiguration {

	@Override
	public void configureRegistry(IConfigRegistry configRegistry) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE);
		registerEditors(configRegistry);
	}

	private void registerEditors(IConfigRegistry configRegistry) {

		registerEastingEditor(configRegistry, 1);
		registerNorthingEditor(configRegistry, 2);
		registerAzimuthEditor(configRegistry, 3);
		registerFieldEditor(configRegistry, 4);
		registerReservoirEditor(configRegistry, 5);

		registertypeEditor(configRegistry, 6);
	}

	private void registertypeEditor(IConfigRegistry configRegistry, int columnIndex) {
//		ComboBoxCellEditor combobox = new ComboBoxCellEditor(
//				Arrays.asList((new String[] { "vertical", "horizontal" })));
		
		ComboBoxCellEditor combobox = new ComboBoxCellEditor(
				Arrays.asList(Well.WellType.values()));

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, combobox, DisplayMode.EDIT,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);

		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new ComboBoxPainter(),
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
		
//		ComboBoxCellEditor comboBoxCellEditor = new ComboBoxCellEditor(Arrays.asList(Gender.FEMALE, Gender.MALE));
//		
//        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, comboBoxCellEditor, DisplayMode.EDIT,
//                ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
//
//        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new ComboBoxPainter(),
//                DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
		
	}

	private void registerReservoirEditor(IConfigRegistry configRegistry, int i) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, new TextCellEditor(true, true),
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);

		// configure to open the adjacent editor after commit
		configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_ADJACENT_EDITOR, Boolean.TRUE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);
	}

	private void registerFieldEditor(IConfigRegistry configRegistry, int i) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, new TextCellEditor(true, true),
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);

		// configure to open the adjacent editor after commit
		configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_ADJACENT_EDITOR, Boolean.TRUE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);
	}

	private void registerAzimuthEditor(IConfigRegistry configRegistry, int i) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, new TextCellEditor(true, true),
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);

		// configure to open the adjacent editor after commit
		configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_ADJACENT_EDITOR, Boolean.TRUE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);
	}

	private void registerNorthingEditor(IConfigRegistry configRegistry, int i) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, new TextCellEditor(true, true),
				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);

		// configure to open the adjacent editor after commit
		configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_ADJACENT_EDITOR, Boolean.TRUE,
				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);
	}

//	private void registerWellNameEditor(IConfigRegistry configRegistry, int i) {
//		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, new TextCellEditor(true, true),
//				DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);
//
//		// configure to open the adjacent editor after commit
//		configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_ADJACENT_EDITOR, Boolean.TRUE,
//				DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);
//	}

	private void registerEastingEditor(IConfigRegistry configRegistry, int i) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE);

		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultIntegerDisplayConverter(), DisplayMode.NORMAL);
		configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
				new DefaultIntegerDisplayConverter(), DisplayMode.EDIT);
//		configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, getValidation(), DisplayMode.EDIT);
	}

//	private IDataValidator getValidation() {
//		return new DataValidator() {
//
//			@Override
//			public boolean validate(int columnIndex, int rowIndex, Object newValue) {
//				if (newValue instanceof Integer && ((Integer) newValue).intValue() > 10000) {
//					return true;
//				} else {
//					throw new ValidationFailedException("The value has to be bigger than 10000");
//				}
//			}
//		};
//	}

}
