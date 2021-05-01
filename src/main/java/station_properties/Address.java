package station_properties;

public class Address {
    private String street;
    private String postalCodeAndCity;
    private String country;

    public Address(String street, String postalCodeAndCity, String country){
        this.street = street;
        this.postalCodeAndCity = postalCodeAndCity;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCodeAndCity() {
        return postalCodeAndCity;
    }

    public String getCountry() {
        return country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCodeAndCity(String postalCodeAndCity) {
        this.postalCodeAndCity = postalCodeAndCity;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
