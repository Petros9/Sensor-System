package utils.monitor_queries;

public class SetMonitorNameQuery implements MonitorQuery {
    public String name;

    public SetMonitorNameQuery(String name){
        this.name = name;
    }
}
