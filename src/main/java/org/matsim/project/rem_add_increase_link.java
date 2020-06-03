package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.*;
import org.matsim.contrib.otfvis.OTFVisLiveModule;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

import java.util.ArrayList;
import java.util.List;

public class rem_add_increase_link {

    public static void main(String[] args) {

        Config config;
        config = ConfigUtils.loadConfig( "scenarios/equil/config.xml" );
        // ---

        Scenario scenario = ScenarioUtils.loadScenario(config);

        // possibly modify scenario here

        //remove all agents from scenario except one

        Id<Person> interestingPersonId = Id.createPersonId(1);
        List<Id<Person>> personsToRemove = new ArrayList<>();

        for (Id<Person> personId : scenario.getPopulation().getPersons().keySet()) {
            if (!personId.equals(interestingPersonId)) {
                personsToRemove.add(personId);
            }
        }

        for (Id<Person> personId : personsToRemove) {
            scenario.getPopulation().removePerson(personId);
        }

        System.out.println("Population size =" + scenario.getPopulation().getPersons().size());



        // We add one new agent with a simple plan


        PopulationFactory populationFactory = scenario.getPopulation().getFactory();

        Person person2 = populationFactory.createPerson(Id.createPersonId("Boyam"));

        Plan plan = populationFactory.createPlan(); //creation du plan

       Activity homeActivity = populationFactory.createActivityFromLinkId("h", Id.createLinkId(1));
       //creation activite maison (il quitte la maison)
        homeActivity.setEndTime(7*60*60.);
        plan.addActivity(homeActivity);//on ajoute cela au plan créé plus haut

        Leg leg = populationFactory.createLeg(TransportMode.car); //mode de transport
        plan.addLeg(leg); //on ajoute le mode de transport au plan

        Activity workActivity = populationFactory.createActivityFromLinkId("w", Id.createLinkId(1));
        //creation activite boulot (il quitte la maison)
        homeActivity.setEndTime(10*60*60.);
        plan.addActivity(workActivity);

        Leg leg2 = populationFactory.createLeg(TransportMode.car);
        plan.addLeg(leg2);

        Activity homeActivity2 = populationFactory.createActivityFromLinkId("h", Id.createLinkId(22));
        plan.addActivity(homeActivity2);

        person2.addPlan(plan); //on intègre la personne 2 (Boyam) au plan créé

        scenario.getPopulation().addPerson(person2);

        System.out.println("Population size = " + scenario.getPopulation().getPersons().size());
        // ---

        //Modifier la capacité des liens

        Id<Link> interestingLinkId= Id.createLinkId(1);
        List<Id<Link>> linksToModify = new ArrayList<>();

        for (Id<Link>linkId: scenario.getNetwork().getLinks().keySet()){
            if (!linkId.equals(interestingLinkId)) {
                linksToModify.add(linkId);
            }
        }

        for (Id<Link>linkId: linksToModify) {
            scenario.getNetwork().getLinks().get(linkId).setCapacity(1500);
        }

        //System.out.println("link capacity = " +  scenario.getNetwork().getLinks().;

    }

}


