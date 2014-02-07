package by.oshmianski.objects;

import by.oshmianski.docks.*;
import by.oshmianski.ui.edt.UIProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: VintsalovichS
 * Date: 9/16/13
 * Time: 2:35 PM
 */
public class UIProcessorImpl implements UIProcessor {
    private DockIndex dockIndex;
    private DockSetup dockSetup;

    public UIProcessorImpl(DockIndex dockIndex, DockSetup dockSetup) {
        this.dockIndex = dockIndex;
        this.dockSetup = dockSetup;
    }

    @Override
    public void appendIndex(IndexItem item) {
        dockIndex.appendIndex(item);
    }

    @Override
    public void setProgressLabelText(String text) {
        dockSetup.setProgressLabelText(text);
    }

    @Override
    public void setProgressValue(int count) {
        dockSetup.setProgressValue(count);
    }

    @Override
    public void setProgressMaximum(int maximum) {
        dockSetup.setProgressMaximum(maximum);
    }

    @Override
    public void clearIndex() {
        dockIndex.clearIndex();
    }

    @Override
    public void setStartEnable(boolean enable) {
        dockSetup.setStartEnable(enable);
    }

    @Override
    public String getFolderPath() {
        return dockSetup.getFolderPath();
    }

    @Override
    public String getParserType() {
        return dockSetup.getParserType();
    }
}
