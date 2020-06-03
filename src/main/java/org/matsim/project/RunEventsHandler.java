package org.matsim.project;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunEventsHandler {

    //Pour pouvoir exécuter ce java script, saisir les lignes du dessous (lignes 7 et 8)

     public static void main (String[] args){
        String inputFile= "output/output_events.xml.gz"; //Matsim peut décompresser directement le dossier

      EventsManager eventsManager= EventsUtils.createEventsManager();//creation d'un event manager
         SimpleEventHandler eventHandler = new SimpleEventHandler();
         eventsManager.addHandler(eventHandler);
         MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager);
         eventsReader.readFile(inputFile);//reads the event file
                  // calculer les temps de trajets: temps d'arrivée moins temps de départ
         eventHandler.printArray(); // afficher l'array créé dans le simple event

    }
}
