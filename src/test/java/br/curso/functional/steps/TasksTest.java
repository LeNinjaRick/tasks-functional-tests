package br.curso.functional.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	
	public WebDriver acessarAplicacao() {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		return driver;
	}
	
	
		
		
	
	
	
	@Test
	public void salvarTaskComSucesso() {
			WebDriver driver = acessarAplicacao();
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		
		
		//vendo se a url esta correta:
		String CurrentURL = driver.getCurrentUrl();
		Assert.assertEquals("http://localhost:8001/tasks/add", CurrentURL);
		
		
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
	public void erroPorDataPassada() {
		WebDriver driver = acessarAplicacao();
		
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		
		
		//vendo se a url esta correta:
		String CurrentURL = driver.getCurrentUrl();
		Assert.assertEquals("http://localhost:8001/tasks/add", CurrentURL);
		
		
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
	public void erroPorDataVazia() {
		WebDriver driver = acessarAplicacao();
		
		
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		
		
		//vendo se a url esta correta:
		String CurrentURL = driver.getCurrentUrl();
		Assert.assertEquals("http://localhost:8001/tasks/add", CurrentURL);
		
		
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
	public void erroPorTaskDescriptionVazia() {
		WebDriver driver = acessarAplicacao();
		
		
		try {
		//navegando até a area de inserção a partir do botão "ADD todo"
		driver.findElement(By.id("addTodo")).click();
		
		
		//vendo se a url esta correta:
		String CurrentURL = driver.getCurrentUrl();
		Assert.assertEquals("http://localhost:8001/tasks/add", CurrentURL);
		
		
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
