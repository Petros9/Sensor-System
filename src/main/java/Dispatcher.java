import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher extends AbstractBehavior<DispatcherQuery> {
    private final Map<String, Sensor> sensors = new HashMap<>();

    public Dispatcher(ActorContext<DispatcherQuery> context){
        super(context);
    }

    public static Behavior<DispatcherQuery> create(){
        return Behaviors.setup(Dispatcher::new);
    }

    @Override
    public Receive<DispatcherQuery> createReceive(){
        return newReceiveBuilder()
                .onMessage(AddNewSensorQuery.class, this::onAddNewSensorQuery)
                .onMessage(DeleteSensorQuery.class, this::onDeleteSensorQuery)
                .onMessage(EditSensorQuery.class, this::onEditSensorQuery)
                .onMessage(GetAllSensorsQuery.class, this::onGetAllSensorsQuery)
                .onMessage(GetSensorByIdQuery.class, this::onGetSensorByIdQuery)
                .build();
    }

    private Behavior<DispatcherQuery> onAddNewSensorQuery(AddNewSensorQuery query){
        Sensor sensor = new Sensor(query.uuid, query.sensorOwnerName, query.sensorOwnerSurname, query.sensorAddressStreet, query.sensorAddressPostalCodeAndCity, query.sensorAddressCountry);
        this.sensors.put(query.uuid, sensor);
        return this;
    }

    private Behavior<DispatcherQuery> onDeleteSensorQuery(DeleteSensorQuery query){
        if(sensors.containsKey(query.uuid)){
            sensors.remove(query.uuid);
        } else {
            query.monitorActorRef.tell(new ErrorResponse("NO SENSOR WITH SUCH ID"));
        }
        return this;
    }

    private Behavior<DispatcherQuery> onEditSensorQuery(EditSensorQuery query){
        synchronized (sensors) {
            if (sensors.containsKey(query.uuid)) {
                sensors.get(query.uuid).editOwnerAndAddress(
                        query.newSensorOwnerName,
                        query.newSensorOwnerSurname,
                        query.newSensorAddressStreet,
                        query.newSensorAddressPostalCodeAndCity,
                        query.newSensorAddressCountry);
            } else {
                query.monitorActorRef.tell(new ErrorResponse("NO SENSOR WITH SUCH ID"));
            }
        }
        return this;
    }
    private Behavior<DispatcherQuery> onGetAllSensorsQuery(GetAllSensorsQuery query){
        query.monitorActorRef.tell(new GetAllSensorsResponse(new ArrayList<>(sensors.values())));
        return this;
    }
    private Behavior<DispatcherQuery> onGetSensorByIdQuery(GetSensorByIdQuery query){
        if(sensors.containsKey(query.uuid)){
            query.monitorActorRef.tell(new GetSensorByIdResponse(sensors.get(query.uuid)));
        } else {
            query.monitorActorRef.tell(new ErrorResponse("NO SENSOR WITH SUCH ID"));
        }
        return this;
    }
}
