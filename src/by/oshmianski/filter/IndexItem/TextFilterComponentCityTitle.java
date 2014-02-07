package by.oshmianski.filter.IndexItem;

import by.oshmianski.docks.Setup.DockingContainer;
import by.oshmianski.filter.FilterComponent;
import by.oshmianski.objects.IndexItem;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.AbstractMatcherEditor;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author <a href="mailto:jesse@swank.ca">Jesse Wilson</a>
 */
public class TextFilterComponentCityTitle extends AbstractMatcherEditor implements FilterComponent<IndexItem> {

    private static final TextFilterator<IndexItem> ISSUE_TEXT_FILTERATOR = new FilteratorDMCityTitle();

    private JTextField filterEdit = new JTextField(15);
    private TextComponentMatcherEditor<IndexItem> textComponentMatcherEditor = new TextComponentMatcherEditor<IndexItem>(filterEdit, ISSUE_TEXT_FILTERATOR, true);

    private DockingContainer container;

    public TextFilterComponentCityTitle(final DockingContainer container) {
        this.container = container;

        textComponentMatcherEditor.addMatcherEditorListener(new Listener<IndexItem>() {
            @Override
            public void changedMatcher(Event<IndexItem> dataMainItemEvent) {
                if (container.getUIProcessor() != null) {
//                    container.getUIProcessor().setFilteredCount();
                }
            }
        });
    }

    public JComponent getComponent() {
        return filterEdit;
    }

    @Override
    public boolean isSelectDeselectAllVisible() {
        return false;
    }

    @Override
    public ActionListener getSelectAction() {
        return null;
    }

    @Override
    public ActionListener getDeselectAction() {
        return null;
    }

    @Override
    public MatcherEditor<IndexItem> getMatcherEditor() {
        return textComponentMatcherEditor;
    }

    @Override
    public void dispose() {

    }

    public String toString() {
        return "Название нас. пункта";
    }

    public void fireMatchAllA() {

    }
}