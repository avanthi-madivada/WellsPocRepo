package com.ltts.wellspoc.dataprovider;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.hideshow.ColumnHideShowLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.reorder.ColumnReorderLayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;

/**
 * @author Deepika KS
 *
 */
public class BodyLayerStack extends AbstractLayerTransform {

    private SelectionLayer selectionLayer;

    /**
     * @param dataProvider
     */
    public BodyLayerStack(IDataProvider dataProvider) {
        DataLayer bodyDataLayer = new DataLayer(dataProvider);
        ColumnReorderLayer columnReorderLayer = new ColumnReorderLayer(bodyDataLayer);
        ColumnHideShowLayer columnHideShowLayer = new ColumnHideShowLayer(columnReorderLayer);
        this.selectionLayer = new SelectionLayer(columnHideShowLayer);
        ViewportLayer viewportLayer = new ViewportLayer(this.selectionLayer);
        setUnderlyingLayer(viewportLayer);
    }

    /**
     * @return SelectionLayer
     */
    public SelectionLayer getSelectionLayer() {
        return this.selectionLayer;
    }
}
