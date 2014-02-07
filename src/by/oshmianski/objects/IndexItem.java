package by.oshmianski.objects;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: VintsalovichS
 * Date: 9/16/13
 * Time: 11:30 AM
 */
public class IndexItem {
    private String index;
    private String file;
    private PostOffice postOffice;
    private CityItem cityItem;

    public IndexItem(String index, CityItem cityItem, String file, PostOffice postOffice) {
        this.index = index;
        this.cityItem = cityItem;
        this.postOffice = postOffice;
        this.file = file;
    }

    public String getIndex() {
        return index;
    }

    public String getCityTitle() {
        return cityItem.getCityTitle();
    }

    public String getCityType() {
        return cityItem.getCityType();
    }

    public String getFile() {
        return file;
    }

    public String getCityRegion(){
        return cityItem.getRegion();
    }

    public String getCityDistrict(){
        return cityItem.getDistrict();
    }

    public String getCityUnit(){
        return cityItem.getUnit();
    }
}
