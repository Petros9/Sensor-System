package utils;

import akka.actor.typed.ActorRef;
import utils.monitor_queries.MonitorQuery;

public class GetSensorByIdQuery implements DispatcherQuery {
    public final String uuid;
    public final ActorRef<MonitorQuery> monitorActorRef;

    public GetSensorByIdQuery(String uuid, ActorRef<MonitorQuery> monitorActorRef){
        this.monitorActorRef = monitorActorRef;
        this.uuid = uuid;
    }
}
