import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import json_parsing.JSON;
import utils.DispatcherQuery;
import utils.GetSensorByIdResponse;
import utils.monitor_queries.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Behavior<Void> create() {
        return Behaviors.setup(
                context -> {

                    String fileSource = "";

                    try{
                        JsonNode node = JSON.parse(fileSource);

                        //System.out.println(node.get("queryType").asText());
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



                    // --- AD HOC SIMPLE TESTS --- //
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

                    Scanner scanner = new Scanner(System.in);

                    Thread.sleep(2000);
                    System.out.println("EDITION TESTING PART");

                    System.out.print("sensor ID: ");
                    String sensorID;
                    String newName;
                    String newSurname;
                    String newStreet;
                    String newPCAndCity;
                    String newCountry;
                    sensorID = scanner.next();

                    System.out.print("sensor new owner name: ");
                    newName = scanner.next();
                    System.out.print("sensor new owner surname: ");
                    newSurname = scanner.next();
                    System.out.print("sensor new street: ");
                    newStreet = scanner.next();
                    System.out.print("sensor new postal code and city: ");
                    newPCAndCity = scanner.next();
                    System.out.print("sensor new country: ");
                    newCountry = scanner.next();

                    monitor2.tell(new EditSensorMonitorQuery(sensorID,
                            newName,
                            newSurname,
                            newStreet,
                            newPCAndCity,
                            newCountry,
                            dispatcher
                    ));
                    monitor1.tell(new GetAllSensorsMonitorQuery(dispatcher));

                    Thread.sleep(2000);
                    System.out.println("DELETING TESTING PART");

                    System.out.print("sensor ID: ");
                    sensorID = scanner.next();
                    monitor2.tell(new DeleteSensorMonitorQuery(sensorID, dispatcher));
                    Thread.sleep(2000);
                    monitor1.tell(new GetAllSensorsMonitorQuery(dispatcher));

                    Thread.sleep(2000);
                    System.out.println("SINGLE SENSOR TESTING PART");
                    System.out.print("sensor ID: ");
                    sensorID = scanner.next();
                    monitor2.tell(new GetSensorByIdMonitorQuery(sensorID, dispatcher));

                    return Behaviors.receive(Void.class).build();
                });
    }

    public static void main(String[] args) {
        File configFile = new File("dispatcher.conf");
        Config config = ConfigFactory.parseFile(configFile);
        ActorSystem.create(Main.create(), "config", config);
    }
}
