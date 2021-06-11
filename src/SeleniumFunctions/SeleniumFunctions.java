package src.SeleniumFunctions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SeleniumFunctions {
    static ThreadLocalRandom random = ThreadLocalRandom.current();

    // In these function you need to replace webDriver by your driver name


    // Return the URL of current webPage
    public static String currentUrl() {
        return webDriver.getCurrentUrl();
    }


    // Return the Title of current webPage
    public static String currentTitle() {
        return webDriver.getTitle();
    }

    // Open URL in new tab
    public static void openUrlNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open()");  // this will open the tab
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles()); // to handle the window
        webDriver.switchTo().window(tabs.get(1)); //switch the focus to new tab index 1
        webDriver.get(url); // get the URL
    }


    // Close the current tab
    public static void closeCurrentTab() {
        webDriver.close();
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0)); //switches to new tab
    }

    // Referesh the current page
    public static void refreshCurrentWebPage(WebDriver driver) {
        driver.navigate().refresh();
    }


    // Generate the random String
    public static String generateRandomString(int count) {  // Pass the count, How many character you want in that string
        final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwzyz";
        StringBuilder stringBuilder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_STRING.length());
            stringBuilder.append(ALPHA_STRING.charAt(character));
        }
        return stringBuilder.toString();
    }

    // Generate the Random Number String
    public static String generateRandomNumber(int count) {  // Pass the count, How many Number you want in that string
        final String numbers = "123456789";
        StringBuilder build_number = new StringBuilder();
        while (count-- != 0) {
            int number = (int) (Math.random() * numbers.length());
            build_number.append(numbers.charAt(number));
        }
        return build_number.toString();
    }



    /*************************Handle_Parent_And_Chile_Window_Functions********************************/

    public static void moveToChildWindow() {

        for (String SubWindow : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(SubWindow);
        }
    }

    public static void moveToParentWindow(webDriver driver) {
        driver.close();
        for (String Window : driver.getWindowHandles()) {
            driver.switchTo().window(Window);
        }
    }


    /************************ Is Displayed Functionality **********************************************/

    public static boolean isDisplayed(By path) {
        return webDriver.findElement(path).isDisplayed();
    }

    public static boolean isDisplayed(String path) {
        return webDriver.findElement(By.xpath(path)).isDisplayed();
    }

    /****************************** Delete File ******************************************/

    public static boolean deleteFileFromSystem(String downloadPath) {
        File file = new File(downloadPath);
        if (file.delete()) {
            testinfo.log(Status.INFO,"file deleted successfully");
            return true;
        } else {
            testinfo.log(Status.INFO,"failed to delete file");
            return false;
        }
    }

    /****************************** Extract FileName ******************************************/

    public static String extractFileName(String FileName) {
        String AbsName;
        AbsName = FileName.substring(FileName.lastIndexOf("/") + 1);
        return AbsName;
    }


    /*************************** String checking ******************************************/
    public static Boolean isValid(String string) {
        if (string==null) {
            System.out.println("String is null");
            return false;
        } else if (string.isEmpty()) {
            System.out.println("String is empty");
            return false;
        } return true;
    }


    // For selecting random value from String[]
    public static String random(String[] string) {
        if (string!= null && string.length>0) {
            return string[random.nextInt(0,string.length)];
        } else {
            throw new NullPointerException();
        }
    }

    //  for selecting Random Element By[] path
    public static By random(By[] value) {
        if(value!=null && value.length>1) {
            return value[random.nextInt(0,value.length)];
        } else {
            throw new NullPointerException();
        }
    }

    /*************************** Click ******************************************/

    public static void click(By path) {
        if(null != path){
            webDriver.findElement(path).click();
        } else {
            throw new NullPointerException();
        }
    }

    public static void click(String string) {
        if(null != string) {
            webDriver.findElement(By.xpath(string)).click();
        } else {
            throw new NullPointerException();
        }
    }

    //Scroll and click on that element
    public static void autoScrollAndClick(By path) throws InterruptedException {
        if(path!=null) {
            WebElement element = webDriver.findElement(path);
            Point classname = element.getLocation();
            int xCoordinate = classname.getX();
            int yCoordinate = classname.getY();
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollBy(" + xCoordinate + "," + yCoordinate + ")");
            Thread.sleep(500);
            click(path);

        } else {
            throw new NullPointerException();
        }
    }

    //Scroll into view
    public static void scrollIntoViewAndClick(By path) {
        JavascriptExecutor javascriptExecutor =(JavascriptExecutor) webDriver;
        WebElement element = webDriver.findElement(path);
        javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0)", element);
        webDriver.findElement(path).click();
    }

    /*************************** Coordinate ******************************************/

    public int[] getCoordinates(By path) {
        //Locate element for which you wants to retrieve x y coordinates.
        WebElement element = webDriver.findElement(path);
        //Used points class to get x and y coordinates of element.
        Point classname = element.getLocation();
        int xCoordinate = classname.getX();
        System.out.println("Element's Position from left side"+xCoordinate +" pixels.");
        int yCoordinate = classname.getY();
        System.out.println("Element's Position from top"+yCoordinate +" pixels.");
        int[] Coordinate = new int[2];
        Coordinate[0] = xCoordinate;
        Coordinate[1] = yCoordinate;
        return Coordinate;
    }

    public static void autoScrollTillElement(By path) throws InterruptedException {
        if (path != null) {
            WebElement element = webDriver.findElement(path);
            Point classname = element.getLocation();
            int xCoordinate = classname.getX();
            int yCoordinate = classname.getY();
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollBy(" + xCoordinate + "," + yCoordinate + ")");
            Thread.sleep(500);

        } else {
            throw new NullPointerException();
        }
    }

    public static void waitForVisibilityOfElement(By path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOfElementLocated(path));
    }

    public static void waitForVisibilityOfElement(String path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
    }

    public static void waitForElementToBeClickable(By path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(path));
    }

    public static void waitForElementToBeClickable(String path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
    }

    public static void waitForInVisibilityOfElement(By path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(path));
    }

    public static void waitForInVisibilityOfElement(String path, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(path)));
    }


    public static String getNamedCookie(String cookieName) {
        Cookie cookie = webDriver.manage().getCookieNamed(cookieName);
        System.out.println(cookie.getValue());
        return String.valueOf(cookie);
    }

    public static void scrollToBottom() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }


}
