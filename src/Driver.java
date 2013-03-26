

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Driver {
    public static WebDriver driver = null;
    private int implicitWaitTimeout = 10;
    
    public Driver() { 
        // Exists only to defeat instantiation.
    }
    
    public static WebDriver getInstance() {
        if(driver == null) {
            cout("Getting instance of WebDriver for local test run.");
        }

            cout("Google Chrome driver is selected");
            if(isWindows()) {
                System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "/lib/chromedriver.exe");
            }
            driver = new ChromeDriver();
            
        Dimension screenResolution = new Dimension(1024, 768);
        driver.manage().window().setSize(screenResolution);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
     }

     private static boolean isWindows() {
         String os = System.getProperty("os.name").toLowerCase();
         //windows
         return (os.indexOf( "win" ) >= 0); 
     }
     
    public static void quit(){
        System.out.println("Shutting down driver");
        driver.close();
        driver.quit();
    }
    
    public String getTitle() {
        return driver.getTitle();
    }
     
   public boolean isEditable(String locator) {
       try {
          return findElement(locator).isEnabled(); 
       }catch(Exception e) {
          cout(e.getMessage());
          return false;
       }       
   }
   
   public boolean isChecked(String checkBoxLocator) {
       try {
           return Boolean.parseBoolean(findElement(checkBoxLocator).getAttribute("checked"));
       }catch(Exception e) {
           cout(e.getMessage());
           return false;
       }  
   }
   
   public boolean isVisible(String locator) {
       if(isElementPresent(locator)) {
           return findElement(locator).isDisplayed();
       }else {
           cout("The element : " + locator + " was missing.");
           return false;
       }  
   }   
     
    public boolean isTextPresent(String pattern) {
        // ANOTHER WAY driver.getPageSource().contains(pattern)
        try {
            WebElement el = driver.findElement(By.xpath("//*[contains(.,'" + pattern + "')]"));
            return true;
          } catch (NoSuchElementException e) {
            return false;
          }
     }

    
    public String getText(String locator) {
        String text = "";
        try {
            text = findElement(locator).getText();
            //logger.logInfo("Text from '" + locator  + "' is " + text);
         }catch(Exception e) {
             cout("Failed to retreive text from " + locator + "<br>" +  e.getMessage());
         }  
         return text;
    }
    
    public String getValue(String locator) {
        implicitWait(locator);
        try {
            String value = findElement(locator).getAttribute("value");
            return value;
         }catch(Exception e) {
             cout("getValue() of " +  locator + " " + e.getMessage());
             return "ERROR";
         }  
    }
    
    public void switchWindow() throws NoSuchWindowException{
        sleep(2000);
       try {
           Set<String> handles = driver.getWindowHandles();
           String current = driver.getWindowHandle();
           handles.remove(current);
           String newTab = handles.iterator().next();
           driver.switchTo().window(newTab);
        }catch(Exception e) {
            cout(e.getMessage());
        }
    }
    
       public String getEval(String javascript) {          
           try {
               JavascriptExecutor js = (JavascriptExecutor) driver;
               return (String) js.executeScript("return " + javascript);
           }catch (Exception e) {
               cout(e.getMessage());
               return "ERROR";
           }
       }
           
      
     public WebElement getElementByValue(String locator, String value) {
         for(WebElement el : findElements(locator)) {
             if(el.getAttribute("value").contains(value)) return el;
         }
         cout("Could not find element " + locator + " with value of " + value);
         return null;
     }
     
       public WebElement getElementByText(String locator, String text) {
             for(WebElement el : findElements(locator)) {
                 if(el.getText().contains(text)) return el;
             }
             cout("Could not find element " + locator + " with text of " + text);
             return null;
         }
       
       public void runScript(String javascript) {
           try {
              JavascriptExecutor js = (JavascriptExecutor) driver;
              js.executeScript(javascript);
           }catch (Exception e) {
               cout("runScript() " + e.getMessage());
           }
       }
       
     
        public void select(String selectLocator, String optionLocator) {
            try {
                WebElement e = findElement(selectLocator);
                Select select = new Select(e);
                if(optionLocator.indexOf("=") > -1) {
                 select.selectByValue(optionLocator.split("=")[1]); // select by option value
                }else {
                    select.selectByVisibleText(optionLocator); // select by the visible option text
                }      
                //select.selectByIndex(specific_index); // select by option index, start from "0"
            }catch(Exception e){
                cout(e.getMessage());
            } 
        }
 
     
    public void clickLink(String linkText) {
        try {
           WebDriverWait wait = new WebDriverWait(driver, 10);
           WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(linkText)));
           link.click();
        } catch (Exception e) {
           cout("Failed to click '" + linkText + "' link. " + e.getMessage());
        }
    }

    public void clickElement(String identifier) {
        implicitWait(identifier);
           try {
               findElement(identifier).click();
           } catch (Exception e) {
               cout("Failed to click '" + identifier + "' " + e.getMessage());
           }
       }
     
    public void clickElementAndWait(String identifier) {
        try {
            implicitWait(identifier);
            findElement(identifier).click();
            sleep(1000);
        } catch (Exception e) {
            cout("Failed to click '" + identifier + "' " + e.getMessage());
        }
    }
    
    public void switchTo(String identifier) {
        sleep(1000);
        try {
            driver.switchTo().frame(findElement(identifier));
        } catch (Exception e) {
            cout("switchTo() " + e.getMessage());
        }
    }
    
    public void switchToDefaultFrame() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
           cout("switchToDefaultFrame() " + e.getMessage());
        }
    }
    
    public void type(String identifier, String text) {
        try {        
            findElement(identifier).clear();
            findElement(identifier).sendKeys(text);
  } catch (Exception e) {
            cout("type() " + e.getMessage());
        }
    }
    
       public void type(WebElement el, String text) {
            try {        
                el.clear();
                el.sendKeys(text);
            } catch (Exception e) {
                cout("type() " + e.getMessage());
            }
        }                                                                   
       
        public static boolean isElementPresent(String identifier) {
            if(findElements(identifier) != null) {
               return  findElements(identifier).size() != 0;
            }else {
                return false;
            }
        }
    
    public WebElement findElement(String identifier) {
        WebElement el = null;
        try {
            if(identifier.indexOf("//") > -1 ) {
                el =  driver.findElement(By.xpath(identifier));
            }else if(identifier.indexOf("link=") > -1 ){
                String linkText = identifier.split("=")[1];
                el = driver.findElement(By.linkText(linkText));
            }else if(identifier.indexOf("$") > -1 ){
                el = getWebElementFromJquery(identifier);
            }else if(identifier.indexOf(">") > -1 || identifier.indexOf(".") > -1) {                
               el = driver.findElement(By.cssSelector(identifier));
            }else {
                el = driver.findElement(By.id(identifier));
            }
        }catch(Exception e) {
             cout("findElement() " + e.getMessage());
        }
        return el;          
    }
    
    public static List<WebElement> findElements(String identifier) {
           List<WebElement> el = null;
            try {
                if(identifier.indexOf("//") > -1 ) {
                    el =  driver.findElements(By.xpath(identifier));                
                }else if(identifier.indexOf("link=") > -1 ){
                    String linkText = identifier.split("=")[1];
                    el = (List<WebElement>) driver.findElements(By.linkText(linkText));
                }else {
                    el =  driver.findElements(By.id(identifier));
                 }     
             }catch(Exception e) {
                 cout("findElements() " + e.getMessage());
             }
             return el;    
        }
       

    protected void implicitWait(String identifier){
         int count = 0;
         int waitTime = 200;
         for(int attempts = 0; attempts < implicitWaitTimeout; attempts++) {
               if(isElementPresent(identifier)) {                      
                  break;
               }else {
                   count++;
                   sleep(waitTime);  
               }
           }
         if(count > 0 ){
             cout("Implicit Wait timed out " + count + " times waiting for " + identifier + " for a total period of " + ((count*waitTime)/1000) + " seconds");
         }
    }
    
 
    public void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            cout("sleep() " + e.getMessage());
        }
    }
    
        
    public WebElement getWebElementFromJquery(String jq) {
        // Returns a WebElement from Jquery selector
        WebElement jqElement = null;
        JavascriptExecutor js = (JavascriptExecutor) driver;
       //String query = "return $(\"" + jq + "\").get(0);";
       String query = "return " + jq + ".get(0);";
        try {
           jqElement = (WebElement) js.executeScript(query);
        }catch(Exception e) {
            cout("jQuery() " + e.getMessage());
        }
        return jqElement;
    }

       public static String getStringFromJQuery(String jq) {  
           //Returns a String from the return value of jQuery
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String query = "return " + jq;
            try {
                return (String) js.executeScript(query);
            }catch(Exception e) {
                cout("jQueryString() " + e.getMessage());
            }
            return null;
        }
    


     
     public void alertAccept() {
         Alert alert = driver.switchTo().alert();
         alert.accept();
         sleep(1000);
     }
     
     public void alertCancel() {
         Alert alert = driver.switchTo().alert();
         alert.dismiss();
         sleep(1000);
     }
     
     public String getAlertText() {
         sleep(1000);
         Alert javascriptAlert = driver.switchTo().alert();
         return  javascriptAlert.getText();
     }
     
    
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
    }
    
    public static void cout(String txt) {
        System.out.println(txt);
    }
   
}
