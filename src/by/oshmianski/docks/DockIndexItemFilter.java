package by.oshmianski.docks;

import by.oshmianski.docks.Setup.DockSimple;
import by.oshmianski.utils.IconContainer;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: oshmianski
 * Date: 15.09.13
 * Time: 22:14
 */
public class DockIndexItemFilter extends DockSimple {
    public DockIndexItemFilter() {
        super("DockIndexItemFilter", IconContainer.getInstance().loadImage("funnel.png"), "Фильтр данных");

        panel.setBorder(BorderFactory.createEmptyBorder(2, 3, 2, 0));
    }

    public void dispose(){

    }
}
