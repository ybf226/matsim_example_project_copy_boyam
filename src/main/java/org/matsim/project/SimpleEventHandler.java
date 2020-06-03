package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SimpleEventHandler implements PersonDepartureEventHandler,PersonArrivalEventHandler,
        LinkEnterEventHandler{
Map<Id<Person>, Double> departureTimeByPersonMap = new HashMap<>();

// Vecteur de 24 éléments(pour chaque heure) qui stocke le nombre de perssonnes qui traversent le lien
   static int [] agentsperhour = new int[24];



    @Override
    public void handleEvent(PersonDepartureEvent event) {
      System.out.println("Departure event; time: " + event.getTime() + "--linkId: " + event.getLinkId()
      + "personId: "+ event.getPersonId());
      departureTimeByPersonMap.put(event.getPersonId(), event.getTime());
    }

    @Override
    public void handleEvent(PersonArrivalEvent event) {
        System.out.println("Arrival event; time: " + event.getTime() + "--linkId: " + event.getLinkId()
                + "personId: "+ event.getPersonId());

        System.out.println("Travel time: " + (event.getTime() - departureTimeByPersonMap.get(event.getPersonId())));

        }

    @Override
    public void handleEvent(LinkEnterEvent event) {
        Id linkId = event.getLinkId();
        Id linkId6 = Id.createLinkId("6");
        if (linkId.equals(linkId6)) {
            double time = event.getTime();
            double aux = time/3600;
            int hour = (int) Math.floor(aux);
            agentsperhour[hour]++;
        }

    }

    public void printArray()
    {
        boolean first = true;
        System.out.print("[");
        for (int element: agentsperhour){
            if (first){
                System.out.print(element);
                first= false;
            }
            System.out.print(", ");
            System.out.print(element);

        }

        System.out.println(",]");

    }

}
