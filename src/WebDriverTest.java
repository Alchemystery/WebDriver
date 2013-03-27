import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;

public class WebDriverTest extends TestCase {

    WebDriver driver = Driver.getInstance();

    String wordList = "feline, leonine, vulpine, canine, ranine, elephantine, serpentine, cervine, ovine, porcine, aquinine, asinine, myrmecine, myrmecophagine, bubaline, antilopine, tolypeutine, equine, musteline, percesocine, ursine, oscine, bisontine, icterine, turdine, bovine, taurine, pyrrhuline, emberizine, pieridine, buteonine, vituline, pyrrhuloxine, galline, viverrine, elapine, cancrine, alectorine, corvine, cuculine, didine, delphine, myoxine, columbine, draconine, anatine, lumbricine, acciptrine, fringilline, piscine, pulicene, phoenicopterine, pteropine, gazelline, cricetine, hylobatine, giraffine, hircine, caprine, anserine, larine, leporine, falconine, hippopotamine, suilline, vespine, hominine, trochiline, hyenine, garruline, macropodine, halcyonine, milvine, hirudine, microtine, lemurine, pardine, patelline, homarine, pediculine, psittacine, manatine, hirundine, acarine, mimine, talpine, aedine, murine, octopine, didelphine, orygine, lutrine, ostracine, pantherine, perdicine, pavonine, phasianine, pullastrine, ornithorhynchine, charadrine, hystricine, phocaenine, pythonine, coturnine, lapine, procyonine, ralline, arietine, crotaline, rangiferine, ceratorhine, glirine, capreoline, zibeline, salamandrine, hippocampine, otarine, phocine, soricine, bombycine, alaudine, limacine, atherine, anguine, colubrine, viperine, passerine, sciurine, ciconine, acipenserine, cygnine, cypseline, termitine, tigrine, testudine, meleagrine, cephalopterine, vulturine, lupine, scolopacine, troglodytine, tetragonopterinine, zebrine, hippotigrine, meline, strigine, alcidine, anopheline, aspine, avine, caballine, cameline, cathartine, crocodiline, culicine, cyprine, dacelonine, daysurine, formicine, fulciline, fuliguline, herpestine, ibidine, lacertine, lyncine, macropine, megapterine, mephitine, moschine, nestorine, noctilionine, ovibovine, picine, rhinocerine, rucervine, rupicaprine, sabelline, sturnine, tapirine, tetraonine, volucrine, vaccine, tringine";
    String[] arList = wordList.split(",");

    public void testNAMEOFTEST() throws Exception {

        for (String word : arList) {
            driver.get("http://www.wordnik.com/words/" + word.trim());
            cout("<h2>" + word.trim() + "<h2>");
            cout(Driver
                    .getStringFromJQuery("$('div.definitions')[1].innerHTML;"));
            // if(Driver.isElementPresent("//div[@data-name='etymologies']")) {
            // cout("Etymology <br><br>");
            // cout(
            // Driver.getStringFromJQuery("$(\"[data-name='etymologies']\").text()")
            // );
            // }
            cout("<hr/>");
        }
        driver.quit();
    }

    public void cout(String txt) {
        System.out.println(txt);
    }

}
