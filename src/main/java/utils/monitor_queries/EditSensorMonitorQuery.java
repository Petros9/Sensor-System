package utils.monitor_queries;

import akka.actor.typed.ActorRef;
import utils.DispatcherQuery;

public class EditSensorMonitorQuery implements MonitorQuery {
    public final String uuid;
    public final String newSensorOwnerName;
    public final String newSensorOwnerSurname;

    public final String newSensorAddressStreet;
    public final String newSensorAddressPostalCodeAndCity;
    public final String newSensorAddressCountry;
    public final ActorRef<DispatcherQuery> dispatcherActorRef;
    public EditSensorMonitorQuery(String uuid, String newSensorOwnerName, String newSensorOwnerSurname, String newSensorAddressStreet, String newSensorAddressPostalCodeAndCity, String newSensorAddressCountry, ActorRef<DispatcherQuery> dispatcherActorRef){
        this.uuid = uuid;
        this.newSensorOwnerName = newSensorOwnerName;
        this.newSensorOwnerSurname = newSensorOwnerSurname;
        this.newSensorAddressCountry = newSensorAddressCountry;
        this.newSensorAddressPostalCodeAndCity = newSensorAddressPostalCodeAndCity;
        this.newSensorAddressStreet = newSensorAddressStreet;
        this.dispatcherActorRef = dispatcherActorRef;
    }
}
