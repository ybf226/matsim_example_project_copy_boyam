package org.matsim.project;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

public class Script_normal_Matsim_avant_de_commencer {
    public static void main(String[] args) {
        //Config config = ConfigUtils.createConfig();
//        le config file créé est vide. Pour charger un config file existant, faire la ligne suivante
        Config config = ConfigUtils.loadConfig( "scenarios/equil/config.xml" );
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        Scenario scenario = ScenarioUtils.loadScenario(config); // On ajoute count, network, plan
        Controler controler = new Controler(scenario);
        controler.run();
    }
}
