package utils;

import utils.monitor_queries.MonitorQuery;

public class GetSensorByIdResponse implements MonitorQuery {
    public final Sensor sensor;

    public GetSensorByIdResponse(Sensor sensor) {
        this.sensor = sensor;
    }
}
