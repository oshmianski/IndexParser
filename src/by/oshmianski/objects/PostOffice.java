package by.oshmianski.objects;

import java.util.ArrayList;

/**
 * Created by vintselovich on 06.02.14.
 */
public class PostOffice {
    private String index;
    private String title;
    private String region;
    private String district;
    private String unit;
    private String chief;
    private String chiefDeputy;
    private String phone;
    private String fax;
    private String cityCode;
    private String cityCodeInternational;
    private String phoneDelivery;
    private String address;

    public PostOffice() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public PostOffice(String index, String title, String region, String district, String unit, String chief, String chiefDeputy, String phone, String fax, String cityCode, String cityCodeInternational, String phoneDelivery, String address) {
        this.index = index;
        this.title = title;
        this.region = region;
        this.district = district;
        this.unit = unit;
        this.chief = chief;
        this.chiefDeputy = chiefDeputy;
        this.phone = phone;
        this.fax = fax;
        this.cityCode = cityCode;
        this.cityCodeInternational = cityCodeInternational;
        this.phoneDelivery = phoneDelivery;
        this.address = address;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public String getChiefDeputy() {
        return chiefDeputy;
    }

    public void setChiefDeputy(String chiefDeputy) {
        this.chiefDeputy = chiefDeputy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getPhoneDelivery() {
        return phoneDelivery;
    }

    public void setPhoneDelivery(String phoneDelivery) {
        this.phoneDelivery = phoneDelivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityCodeInternational() {
        return cityCodeInternational;
    }

    public void setCityCodeInternational(String cityCodeInternational) {
        this.cityCodeInternational = cityCodeInternational;
    }
}
