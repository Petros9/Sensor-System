package utils;

import utils.monitor_queries.MonitorQuery;

public class ErrorResponse implements MonitorQuery {
    public String errorMsg;
    public ErrorResponse(String errorMsg){
        this.errorMsg = errorMsg;
    }
}
