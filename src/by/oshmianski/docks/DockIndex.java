package by.oshmianski.docks;

import by.oshmianski.docks.Setup.DockSimple;
import by.oshmianski.docks.Setup.DockingContainer;
import by.oshmianski.filter.IndexItem.FilterPanel;
import by.oshmianski.main.AppletWindowFrame;
import by.oshmianski.models.IndexModel;
import by.oshmianski.objects.IndexItem;
import by.oshmianski.ui.utils.BetterJTable;
import by.oshmianski.ui.utils.ColorRenderer;
import by.oshmianski.ui.utils.niceScrollPane.NiceScrollPane;
import by.oshmianski.utils.IconContainer;
import by.oshmianski.utils.MyLog;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.AbstractTableComparatorChooser;
import ca.odell.glazedlists.swing.DefaultEventSelectionModel;
import ca.odell.glazedlists.swing.DefaultEventTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: oshmianski
 * Date: 15.09.13
 * Time: 22:14
 */
public class DockIndex extends DockSimple {
    private DockingContainer dockingContainer;
    private EventList<IndexItem> indexItems = new BasicEventList<IndexItem>();

    private DefaultEventTableModel model;
    private EventList<IndexItem> entries;
    private SortedList<IndexItem> sortedEntries;
    private FilterList<IndexItem> filteredEntries;
    private JTable table;
    private DefaultEventSelectionModel issuesSelectionModel;
    private static final String frameTitle = "Данные";
    private FilterPanel filterPanel;

    public DockIndex(DockingContainer dockingContainer) {
        super("DockIndex", IconContainer.getInstance().loadImage("grid.png"), frameTitle);

        this.dockingContainer = dockingContainer;

        indexItems.getReadWriteLock().writeLock().lock();

        table = null;
        JScrollPane sp;

        try {
            entries = GlazedListsSwing.swingThreadProxyList(this.indexItems);

            filterPanel = new FilterPanel(entries, true, dockingContainer);

            sortedEntries = new SortedList<IndexItem>(entries, null);

            filteredEntries = new FilterList<IndexItem>(sortedEntries, filterPanel.getMatcherEditor());

            model = new DefaultEventTableModel(filteredEntries, new IndexModel(filteredEntries));

            filterPanel.install(model);

            table = new BetterJTable(null, true);

            table.setModel(model);

            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setPreferredWidth(200);
            table.getColumnModel().getColumn(5).setPreferredWidth(300);
            table.getColumnModel().getColumn(6).setPreferredWidth(70);
            table.getColumnModel().getColumn(7).setPreferredWidth(200);

            table.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.BLACK, false));
            table.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.BLUE, false));
            table.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(new Color(0xC26802), false));
            table.getColumnModel().getColumn(3).setCellRenderer(new ColorRenderer(Color.BLUE, false));
            table.getColumnModel().getColumn(4).setCellRenderer(new ColorRenderer(Color.BLACK, false));
            table.getColumnModel().getColumn(5).setCellRenderer(new ColorRenderer(Color.BLUE, false));
            table.getColumnModel().getColumn(6).setCellRenderer(new ColorRenderer(Color.BLACK, false));
            table.getColumnModel().getColumn(7).setCellRenderer(new ColorRenderer(Color.BLUE, false));

            table.setRowHeight(20);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setIntercellSpacing(new Dimension(1, 1));
            table.setGridColor(AppletWindowFrame.DATA_TABLE_GRID_COLOR);
            table.setSelectionBackground(new Color(217, 235, 245));
            table.setSelectionForeground(Color.BLACK);

            TableComparatorChooser.install(table, sortedEntries, AbstractTableComparatorChooser.MULTIPLE_COLUMN_MOUSE_WITH_UNDO);

            issuesSelectionModel = new DefaultEventSelectionModel(filteredEntries);
            issuesSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            issuesSelectionModel.addListSelectionListener(new IssuesSelectionListener(dockingContainer, issuesSelectionModel));
            table.setSelectionModel(issuesSelectionModel);

            sp = new NiceScrollPane(table);

            sp.setBackground(AppletWindowFrame.REPORT_PANEL_BACKGROUND);
            sp.getViewport().setBackground(AppletWindowFrame.REPORT_PANEL_BACKGROUND);

            panel.add(sp);

            dockingContainer.setIndexItemFilter(filterPanel);

        } catch (Exception e) {
            MyLog.add2Log(e);
        } finally {
            this.indexItems.getReadWriteLock().writeLock().unlock();
        }
    }

    public void clearIndex() {
        indexItems.clear();
        filterPanel.getTextFilterComponentCityTitle().fireMatchAllA();
    }

    public void appendIndex(IndexItem indexItem) {
        indexItems.add(indexItem);
    }

    /**
     * Listens for changes in the selection on the issues table.
     */
    private static class IssuesSelectionListener implements ListSelectionListener {
        private DefaultEventSelectionModel issuesSelectionModel;
        private DockingContainer dockingContainer;

        IssuesSelectionListener(DockingContainer dockingContainer, DefaultEventSelectionModel issuesSelectionModel) {
            super();

            this.dockingContainer = dockingContainer;
            this.issuesSelectionModel = issuesSelectionModel;
        }

        public void valueChanged(ListSelectionEvent e) {
            IndexItem selectedItem = null;
            if (issuesSelectionModel.getSelected().size() > 0) {
                Object selectedObject = issuesSelectionModel.getSelected().get(0);
                if (selectedObject instanceof IndexItem) {
                    selectedItem = (IndexItem) selectedObject;
                }
            }

            if (selectedItem == null) return;

//            dockingContainer.getUIProcessor().setDockDataChildItems(selectedItem);
//            dockingContainer.getUIProcessor().setDockObjectTreeObjects(selectedItem);
//            dockingContainer.getUIProcessor().setDockAddressParserItems(selectedItem);
        }
    }

    public EventList<IndexItem> getIndexItems() {
        return indexItems;
    }

    public void dispose() {
        System.out.println("DockIndex clear...");

        indexItems.clear();
        indexItems.dispose();
        indexItems = null;

        if (model != null) model = null;
        if (filteredEntries != null) filteredEntries.dispose();
        if (sortedEntries != null) sortedEntries.dispose();
        if (entries != null) entries.dispose();
        if (indexItems != null) indexItems.dispose();

        System.out.println("DockIndex clear...OK");
    }
}
