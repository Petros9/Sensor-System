package utils;

import akka.actor.typed.ActorRef;
import utils.monitor_queries.MonitorQuery;

public class DeleteSensorQuery implements DispatcherQuery {
    public final String uuid;
    public final ActorRef<MonitorQuery> monitorActorRef;
    public DeleteSensorQuery(String uuid, ActorRef<MonitorQuery> monitorActorRef){
        this.uuid = uuid;
        this.monitorActorRef = monitorActorRef;
    }
}
