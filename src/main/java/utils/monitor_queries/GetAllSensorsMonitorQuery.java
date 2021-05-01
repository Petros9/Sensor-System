package utils.monitor_queries;

import akka.actor.typed.ActorRef;
import utils.DispatcherQuery;

public class GetAllSensorsMonitorQuery implements MonitorQuery {
    public final ActorRef<DispatcherQuery> dispatcherActorRef;
    public GetAllSensorsMonitorQuery(ActorRef<DispatcherQuery> dispatcherActorRef) {
        this.dispatcherActorRef = dispatcherActorRef;
    }
}
