package utils;

import utils.monitor_queries.MonitorQuery;

import java.util.ArrayList;

public class GetAllSensorsResponse implements MonitorQuery {
    public final ArrayList<Sensor> sensors;

    public GetAllSensorsResponse(ArrayList<Sensor> sensors) {
        this.sensors = sensors;
    }
}
