package utils.monitor_queries;

import akka.actor.typed.ActorRef;
import utils.DispatcherQuery;

public class AddNewSensorMonitorQuery implements MonitorQuery {

    public final String sensorOwnerName;
    public final String sensorOwnerSurname;

    public final String sensorAddressStreet;
    public final String sensorAddressPostalCodeAndCity;
    public final String sensorAddressCountry;
    public final ActorRef<DispatcherQuery> dispatcherActorRef;

    public AddNewSensorMonitorQuery(String sensorOwnerName, String sensorOwnerSurname, String sensorAddressStreet, String sensorAddressPostalCodeAndCity, String sensorAddressCountry, ActorRef<DispatcherQuery> dispatcherActorRef){
        this.sensorOwnerName = sensorOwnerName;
        this.sensorOwnerSurname = sensorOwnerSurname;
        this.sensorAddressCountry = sensorAddressCountry;
        this.sensorAddressPostalCodeAndCity = sensorAddressPostalCodeAndCity;
        this.sensorAddressStreet = sensorAddressStreet;
        this.dispatcherActorRef = dispatcherActorRef;
    }
}
