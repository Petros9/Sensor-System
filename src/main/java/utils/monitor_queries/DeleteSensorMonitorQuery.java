package utils.monitor_queries;

import akka.actor.typed.ActorRef;
import utils.DispatcherQuery;

public class DeleteSensorMonitorQuery implements MonitorQuery {
    public final String uuid;
    public final ActorRef<DispatcherQuery> dispatcherActorRef;
    public DeleteSensorMonitorQuery(String uuid, ActorRef<DispatcherQuery> dispatcherActorRef){
        this.uuid = uuid;
        this.dispatcherActorRef = dispatcherActorRef;
    }
}
