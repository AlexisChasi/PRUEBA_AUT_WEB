package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyStorePage {

    public static By botonLogin = By.xpath("//span[text()='Iniciar sesión']");
    public static By userInput = By.id("field-email");
    public static By passInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");
    public static By authLogin = By.xpath("//span[@class='hidden-sm-down' and contains(text(), 'Alexis Chasi')]");

    public static By categoria = By.xpath("//li[@id='category-3']//a[@class='dropdown-item']");
    public static By subCategoria = By.xpath("//a[@title='Men' and contains(@class, 'img')]");

    public static By primerProducto = By.xpath("//img[@src='https://qalab.bensg.com/store/1-home_default/hummingbird-printed-t-shirt.jpg']");
    public static By aumentarProducto= By.xpath("//button[contains(@class, 'btn-touchspin') and contains(@class, 'bootstrap-touchspin-up')]");
    public static By botonComprar =By.xpath("//button[contains(@class, 'btn-primary')]");
    public static By botonFinCompra = By.xpath("//a[contains(@class, 'btn btn-primary') and contains(text(), 'Finalizar compra')]");
    public static By tituloCarrito = By.xpath("//h1[@class='h1' and text()='Carrito']");


    public static By validarPrecio = By.xpath("//span[@class='product-price']");
}
