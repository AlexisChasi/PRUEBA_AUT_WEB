package com.nttdata.stepsdefinitions;

import com.nttdata.steps.QAlabStep;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class QAlabStepDef {

    private QAlabStep qAlabStep;
    private WebDriver driver;
    private Scenario scenario;

    @Before(order = 0)
    public void setUp(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Before(order = 1)
    public void setScenario(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void quitDriver(){
        driver.quit();
    }

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        qAlabStep = new QAlabStep(driver);
        qAlabStep.navegarTienda("https://qalab.bensg.com/store");
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String correo, String contr) throws InterruptedException {
        qAlabStep.login(correo, contr);
        screenShot();
    }

    @When("navego a la categoría {string} y subcategoría {string}")
    public void navegoALaCategoríaYSubcategoría(String categoria, String subcategoria) throws InterruptedException {
        qAlabStep.categoria(categoria, subcategoria);
        screenShot();
    }

    @And("agrego dos unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito() throws InterruptedException {
        qAlabStep.agregarProducto();
        screenShot();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        qAlabStep.validarPopUp();
        screenShot();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        qAlabStep.validarMonto();
        screenShot();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        qAlabStep.finalizarCompra();
        screenShot();
    }

    @Then("valido el título de la página del carrito")
    public void validoElTítuloDeLaPáginaDelCarrito() {
        qAlabStep.validarTituloCarrito();
        screenShot();
    }

    @And("vuelvo a validar el cálculo de precios en el carrito")
    public void vuelvoAValidarElCálculoDePreciosEnElCarrito() {
        qAlabStep.ValidarPrecio();
        screenShot();
    }

    public void screenShot() {
        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        this.scenario.attach(evidencia, "image/png", "evidencias");
    }
}
