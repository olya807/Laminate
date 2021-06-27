package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class SelectorsTest {

    @Test
    public void CssSelectorTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("drivers" + File.separatorChar + "chromedriver").getFile());
        String absolutePath = file.getAbsolutePath();

        System.setProperty("webdriver.chrome.driver", absolutePath);

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://calc.by/building-calculators/laminate.html");

        WebElement byId = driver.findElement(By.cssSelector("#calc_message")); // Поиск по ID
        WebElement byClass = driver.findElement(By.cssSelector(".calc-footer")); // Поиск по одному классу
        WebElement byClasses = driver.findElement(By.cssSelector(".wrap.t3-mainnav")); // Поиск по нескольким классам в одном элементе
        WebElement byTag = driver.findElement(By.cssSelector("nav")); // Поиск по тэгу

        List<WebElement> byAttribute = driver.findElements(By.cssSelector("[itemprop]"));
        System.out.println(byAttribute.size());

        WebElement byAttributeWithValue = driver.findElement(By.cssSelector("[itemprop='articleBody']")); // Поиск по тэгу c значением
        WebElement byTagClassAttribute = driver.findElement(By.cssSelector("div.calc-header[style]")); // Поиск по тэгу + класс + атрибут

        WebElement childElementByClass = driver.findElement(By.cssSelector(".calc-container .order-line")); // Поиск дочернего элемента по классу

        // Поиск дочернего элемента по сложным параметрам
        WebElement childElementByAttributeAndTag = driver.findElement(By.cssSelector("[for='name_input'] span"));

        // Поиск дочернего элемента у которого непосредственным родителем является .calc-content
        WebElement childRightAfterParent = driver.findElement(By.cssSelector(".calc-content > .calc-btn-div"));

        // Поиск элемента который идет сразу за первым элементом на одном уровне
        WebElement tweenElement = driver.findElement(By.cssSelector("label + input.form-control"));

        // Поиск соседнего элемента который находится с первым элементом на одном уровне
        WebElement neighborElement = driver.findElement(By.cssSelector("label ~ input"));

        // Поиск всех элементов с указанными селекторами
        WebElement combineElements = driver.findElement(By.cssSelector("label, input"));

        // Поиск всех элементов у который аттрибут содержить указанное значение
        WebElement elementWithPartialValue = driver.findElement(By.cssSelector("[for *= 'input']"));

        // Поиск всех элементов у который аттрибут начинается с указанного значения
        WebElement elementStartsFromValue = driver.findElement(By.cssSelector("[for ^= 'email']"));

        // Поиск всех элементов у который аттрибут заканчивается указанным значением
        WebElement elementEndsValue = driver.findElement(By.cssSelector("[for $= 'put']"));

        // Поиск всех элементов которые являются первыми дочерними элементами
        WebElement firstChildElement = driver.findElement(By.cssSelector("form:first-child"));

        // Поиск всех элементов которые являются последними дочерними элементами
        WebElement lastChildElement = driver.findElement(By.cssSelector("li:last-child"));

        // Поиск всех элементов которые являются n-ыми дочерними элементами
        WebElement nthChildElement = driver.findElement(By.cssSelector("li:nth-child(13)"));

        driver.quit();
    }

    @Test
    public void XPathSelectorTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("drivers" + File.separatorChar + "chromedriver").getFile());
        String absolutePath = file.getAbsolutePath();

        System.setProperty("webdriver.chrome.driver", absolutePath);

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://calc.by/building-calculators/laminate.html");

        // Поиск элемента по тэгу
        WebElement byTag = driver.findElement(By.xpath("//input"));

        // Поиск элемента по наличию аттрибута
        WebElement byAttribute = driver.findElement(By.xpath("//*[@itemprop]"));

        // Поиск элемента по наличию аттрибута с значением
        WebElement byAttributeWithValue = driver.findElement(By.xpath("//*[@itemprop = 'inLanguage']"));

        // Поиск элемента по тэгу и классу (полное значение)
        WebElement byTagAndClassWithFullValue = driver.findElement(By.xpath("//div[@class='order-line clearfix']"));

        // Поиск элемента по тэгу и частичному значению аттрибута
        WebElement byTagAndPartialValue = driver.findElement(By.xpath("//div[contains(@class, 'order-line')]"));

        // Поиск элемента по тексту
        WebElement byText1 = driver.findElement(By.xpath("//*[text() = 'Длина комнаты']"));
        WebElement byText2 = driver.findElement(By.xpath("//*[. = 'Длина комнаты']"));

        // Поиск элемента по подстроке
        WebElement byPartialText = driver.findElement(By.xpath("//*[contains(text(), 'Калькулятор для расчета ламината')]"));

        // Поиск элемента по двум аттрибутам
        WebElement byTwoAttributes1 = driver.findElement(By.xpath("//*[@class='calc-input' and @value='320']"));
        WebElement byTwoAttributes2 = driver.findElement(By.xpath("//*[@class='calc-input' or @value='320']"));
        WebElement byTwoAttributes3 = driver.findElement(By.xpath("//*[@class='calc-input' and @value != '320']"));

        // !==== Оси (Axes) ===
        // Получение родителя ближайшего элемента (поднимаемся на 1 уровень)
        WebElement getParent = driver.findElement(By.xpath("//*[. = 'Ширина комнаты']/.."));

        // Получение родителя элемента по тэгу (поднимаемся на N уровней)
        WebElement getParentBy = driver.findElement(By.xpath("//*[. = 'Ширина комнаты']/ancestor::section"));

        // Получение дочернего элемента по классу (опускаемся на N уровней)
        WebElement getChildBy1 = driver.findElement(By.xpath("//*[@class = 'calc-container']/descendant::*[@class = 'submit-div']"));
        WebElement getChildBy2 = driver.findElement(By.xpath("//*[@class = 'calc-container']//div[@class = 'submit-div']"));

        // Получение первого дочернего элемента сразу после родительского (опускаемся на 1 уровень)
        WebElement getFirstChildRightAfterParent = driver.findElement(By.xpath("//*[@class = 'calc-container']/div"));

        // Получение последнего дочернего элемента сразу после родительского (опускаемся на 1 уровень)
        WebElement getLastChildRightAfterParent = driver.findElement(By.xpath("//*[@class = 'calc-container']/div[last()]"));

        // Получение n-го дочернего элемента сразу после родительского (опускаемся на 1 уровень)
        WebElement getNthChildRightAfterParent = driver.findElement(By.xpath("//*[@class = 'calc-container']/div[3]"));

        // Получение всех элементов с тэгом div от начала до элемента с классом calc-container
        WebElement getALlElementsBeforeCurrent = driver.findElement(By.xpath("//*[@class = 'calc-container']/preceding::div"));

        // Получение всех элементов с тэгом div от текущего элемента с классом calc-container до конца
        WebElement getALlElementsAfterCurrent = driver.findElement(By.xpath("//*[@class = 'calc-container']/following::div"));


        driver.quit();
    }
}