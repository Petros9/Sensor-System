package utils;

public class AddNewSensorQuery implements DispatcherQuery {
    public final String uuid;
    public final String sensorOwnerName;
    public final String sensorOwnerSurname;

    public final String sensorAddressStreet;
    public final String sensorAddressPostalCodeAndCity;
    public final String sensorAddressCountry;

    public AddNewSensorQuery(String uuid, String sensorOwnerName, String sensorOwnerSurname, String sensorAddressStreet, String sensorAddressPostalCodeAndCity, String sensorAddressCountry){
        this.uuid = uuid;
        this.sensorOwnerName = sensorOwnerName;
        this.sensorOwnerSurname = sensorOwnerSurname;
        this.sensorAddressCountry = sensorAddressCountry;
        this.sensorAddressPostalCodeAndCity = sensorAddressPostalCodeAndCity;
        this.sensorAddressStreet = sensorAddressStreet;
    }
}
