package com.nttdata.steps;

import com.nttdata.page.MyStorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QAlabStep {
    WebDriver driver;

    public QAlabStep(WebDriver driver) {
        this.driver = driver;
    }

    public void navegarTienda(String url){
        driver.get(url);
    }


    public void login(String correo, String contr) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Aumenté el tiempo de espera a 10 segundos para asegurar que el elemento aparezca

        try {
            // Click en el botón de login
            WebElement botonLogin = wait.until(ExpectedConditions.elementToBeClickable(MyStorePage.botonLogin));
            botonLogin.click();

            // Ingreso del correo electrónico
            WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(MyStorePage.userInput));
            userInput.sendKeys(correo);

            // Ingreso de la contraseña
            WebElement passInput = driver.findElement(MyStorePage.passInput);
            passInput.sendKeys(contr);

            // Click en el botón de login
            WebElement loginButton = driver.findElement(MyStorePage.loginButton);
            loginButton.click();

            // Validación del nombre después del login
            WebElement nombreUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(MyStorePage.authLogin));
            System.out.println("Login exitoso para: " + nombreUsuario.getText());
        } catch (Exception e) {
            // Manejar cualquier excepción aquí
            e.printStackTrace();
            throw new RuntimeException("Error durante el login: " + e.getMessage());
        }
    }



    public void categoria(String cat, String subcat) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Aumenta el tiempo si es necesario

        try {
            WebElement categoria = wait.until(ExpectedConditions.elementToBeClickable(MyStorePage.categoria));
            categoria.click();

            WebElement subcategoria = wait.until(ExpectedConditions.elementToBeClickable(MyStorePage.subCategoria));
            subcategoria.click();
        } catch (TimeoutException e) {
            // Manejar excepciones de tiempo de espera aquí
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar cualquier otra excepción aquí
            e.printStackTrace();
        }

    }

    public void agregarProducto() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ajusta el tiempo según sea necesario

        try {
            WebElement primerProducto = wait.until(ExpectedConditions.elementToBeClickable(MyStorePage.primerProducto));
            primerProducto.click();

            WebElement aumentarProducto = wait.until(ExpectedConditions.elementToBeClickable(MyStorePage.aumentarProducto));
            aumentarProducto.click();

            WebElement botonComprar = wait.until(ExpectedConditions.elementToBeClickable(MyStorePage.botonComprar));
            botonComprar.click();
        } catch (TimeoutException e) {
            // Manejar excepciones de tiempo de espera aquí
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar cualquier otra excepción aquí
            e.printStackTrace();
        }



    }

    public void validarPopUp() {
        // Esperar a que el popup aparezca usando WebDriverWait
        try {
            // Esperar a que aparezca el popup con el mensaje de confirmación
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Aumenté el tiempo de espera a 10 segundos para asegurar que el elemento aparezca
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='modal-title h6 text-sm-center' and contains(text(), 'Producto añadido correctamente a su carrito de compra')]")));

            // Verificar si el mensaje está presente en el popup
            String mensajeEsperado = "Producto añadido correctamente a su carrito de compra";
            if (popup.getText().contains(mensajeEsperado)) {
                System.out.println("El mensaje '" + mensajeEsperado + "' está presente en el popup.");

                // Validar el texto en el carrito después de que aparezca el popup
                try {
                    WebElement cartProductsCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.cart-products-count")));
                    String textoEsperado = "Hay 2 artículos en su carrito.";

                    // Verificar si el texto esperado está presente en el carrito
                    if (cartProductsCount.getText().equals(textoEsperado)) {
                        System.out.println("El texto '" + textoEsperado + "' está presente en el carrito.");
                    } else {
                        System.out.println("Error: El texto en el carrito no coincide con el esperado.");
                    }
                } catch (TimeoutException e) {
                    System.out.println("Error: El texto en el carrito no apareció dentro del tiempo esperado.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error: El mensaje no está presente en el popup.");
            }
        } catch (TimeoutException e) {
            System.out.println("Error: El popup no apareció dentro del tiempo esperado.");
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar cualquier otra excepción
            e.printStackTrace();
            throw new RuntimeException("Error durante la validación del popup: " + e.getMessage());
        }

    }

    public void validarMonto() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Aumenté el tiempo de espera a 10 segundos para asegurar que el elemento aparezca
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='modal-title h6 text-sm-center' and contains(text(), 'Producto añadido correctamente a su carrito de compra')]")));

            // Obtener el elemento del monto
            WebElement montoElemento = popup.findElement(By.xpath("//span[@class='value']"));
            String montoTexto = montoElemento.getText().trim(); // Elimina espacios en blanco al inicio y al final

            // Mismo formato que aparece en la interfaz, eliminando caracteres no deseados

            String montoEsperado = "38.24 PEN".replaceAll("[^\\d.,]", "").replaceAll("\\s+", "");
            // Limpia el monto actual de caracteres no numéricos y espacios adicionales
            String montoActual = montoTexto.replaceAll("[^\\d.,]", "").replaceAll("\\s+", "");

            // Comparar el texto del monto obtenido con el monto esperado
            if (montoActual.equals(montoEsperado)) {
                System.out.println("El monto '" + montoEsperado + "' es correcto en el popup.");
            } else {
                System.out.println("Error: El monto no coincide con el esperado. Esperado: " + montoEsperado + ", Actual: " + montoActual);
            }
        } catch (Exception e) {
            // Manejar cualquier excepción aquí
            e.printStackTrace();
            throw new RuntimeException("Error durante la validación del monto en el popup: " + e.getMessage());
        }
    }

    public void finalizarCompra() {
        WebElement botonFinCompra  = driver.findElement(MyStorePage.botonFinCompra);
        botonFinCompra.click();
    }

    public void validarTituloCarrito() {
        try {
            // Espera a que el elemento h1 con clase 'h1' y texto 'Carrito' esté presente en el DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement tituloCarrito = wait.until(ExpectedConditions.presenceOfElementLocated(MyStorePage.tituloCarrito));

            // Verifica si el elemento está presente
            if (tituloCarrito.isDisplayed()) {
                System.out.println("El título 'Carrito' está presente en la página.");
            } else {
                System.out.println("Error: El título 'Carrito' no está visible en la página.");
            }
        } catch (TimeoutException e) {
            // Maneja la excepción de tiempo de espera
            e.printStackTrace();
        } catch (Exception e) {
            // Maneja otras excepciones
            e.printStackTrace();
        }

    }

    public void ValidarPrecio() {
        try {
            // Espera a que el elemento span con clase 'product-price' y texto '38,24 PEN' esté presente en el DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement precioProducto = wait.until(ExpectedConditions.presenceOfElementLocated(MyStorePage.validarPrecio));

            // Verifica si el elemento está presente
            if (precioProducto.isDisplayed()) {
                System.out.println("El precio '38,24 PEN' está presente en la página.");
            } else {
                System.out.println("Error: El precio '38,24 PEN' no está visible en la página.");
            }
        } catch (TimeoutException e) {
            // Maneja la excepción de tiempo de espera
            e.printStackTrace();
        } catch (Exception e) {
            // Maneja otras excepciones
            e.printStackTrace();
        }

    }
}
