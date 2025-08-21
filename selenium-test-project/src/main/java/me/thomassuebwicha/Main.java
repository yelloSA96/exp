package me.thomassuebwicha;

import me.thomassuebwicha.frontend.GUI;
import me.thomassuebwicha.service.BrowserService;
import me.thomassuebwicha.service.RealEstate;

public class Main {
    public static void main(String[] args) {
//        Coles specialsWebCrawl = new Coles();
//        specialsWebCrawl.execute();

        BrowserService browserService = new BrowserService();
        RealEstate realEstate = new RealEstate();
        realEstate.setDriver(browserService.getDriver());
        GUI gui = new GUI(realEstate);
        gui.init();
        gui.show();
    }

}