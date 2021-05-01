import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import utils.*;
import utils.monitor_queries.*;

import java.util.ArrayList;
import java.util.UUID;


public class Monitor extends AbstractBehavior<MonitorQuery> {
    private String name;
    public Monitor(ActorContext<MonitorQuery> context){
        super(context);
    }

    public static Behavior<MonitorQuery> create(){
        return Behaviors.setup(Monitor::new);
    }

    @Override
    public Receive<MonitorQuery> createReceive(){
        return newReceiveBuilder()
                .onMessage(GetAllSensorsResponse.class, this::onGetAllSensorsResponse)
                .onMessage(GetSensorByIdResponse.class, this::onGetSensorByIdResponse)
                .onMessage(SetMonitorNameQuery.class, this::onSetMonitorNameQuery)
                .onMessage(ErrorResponse.class, this::onErrorResponse)
                .onMessage(AddNewSensorMonitorQuery.class, this::onAddNewSensorMonitorQuery)
                .onMessage(DeleteSensorMonitorQuery.class, this::onDeleteSensorMonitorQuery)
                .onMessage(EditSensorMonitorQuery.class, this::onEditSensorMonitorQuery)
                .onMessage(GetAllSensorsMonitorQuery.class, this::onGetAllSensorsMonitorQuery)
                .onMessage(GetSensorByIdMonitorQuery.class, this::onGetSensorByIdMonitorQuery)
                .build();
    }

    private Behavior<MonitorQuery> onGetAllSensorsResponse(GetAllSensorsResponse response){
        ArrayList<Sensor> sensors = response.sensors;
        sensors.forEach(sensor ->
            System.out.println(sensor.getUuid())
        );
        return this;
    }

    private Behavior<MonitorQuery> onGetSensorByIdResponse(GetSensorByIdResponse response){
        Sensor sensor = response.sensor;
        System.out.println(sensor.getUuid());
        return this;
    }
    private Behavior<MonitorQuery> onSetMonitorNameQuery(SetMonitorNameQuery query){
        this.name = query.name;
        return this;
    }

    private Behavior<MonitorQuery> onErrorResponse(ErrorResponse response){
        System.out.println(response.errorMsg);
        return this;
    }

    private Behavior<MonitorQuery> onAddNewSensorMonitorQuery(AddNewSensorMonitorQuery query){
        UUID uuid = UUID.randomUUID();

        query.dispatcherActorRef.tell(new AddNewSensorQuery(name + "-" + uuid.toString(),
                query.sensorOwnerName,
                query.sensorOwnerSurname,
                query.sensorAddressStreet,
                query.sensorAddressPostalCodeAndCity,
                query.sensorAddressCountry
        ));
        return this;
    }
    private Behavior<MonitorQuery> onDeleteSensorMonitorQuery(DeleteSensorMonitorQuery query){
        query.dispatcherActorRef.tell(new DeleteSensorQuery(query.uuid, getContext().getSelf()));
        return this;
    }
    private Behavior<MonitorQuery> onEditSensorMonitorQuery(EditSensorMonitorQuery query){
        query.dispatcherActorRef.tell(new EditSensorQuery(query.uuid,
                query.newSensorOwnerName,
                query.newSensorOwnerSurname,
                query.newSensorAddressStreet,
                query.newSensorAddressPostalCodeAndCity,
                query.newSensorAddressCountry,
                getContext().getSelf()
        ));
        return this;
    }
    private Behavior<MonitorQuery> onGetAllSensorsMonitorQuery(GetAllSensorsMonitorQuery query){
        query.dispatcherActorRef.tell(new GetAllSensorsQuery(getContext().getSelf()));
        return this;
    }
    private Behavior<MonitorQuery> onGetSensorByIdMonitorQuery(GetSensorByIdMonitorQuery query){
        query.dispatcherActorRef.tell(new GetSensorByIdQuery(query.uuid, getContext().getSelf()));
        return this;
    }
}
