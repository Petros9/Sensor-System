package utils;

import station_properties.Address;
import station_properties.Owner;

public class Sensor {
    private String uuid;
    private Owner owner;
    private Address address;

    public Sensor(String uuid, String ownerName, String ownerSurname, String addressStreet, String addressPostalCodeAndCity, String addressCountry) {
        this.uuid = uuid;
        this.owner = new Owner(ownerName, ownerSurname);
        this.address = new Address(addressStreet, addressPostalCodeAndCity, addressCountry);
    }

    public void editOwnerAndAddress(String newOwnerName, String newOwnerSurname, String newAddressStreet, String newAddressPostalCodeAndCity, String newAddressCountry){
        if(newOwnerName != null){
            this.owner.setName(newOwnerName);
        }
        if(newOwnerSurname != null){
            this.owner.setSurname(newOwnerSurname);
        }
        if(newAddressStreet != null){
            this.address.setStreet(newAddressStreet);
        }
        if(newAddressPostalCodeAndCity != null){
            this.address.setPostalCodeAndCity(newAddressPostalCodeAndCity);
        }
        if(newAddressCountry != null){
            this.address.setCountry(newAddressCountry);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public Owner getOwner() {
        return owner;
    }

    public Address getAddress() {
        return address;
    }
}