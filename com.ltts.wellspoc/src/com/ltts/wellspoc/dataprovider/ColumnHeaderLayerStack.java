package com.ltts.wellspoc.dataprovider;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;

import com.ltts.wellspoc.ui.views.WellDetailsView;

/**
 * @author Deepika KS
 * Subclass of AbstractLayerTransform which are expected to override methods in this
 * class to implement column layer transformations.
 *
 */
public class ColumnHeaderLayerStack extends AbstractLayerTransform {

    /**
     * @param dataProvider
     */
    public ColumnHeaderLayerStack(IDataProvider dataProvider) {
        DataLayer dataLayer = new DataLayer(dataProvider);
        ColumnHeaderLayer colHeaderLayer = new ColumnHeaderLayer(dataLayer,WellDetailsView.getBodyLayer(), WellDetailsView.getBodyLayer().getSelectionLayer());
        setUnderlyingLayer(colHeaderLayer);
    }
}
