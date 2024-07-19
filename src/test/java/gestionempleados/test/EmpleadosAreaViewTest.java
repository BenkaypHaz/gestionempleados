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

public class EmpleadosAreaViewTest {

    @Test
    public void guardarTest() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080/empleados-area");

            new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(titleIs("Empleados por Area"));

            WebElement grid = driver.findElement(By.id("empleadosGrid"));
            assertNotNull(grid);

            WebElement empleadoCard = grid.findElement(By.cssSelector(".card"));
            assertNotNull(empleadoCard);

            WebElement name = empleadoCard.findElement(By.cssSelector(".name"));
            assertNotNull(name);
            assertEquals("Nombre del Empleado", name.getText());

            WebElement post = empleadoCard.findElement(By.cssSelector(".post"));
            assertNotNull(post);
            assertEquals("Empleado en el área de Nombre del Area", post.getText());

            WebElement likeIcon = empleadoCard.findElement(By.cssSelector(".icon"));
            assertNotNull(likeIcon);

            WebElement likes = empleadoCard.findElement(By.cssSelector(".likes"));
            assertNotNull(likes);
            assertEquals("0", likes.getText());

            WebElement commentIcon = empleadoCard.findElement(By.cssSelector(".icon"));
            assertNotNull(commentIcon);

            WebElement comments = empleadoCard.findElement(By.cssSelector(".comments"));
            assertNotNull(comments);
            assertEquals("0", comments.getText());

            WebElement shareIcon = empleadoCard.findElement(By.cssSelector(".icon"));
            assertNotNull(shareIcon);

            WebElement shares = empleadoCard.findElement(By.cssSelector(".shares"));
            assertNotNull(shares);
            assertEquals("0", shares.getText());

            likeIcon.click();


            assertEquals("1", likes.getText());

        } catch (Exception e) {
            e.printStackTrace();
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
