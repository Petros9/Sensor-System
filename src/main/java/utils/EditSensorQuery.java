package utils;

import akka.actor.typed.ActorRef;
import utils.monitor_queries.MonitorQuery;

public class EditSensorQuery implements DispatcherQuery {
    public final String uuid;
    public final String newSensorOwnerName;
    public final String newSensorOwnerSurname;

    public final String newSensorAddressStreet;
    public final String newSensorAddressPostalCodeAndCity;
    public final String newSensorAddressCountry;
    public final ActorRef<MonitorQuery> monitorActorRef;
    public EditSensorQuery(String uuid, String newSensorOwnerName, String newSensorOwnerSurname, String newSensorAddressStreet, String newSensorAddressPostalCodeAndCity, String newSensorAddressCountry, ActorRef<MonitorQuery> monitorActorRef){
        this.uuid = uuid;
        this.newSensorOwnerName = newSensorOwnerName;
        this.newSensorOwnerSurname = newSensorOwnerSurname;
        this.newSensorAddressCountry = newSensorAddressCountry;
        this.newSensorAddressPostalCodeAndCity = newSensorAddressPostalCodeAndCity;
        this.newSensorAddressStreet = newSensorAddressStreet;
        this.monitorActorRef = monitorActorRef;
    }
}
