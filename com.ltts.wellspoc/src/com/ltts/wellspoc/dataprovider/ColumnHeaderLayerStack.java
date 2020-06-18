package com.ltts.wellspoc.dataprovider;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;

import com.ltts.wellspoc.ui.views.WellDetailsView;

/**
 * @author Deepika KS
 *
 */
public class ColumnHeaderLayerStack extends AbstractLayerTransform {

    /**
     * @param dataProvider
     */
    public ColumnHeaderLayerStack(IDataProvider dataProvider) {
        DataLayer dataLayer = new DataLayer(dataProvider);
        ColumnHeaderLayer colHeaderLayer = new ColumnHeaderLayer(dataLayer,WellDetailsView.bodyLayer, WellDetailsView.bodyLayer.getSelectionLayer());
        setUnderlyingLayer(colHeaderLayer);
    }
}