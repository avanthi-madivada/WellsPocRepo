package com.ltts.wellspoc.dataprovider;

import java.util.Arrays;

import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultBooleanDisplayConverter;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.edit.editor.CheckBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ComboBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.TextCellEditor;
import org.eclipse.nebula.widgets.nattable.layer.cell.ColumnLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.painter.cell.CheckBoxPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.ComboBoxPainter;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;

public class EditConfiguration extends AbstractRegistryConfiguration{

	@Override
	public void configureRegistry(IConfigRegistry configRegistry) {

		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE,
                IEditableRule.ALWAYS_EDITABLE);
		registerEditors(configRegistry);
		/*
		 * Style cellStyle = new Style();
		 * cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR,
		 * GUIHelper.COLOR_YELLOW);
		 * configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE,
		 * cellStyle, DisplayMode.NORMAL, FOO_LABEL);
		 * 
		 * cellStyle = new Style();
		 * cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR,
		 * GUIHelper.COLOR_RED);
		 * configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE,
		 * cellStyle, DisplayMode.NORMAL, CELL_LABEL);
		 */// TODO Auto-generated method stub
		
	}
	private void registerEditors(IConfigRegistry configRegistry) {
		
		  registerEastingEditor(configRegistry, 1);
		  registerNorthingEditor(configRegistry, 2);
		  registerAzimuthEditor(configRegistry, 3); 
		  registerFieldEditor(configRegistry,4); 
		  registerReservoirEditor(configRegistry, 5);
		 
		registertypeEditor(configRegistry,6);
	}

	private void registertypeEditor(IConfigRegistry configRegistry, int columnIndex) 
	{
		ComboBoxCellEditor combobox=new ComboBoxCellEditor(Arrays.asList((new String[] {"vertical", "horizontal"})));
			
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR,combobox,DisplayMode.EDIT,
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
		
		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER,
				new ComboBoxPainter(), 
				DisplayMode.EDIT, 
				ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + columnIndex);
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

	private void registerWellNameEditor(IConfigRegistry configRegistry, int i) {
		configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, new TextCellEditor(true, true),
                DisplayMode.NORMAL, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);

        // configure to open the adjacent editor after commit
        configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_ADJACENT_EDITOR, Boolean.TRUE,
                DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);				
	}

	private void registerEastingEditor(IConfigRegistry configRegistry, int i) {
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITABLE_RULE, 
				IEditableRule.ALWAYS_EDITABLE, 
				DisplayMode.EDIT, 
				"Easting");
        // configure to open the adjacent editor after commit
        configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_ADJACENT_EDITOR, Boolean.TRUE,
                DisplayMode.EDIT, ColumnLabelAccumulator.COLUMN_LABEL_PREFIX + i);				
	}

}
