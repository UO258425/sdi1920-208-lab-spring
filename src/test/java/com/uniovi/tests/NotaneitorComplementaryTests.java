package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.utils.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementaryTests {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Carlos Manrique\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		// antes de cada prueba navega a la url home de la app
		driver.navigate().to(URL);

	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
		// antes de la primera prueba
	}

	@AfterClass
	static public void end() {
		// al finalizar la ultima prueba
		// cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR01. Registro profesor con datos validos
	@Test
	public void PR01() {
		PO_LoginView.loginAs(driver,"99999988F","123456","99999988F");
		PO_PrivateView.goTo(driver, "teachers-menu", "/profesor/add");
		
		PO_PrivateView.fillProfessorForm(driver, "12345678A", "nombre", "apellido", "1");
		PO_PrivateView.checkElement(driver, "text", "nombre");
		

	}

	// PR02. Registro profesor con datos invalidos:
	// nombre y categoria
	@Test
	public void PR02() {
		PO_LoginView.loginAs(driver,"99999988F","123456","99999988F");
		PO_PrivateView.goTo(driver, "teachers-menu", "/profesor/add");
		
		PO_PrivateView.fillProfessorForm(driver, "123456789123A", "nombre", "apellido", "1");
		PO_RegisterView.checkKey(driver, "Error.profesor.dni.length", PO_Properties.getSPANISH());
		
		PO_PrivateView.fillProfessorForm(driver, "12345678A", "nombre", "apellido", "1");
		PO_RegisterView.checkKey(driver, "Error.profesor.dni.duplicate", PO_Properties.getSPANISH());
		
		PO_PrivateView.fillProfessorForm(driver, "123", "nombre", "apellido", "1");
		PO_RegisterView.checkKey(driver, "Error.profesor.dni.length", PO_Properties.getSPANISH());
		

	}
	// PR03. Solo admin puede acceder a crear profesores
	@Test
	public void PR03() {

		driver.navigate().to("http://localhost:8090/profesor/add");
		SeleniumUtils.textoNoPresentePagina(driver, "Forbidden");
		driver.navigate().to(URL);

	}

	
	

}