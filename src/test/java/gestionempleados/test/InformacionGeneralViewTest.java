package gestionempleados.test;



import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InformacionGeneralViewTest {

    @Test
    public void guardarEmpleadoTest() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080/");

            new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                    .until(titleIs("Informacion General"));

            WebElement nombreCompleto = driver.findElement(By.id("nombreCompletoField"));
            WebElement identidad = driver.findElement(By.id("identidadField"));
            WebElement edad = driver.findElement(By.id("edadField"));
            WebElement sexo = driver.findElement(By.id("sexoField"));
            WebElement areaNombre = driver.findElement(By.id("areaNombreField"));
            WebElement cargoNombre = driver.findElement(By.id("cargoNombreField"));
            WebElement fechaIngreso = driver.findElement(By.id("fechaIngresoField"));

            nombreCompleto.sendKeys("Juan Perez");
            identidad.sendKeys("0801199912345");
            edad.sendKeys("30");
            sexo.sendKeys("Masculino");
            areaNombre.sendKeys("IT");
            cargoNombre.sendKeys("Developer");

            fechaIngreso.click();
            WebElement todayButton = driver.findElement(By.xpath("//vaadin-date-picker-overlay-content//vaadin-button[contains(., 'Today')]"));
            todayButton.click();

            WebElement saveButton = driver.findElement(By.id("saveButton"));
            saveButton.click();

            WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
            WebElement notification = wait.until(d -> {
                try {
                    WebElement elem = d.findElement(By.cssSelector("vaadin-notification-card"));
                    if (elem.isDisplayed()) {
                        return elem;
                    } else {
                        return null;
                    }
                } catch (Exception e) {
                    return null;
                }
            });

            assertNotNull(notification);
            assertEquals("Exito,se realizo correctamente", notification.getText());
        } finally {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
