package core.browsers;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import core.Driver.BrowserType;

public class Chrome extends WebDriverGeneral {

    public Chrome() {
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(BrowserType.findByName(BrowserType.CHROME.getName()).getOptions());
        this.setDriver(new ChromeDriver(options));
    }
}
