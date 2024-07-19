package gestionempleados.test;


import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormacionViewTest {

    @Test
    public void guardarTest() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080/empleados-formacion");

            new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(presenceOfElementLocated(By.id("nombreEmpleadoField")));

            WebElement nombreEmpleado = driver.findElement(By.id("nombreEmpleadoField"));
            WebElement educacion = driver.findElement(By.id("educacionField"));
            WebElement institucion = driver.findElement(By.id("institucionField"));
            WebElement carreraCertificaciones = driver.findElement(By.id("carreraCertificacionesField"));
            WebElement puntuacion = driver.findElement(By.id("puntuacionField"));

            nombreEmpleado.click();
            nombreEmpleado.sendKeys("Jose Benkay Zelaya Ham");

            educacion.click();
            educacion.sendKeys("Maestria");

            institucion.click();
            institucion.sendKeys("UTH");

            carreraCertificaciones.click();
            carreraCertificaciones.sendKeys("Ingenieria en Computacion");

            puntuacion.click();
            puntuacion.sendKeys("95");

            WebElement saveButton = driver.findElement(By.id("saveButton"));
            saveButton.click();

            WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
            WebElement notification = wait.until(presenceOfElementLocated(By.cssSelector("vaadin-notification-card")));

            assertNotNull(notification);
            assertEquals("Exito,se realizo correctamente", notification.getText());

            System.out.println("Texto notificacion: " + notification.getText());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(driver.getPageSource());
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
