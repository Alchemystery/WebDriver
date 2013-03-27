import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;


public class FreeDictionaryDefinitions  extends TestCase{

    
    WebDriver driver = Driver.getInstance();    
    
    String wordList = "hurlyburly, nave, direful, whence, sooth, dwindle, tempest-tost, withal, selfsame, wrack, unfix, recompense, hither, hie, compunctious, gall, dun, hautboy, martlet, mansionry, buttress, courier, afeared, prithee, sticking-place, wassail, limbeck, swinish, dudgeon, alarum, ravishing, knell, posset, brainsickly, gild, multitudinous, incarnadine, anon, carouse, requited, feverous, remembrance, sacrilegious, counterfeit, parley, temperate, breeched, auger, consort, benison, indissoluble, twain, exeunt, unlineal, rancours, liege, cur, shough, demi-wolves, avouch, sundry, vizard, jocund, charnel-house, maw, kite, weal, avaunt, blanched, delinquent, rue, brinded, hedge-pig, adder, blind-worm, hemlock, gibbet, resolute, lion-mettled, treble, pernicious, firstling, judicious, sirrah, prattler, homely, nigh, abide, laudable, dolour, avaricious, cistern, continent, impediment, hoodwink, staunchless, foison, fortitude, interdiction, blaspheme, scruple, credulous, abjure, forsworn, malady, ulcerous, betimes, niggard, fraught, dam, demerit, physic, fie, sweeten, gentry, unrough, valiant, distempered, upbraid, dwarfish, sere, cyme, bane, arbitrate, night-shriek, wherefore, slaughterous, aweary, bear-like, bruited, intrenchant, palter, underwrit, rabble, yonder, vestal, doff, henceforth, impute, variable, promontory, firmament, apprehension, pestilent, paragon, gratis, garb, coagulate, carbuncle, strumpet, bawdy, pate, malefaction, augure";
    String[] arList = wordList.split(",");
    
    public void testNAMEOFTEST() throws Exception {         
        
        for(String word : arList) {
            driver.get("http://www.thefreedictionary.com/" + word.trim());
            cout("<h2>" + word + "<h2>");
            cout("<p>");
            Driver.sleep(1000);
            cout( Driver.getStringFromJQuery("document.querySelectorAll('div#MainTxt table')[0].innerText") );
            cout("</p>");
            cout("<hr/>");
        }      
        driver.quit();
    }

    public void cout(String txt) {
        System.out.println(txt);
    }
  
    
    
}
