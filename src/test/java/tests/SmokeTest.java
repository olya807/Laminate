package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class SmokeTest {

    @Test
    public void smokeTest2() throws InterruptedException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("drivers//chromedriver.exe").getFile());
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", absolutePath);

        // 1
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("https://calc.by/building-calculators/laminate.html");

        /*
        layingMethodLaminate.selectByIndex(0);
        layingMethodLaminate.selectByValue("1");
        layingMethodLaminate.selectByVisibleText("с использованием отрезанного элемента");
        */

        //2
        WebElement layingMethodLaminateElement = chromeDriver.findElement(By.tagName("select"));
        Select layingMethodLaminate = new Select(layingMethodLaminateElement);
        layingMethodLaminate.selectByValue("2");

        //3 Ввести ‘Длина комнаты’ = 500
        WebElement lengthInput = chromeDriver.findElement(By.id("ln_room_id"));
        lengthInput.clear();
        lengthInput.sendKeys("500");

        //4 Ввести ‘Ширина комнаты’ = 400
        WebElement widthInput = chromeDriver.findElement(By.id("wd_room_id"));
        widthInput.clear();
        widthInput.sendKeys("400");

        //5 Ввести ‘Длина панели ламината’ = 2000
        WebElement lengthLaminateInput = chromeDriver.findElement(By.id("ln_lam_id"));
        lengthLaminateInput.clear();
        lengthLaminateInput.sendKeys("2000");

        //6 Ввести ‘Ширина панели ламината’ = 200
        WebElement widthLaminateInput = chromeDriver.findElement(By.id("wd_lam_id"));
        widthLaminateInput.clear();
        widthLaminateInput.sendKeys("200");

        //7 Выбрать ’Направление укладки’ = по ширине комнаты
        WebElement layinglaminateByWidth = chromeDriver.findElement(By.id("direction-laminate-id1"));
        layinglaminateByWidth.click();

        Thread.sleep(2000);

        //8 Нажать на кнопку ‘Рассчитать’
        WebElement calculateButton = chromeDriver.findElement(By.xpath("//a[text()='Рассчитать']"));
        calculateButton.click();

        Thread.sleep(2000);

        //9 Проверить результаты: ‘Требуемое количество досок ламината: 53’
        WebElement actualImpResultElement = chromeDriver.findElement(By.xpath("//div[contains(text(),'Требуемое количество досок ламината')]/span"));
        String actualImpResult = actualImpResultElement.getText();
        Assert.assertEquals(actualImpResult, "53");

        Thread.sleep(2000);

        //10 Проверить результаты: ‘Количество упаковок ламината:7’
        WebElement actualResultElement = chromeDriver.findElement(By.xpath("//div[contains(text(),'Количество упаковок ламината')]/span"));
        String actualImpResultMessage = actualResultElement.getText();
        Assert.assertEquals(actualImpResultMessage, "7");

        //11 Закрыть окно браузера
        chromeDriver.quit();
    }
}
