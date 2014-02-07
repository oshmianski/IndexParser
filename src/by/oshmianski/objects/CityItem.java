package by.oshmianski.objects;

/**
 * Created by vintselovich on 06.02.14.
 */
public class CityItem {
    private String region;
    private String district;
    private String unit;
    private String cityType;
    private String cityTitle;

    public CityItem(String region, String district, String unit, String cityType, String cityTitle) {
        this.region = region;
        this.district = district;
        this.unit = unit;
        this.cityType = cityType;
        this.cityTitle = cityTitle;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public String getUnit() {
        return unit;
    }

    public String getCityType() {
        return cityType;
    }

    public String getCityTitle() {
        return cityTitle;
    }
}
