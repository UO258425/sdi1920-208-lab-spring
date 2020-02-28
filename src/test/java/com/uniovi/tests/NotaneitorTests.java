package com.uniovi.tests;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {
	
	static String PathFirefox65= "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024= "C:\\Users\\Carlos Manrique\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver= getDriver(PathFirefox65, Geckdriver024); 
	static String URL= "http://localhost:8090";
	
	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	@Before
	public void setUp() throws Exception {
		//antes de cada prueba navega a la url home de la app
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		//despues de cada prueba se borran las cookies del navegador
		driver.manage().deleteAllCookies();
	}
	
	@BeforeClass
	static public void begin() {
		//antes de la primera prueba
	}
	
	@AfterClass
	static public void end() {
		//al finalizar la ultima prueba
		//cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
}