package me.thomassuebwicha.domain.property;

public class Property {
    private String street;
    private String suburb;
    private String houseType;
    private Integer bedrooms;
    private String sellingAll;

    public Property(String street, String suburb, String houseType, Integer bedrooms, String sellingAll) {
        this.street = street;
        this.suburb = suburb;
        this.houseType = houseType;
        this.bedrooms = bedrooms;
        this.sellingAll = sellingAll;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getSellingAll() {
        return sellingAll;
    }

    public void setSellingAll(String sellingAll) {
        this.sellingAll = sellingAll;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Property{");
        sb.append("street='").append(street).append('\'');
        sb.append(", suburb='").append(suburb).append('\'');
        sb.append(", houseType='").append(houseType).append('\'');
        sb.append(", bedrooms=").append(bedrooms);
        sb.append(", sellingAll='").append(sellingAll).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
