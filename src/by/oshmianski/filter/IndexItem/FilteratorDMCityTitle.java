package by.oshmianski.filter.IndexItem;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import by.oshmianski.objects.IndexItem;
import ca.odell.glazedlists.TextFilterator;

import java.util.List;

public class FilteratorDMCityTitle implements TextFilterator<IndexItem> {
    public void getFilterStrings(List<String> baseList, IndexItem item) {

        baseList.add(item.getCityTitle());
    }
}