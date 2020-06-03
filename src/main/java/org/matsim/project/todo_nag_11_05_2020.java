package org.matsim.project;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

public class todo_nag_11_05_2020 {
    public static void main(String[] args) {
    //    Config config = ConfigUtils.createConfig();
//        le config file créé est vide. Pour charger un config
//        file existant, faire la ligne suivante
         Config config = ConfigUtils.loadConfig( "scenarios/equil/config.xml" );
          config.controler().setLastIteration(10);
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        //config.plans().setInputFile("../../output/output_plans.xml.gz");exemple d'ajout d'un plan
         //config.network().setInputFile("../../output/output_network.xml.gz");exemple d'ajout d'un reseau
        //config.qsim().setFlowCapFactor(0.8);// reduire la capacite de vitesse du reseau
        //config.qsim().setStorageCapFactor(0.8); //reduire la capacite de retention du reseau
         Scenario scenario = ScenarioUtils.loadScenario(config); // On ajoute count, network, plan
        Controler controler = new Controler(scenario);
        controler.run();

    }
}

