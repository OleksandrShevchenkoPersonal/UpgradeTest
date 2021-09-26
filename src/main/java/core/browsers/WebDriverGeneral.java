package core.browsers;

import org.openqa.selenium.WebDriver;

import lombok.Getter;
import lombok.Setter;


public abstract class WebDriverGeneral {
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

}
