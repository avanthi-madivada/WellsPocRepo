package com.ltts.wellspoc.ui.views;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;

public class RowHeaderLayerStack extends AbstractLayerTransform {

    public RowHeaderLayerStack(IDataProvider dataProvider) {
        DataLayer dataLayer = new DataLayer(dataProvider, 50, 20);
        RowHeaderLayer rowHeaderLayer = new RowHeaderLayer(dataLayer,WellDetailsView.bodyLayer, WellDetailsView.bodyLayer.getSelectionLayer());setUnderlyingLayer(rowHeaderLayer);
    }
}
