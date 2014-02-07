package by.oshmianski.models;

import by.oshmianski.objects.IndexItem;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.gui.WritableTableFormat;

/**
 * Created with IntelliJ IDEA.
 * User: VintsalovichS
 * Date: 9/16/13
 * Time: 11:34 AM
 */
public class IndexModel implements WritableTableFormat<IndexItem> {
    private String[] colsTitle = {"#", "Индекс", "Область", "Район", "Совет", "Населенный пункт", "Тип нп", "Из файла"};

    private EventList eventList;

    public IndexModel(EventList eventList) {
        this.eventList = eventList;
    }

    @Override
    public int getColumnCount() {
        return colsTitle.length;
    }

    @Override
    public String getColumnName(int i) {
        return colsTitle[i];
    }

    @Override
    public Object getColumnValue(IndexItem indexItem, int i) {
        switch (i) {
            case 0:
                return eventList.indexOf(indexItem) + 1;
            case 1:
                return indexItem.getIndex();
            case 2:
                return indexItem.getCityRegion();
            case 3:
                return indexItem.getCityDistrict();
            case 4:
                return indexItem.getCityUnit();
            case 5:
                return indexItem.getCityTitle();
            case 6:
                return indexItem.getCityType();
            case 7:
                return indexItem.getFile();
            default:
                return null;
        }
    }


    @Override
    public boolean isEditable(IndexItem indexItem, int i) {
        return false;
    }

    @Override
    public IndexItem setColumnValue(IndexItem indexItem, Object o, int i) {
        return null;
    }
}
