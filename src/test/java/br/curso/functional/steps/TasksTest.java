package br.curso.functional.steps;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		
		DesiredCapabilities cap = DesiredCapabilities.chrome(); 
		
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.99.1:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		return driver;
	}
	
	
		
		
	
	
	
	@Test
	public void salvarTaskComSucesso() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		
	
		
		
		//preenchendo os campos:
		driver.findElement(By.id("task")).sendKeys("Teste funcional task");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//salvando a task:
		driver.findElement(By.id("saveButton")).click();
		
		
		//vendo se foi bem sucedido:
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals("Success!", mensagem);
		}finally {
		//fechar browser
		driver.close();
		}
		
		}
	
	
	
	@Test
	public void erroPorDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		
		
		
		
		
		//preenchendo os campos:
		driver.findElement(By.id("task")).sendKeys("Teste funcional task erro1");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		
		//salvando a task:
		driver.findElement(By.id("saveButton")).click();
		
		
		//vendo se foi bem sucedido:
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals("Due date must not be in past", mensagem);
		}finally{
		//fechar browser
		driver.close();
		}
		
		
		}
	
	
	@Test
	public void erroPorDataVazia() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		

		
		
		//preenchendo os campos:
		driver.findElement(By.id("task")).sendKeys("Teste funcional task erro2");
		driver.findElement(By.id("dueDate")).sendKeys("");
		
		//salvando a task:
		driver.findElement(By.id("saveButton")).click();
		
		
		//vendo se foi bem sucedido:
		String mensagem = driver.findElement(By.cssSelector("p[class=\"alert alert-danger\"]")).getText();
		
		Assert.assertEquals("Fill the due date", mensagem);
		
		}finally{
		//fechar browser
		driver.close();
		}
	}
	
	
	@Test
	public void erroPorTaskDescriptionVazia() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		
		
		
		
		//preenchendo os campos:
		driver.findElement(By.id("task")).sendKeys("");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2040");
		
		//salvando a task:
		driver.findElement(By.id("saveButton")).click();
		
		
		//vendo se foi bem sucedido:
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals("Fill the task description", mensagem);
		}finally{
		//fechar browser
		driver.close();
		}
		
		
	}
	
	
	
	
	
}
