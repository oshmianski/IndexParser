package by.oshmianski.ui.edt;

import by.oshmianski.objects.IndexItem;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;

/**
 * Created with IntelliJ IDEA.
 * User: 8-058
 * Date: 05.04.13
 * Time: 9:59
 */
public interface UIProcessor {
    @RequiresEDT
    void appendIndex(IndexItem item);

    @RequiresEDT
    void setProgressLabelText(String text);

    @RequiresEDT
    void setProgressValue(int count);

    @RequiresEDT
    void setProgressMaximum(int maximum);

    @RequiresEDT
    void clearIndex();

    @RequiresEDT
    void setStartEnable(boolean enable);

    String getFolderPath();

    String getParserType();
}
