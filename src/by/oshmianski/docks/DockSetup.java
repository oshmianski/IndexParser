package by.oshmianski.docks;

import by.oshmianski.docks.Setup.DockSimple;
import by.oshmianski.docks.Setup.DockingContainer;
import by.oshmianski.loaders.LoadIndex;
import by.oshmianski.ui.utils.ActionButton;
import by.oshmianski.utils.IconContainer;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: oshmianski
 * Date: 15.09.13
 * Time: 22:14
 */
public class DockSetup extends DockSimple {
    private DockingContainer dockingContainer;
    private ActionButton buttonStart;
    private ActionButton buttonStop;
    private ActionButton getFolder;
    private JTextField folder;
    private JComboBox parserType;
    private JProgressBar progress;
    private JLabel progressLabel;

    private final static String dockTitle = "Настройка парсера";

    public DockSetup(final DockingContainer dockingContainer) {
        super("DockSetup", IconContainer.getInstance().loadImage("layers.png"), dockTitle);

        this.dockingContainer = dockingContainer;

        folder = new JTextField("d:\\temp\\parser\\zip_code");
        parserType = new JComboBox(new ParserTypeModel());
        parserType.setSelectedIndex(0);
        getFolder = new ActionButton("...", null, new Dimension(25, 20), "Выбрать папку");
        getFolder.addActionListener(new ActionListenerGetFolder());

        buttonStart = new ActionButton("Старт", null, new Dimension(80, 30), "Запуск чтения реестра");
        buttonStop = new ActionButton("Стоп", null, new Dimension(80, 30), "Остановить чтение реестра");
        buttonStop.setEnabled(false);

        progress = new JProgressBar();
        progress.setStringPainted(true);

        progressLabel = new JLabel("");

        buttonStart.addActionListener(new ActionListenerStart());

        buttonStop.addActionListener(new ActionListenerStop());

        FormLayout layout = new FormLayout(
                "5px, right:250px, 5px, fill:350px, 5px, 25px, 5px", // columns
                "2px, 26px, 2px, 26px, 2px");      // rows

//        FormDebugPanel debugPanel = new FormDebugPanel(layout);
//        PanelBuilder builder = new PanelBuilder(layout, debugPanel);
        PanelBuilder builder = new PanelBuilder(layout);

        // Obtain a reusable constraints objects to place components in the grid.
        CellConstraints cc = new CellConstraints();

        builder.addLabel("Папка для парсинга:", cc.xy(2, 2));
        builder.add(folder, cc.xy(4, 2));
        builder.add(getFolder, cc.xy(6, 2));
        builder.addLabel("Тип парсера:", cc.xy(2, 4));
        builder.add(parserType, cc.xy(4, 4));

        panel.add(builder.getPanel(), BorderLayout.NORTH);

        FormLayout layoutButton = new FormLayout(
                "5px, right:140px, 20px, left:90px, 5px, fill:200px, 5px, left:100px, 5px", // columns
                "2px, 40px, 10px");      // rows

//            FormDebugPanel debugPanel = new FormDebugPanel(layout);
//            PanelBuilder builder = new PanelBuilder(layout, debugPanel);
        PanelBuilder builderButton = new PanelBuilder(layoutButton);

        // Obtain a reusable constraints objects to place components in the grid.
        CellConstraints ccButton = new CellConstraints();

        builderButton.add(buttonStart, ccButton.xy(2, 2));
        builderButton.add(buttonStop, ccButton.xy(4, 2));
        builderButton.add(progress, ccButton.xy(6, 2));
        builderButton.add(progressLabel, ccButton.xy(8, 2));

        panel.add(builderButton.getPanel(), BorderLayout.CENTER);

    }

    public void dispose() {
        System.out.println("DockSetup clear...");

        System.out.println("DockSetup clear...OK");
    }

    public void setStartEnable(boolean enable) {
        buttonStop.setEnabled(!enable);
        buttonStart.setEnabled(enable);
    }

    private class ActionListenerGetFolder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoadIndex loader = dockingContainer.getLoader();
            if (loader.isExecuted()) {
                loader.cancel();
            }
        }
    }

    private class ActionListenerStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoadIndex loader = dockingContainer.getLoader();

            if (loader.isExecuted()) {
                JOptionPane.showMessageDialog(
                        null,
                        "В данные момент выполняется загрузка!\n" +
                                "Действие отменено.",
                        "Внимание",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                loader.execute();
            }
        }
    }

    private static class ActionListenerStop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void setProgressMaximum(int count) {
        progress.setMaximum(count);
    }

    public void setProgressValue(int value) {
        progress.setValue(value);
    }

    public void setProgressLabelText(String text) {
        progressLabel.setText(text);
    }

    private static class ParserTypeModel extends AbstractListModel implements ComboBoxModel {
        String[] ComputerComps = {"zip_code"};

        String selection = null;

        public Object getElementAt(int index) {
            return ComputerComps[index];
        }

        public int getSize() {
            return ComputerComps.length;
        }

        public void setSelectedItem(Object anItem) {
            selection = (String) anItem; // to select and register an
        } // item from the pull-down list

        // Methods implemented from the interface ComboBoxModel
        public Object getSelectedItem() {
            return selection; // to add the selection to the combo box
        }
    }

    public String getFolderPath(){
        return folder.getText();
    }

    public String getParserType(){
        return (String)parserType.getSelectedItem();
    }
}
