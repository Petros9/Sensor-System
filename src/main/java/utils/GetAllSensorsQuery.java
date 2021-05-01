package utils;

import akka.actor.typed.ActorRef;
import utils.monitor_queries.MonitorQuery;

public class GetAllSensorsQuery implements DispatcherQuery {
    public final ActorRef<MonitorQuery> monitorActorRef;

    public GetAllSensorsQuery(ActorRef<MonitorQuery> monitorActorRef) {
        this.monitorActorRef = monitorActorRef;
    }
}
