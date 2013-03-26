import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;

public class WebDriverTest extends TestCase{
    
    WebDriver driver = Driver.getInstance();    
    
    String wordList = "recherche, absquatulate, perspicacious, teleology, monistic, theogony, instantiation, inchoate, auriferous, nitre, homiletic, glabrous";
    
    String[] arList = wordList.split(",");
    
    public void testNAMEOFTEST() throws Exception {         
        
        for(String word : arList) {
            driver.get("http://www.wordnik.com/words/" + word.trim());
            cout("<h2>" + word + "<h2>");
            cout( Driver.getStringFromJQuery("$('div.definitions')[1].innerHTML;") );
//            if(Driver.isElementPresent("//div[@data-name='etymologies']")) {
//                cout("Etymology <br><br>");
//                cout( Driver.getStringFromJQuery("$(\"[data-name='etymologies']\").text()") );
//            }
            cout("<hr/>");
        }      
        driver.quit();
    }

    public void cout(String txt) {
        System.out.println(txt);
    }
  
    
}
