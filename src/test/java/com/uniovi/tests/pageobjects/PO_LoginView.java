package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {


	static public void fillForm(WebDriver driver, String dnip, String passwordp) {
		WebElement dni = driver.findElement(By.name("username"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		
		//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void loginAs(WebDriver driver, String user, String password, String textThatShouldAppear) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, user, password);
		PO_View.checkElement(driver, "text", textThatShouldAppear);
	}
}
