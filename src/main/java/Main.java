import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import json_parsing.JSON;
import utils.DispatcherQuery;
import utils.monitor_queries.AddNewSensorMonitorQuery;
import utils.monitor_queries.GetAllSensorsMonitorQuery;
import utils.monitor_queries.MonitorQuery;
import utils.monitor_queries.SetMonitorNameQuery;

import java.io.File;
import java.io.IOException;

public class Main {
    public static Behavior<Void> create() {
        return Behaviors.setup(
                context -> {

                    String fileSource = "";
                    
                    try{
                        JsonNode node = JSON.parse(fileSource);

                        System.out.println(node.get("queryType").asText());
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                    ActorRef<DispatcherQuery> dispatcher =  context.spawn(Dispatcher.create(), "dispatcher");
                    ActorRef<MonitorQuery> monitor1 = context.spawn(Monitor.create(), "Monitor1");
                    ActorRef<MonitorQuery> monitor2 = context.spawn(Monitor.create(), "Monitor2");
                    ActorRef<MonitorQuery> monitor3 = context.spawn(Monitor.create(), "Monitor3");

                    monitor1.tell(new SetMonitorNameQuery("monitor1"));
                    monitor2.tell(new SetMonitorNameQuery("monitor2"));
                    monitor3.tell(new SetMonitorNameQuery("monitor3"));



                    monitor1.tell(new AddNewSensorMonitorQuery(
                            "Adam",
                            "Kowalski",
                            "ul. Garncarska 32",
                            "12-123, Warszawa",
                            "Polska",
                            dispatcher
                    ));
                    monitor2.tell(new AddNewSensorMonitorQuery(
                            "Marian",
                            "Chudy",
                            "ul. Mitu 12",
                            "12-123, Szczecin",
                            "Polska",
                            dispatcher
                    ));
                    monitor2.tell(new AddNewSensorMonitorQuery(
                            "Michael",
                            "Gratz",
                            "Grunstrasse 12",
                            "12-123, Berlin",
                            "Niemcy",
                            dispatcher
                    ));
                    monitor1.tell(new GetAllSensorsMonitorQuery(dispatcher));

                   return Behaviors.receive(Void.class).build();
                });
    }

    public static void main(String[] args) {
        File configFile = new File("dispatcher.conf");
        Config config = ConfigFactory.parseFile(configFile);
        ActorSystem.create(Main.create(), "config", config);
    }
}
