package com.example.restclient.model;

import android.util.Log;

import com.example.restclient.ILogger;
import com.example.restclient.LoggerConsole;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Test;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class JsonParserUnitTest {

    ModelJsonParser parser;
    ILogger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Log.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                System.out.println(invocation);
                return null;
            }
        });

        parser = new ModelJsonParser();
        logger = LoggerConsole.getInstance();
    }

    @Test
    public void checkLogger() {
        assertNotNull("logger == null", logger);
    }

    @Test
    public void parseBashJson() throws Exception {
        //parse response from final String BASH_URL = "http://www.umori.li/api/get?site=bash.im&name=bash&num=100";
        //model class is BashModel
    }

    @Test
    public void parseOneTestModel() {
        String response = "{name:\"John\", age:\"32\"}";
        TestModel model= parser.parseOneTestModel(response);
        assertEquals("John", model.name);
        assertEquals("32", model.age);

        logger.log(model.toString());
    }

    @Test
    public void parseArrayTestModel() throws Exception {
        String response = "[{name:\"John\", age:\"32\"}, {name:\"Mike\", age:\"45\"}]";
        TestModel[] expected = {new TestModel("John", "32"), new TestModel("Mike", "45")};

        TestModel[] models = parser.parseArrayTestModel(response);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].name, models[i].name);
            assertEquals(expected[i].age, models[i].age);
        }

        for (TestModel model : models) {
            logger.log(model.toString());
        }
    }

    @Test
    public void parseArrayTestModelNewMethod() {
        String response = "[{name:\"John\", age:\"32\"}, {name:\"Mike\", age:\"45\"}]";
        parser.parseArrayTestModelNewMethod(response, logger);
    }

    @Test
    public void parseArrayOfArrayTestModel() {
        String response = "[[{name:\"John\", age:\"32\"}, {name:\"Mike\", age:\"45\"}], [{name:\"Marian\", age:\"18\"}]]";
        parser.parseArrayOfArrayTestModel(response, logger);
    }

    @Test
    public void parseSources() {
        String response = "[[{\"site\":\"bash.im\",\"name\":\"bash\",\"url\":\"http://bash.im\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/quote/\",\"desc\":\"Цитатник Рунета\"},{\"site\":\"bash.im\",\"name\":\"abyss\",\"url\":\"http://bash.im/abyss\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"none\",\"desc\":\"Цитатник Рунета - Бездна\"}],[{\"site\":\"ithappens.me\",\"name\":\"ithappens\",\"url\":\"http://ithappens.me\",\"parsel\":\".text\",\"encoding\":\"UTF-8\",\"linkpar\":\"/story/\",\"desc\":\"IT Happens\"}],[{\"site\":\"zadolba.li\",\"name\":\"zadolbali\",\"url\":\"http://zadolba.li\",\"parsel\":\".text\",\"encoding\":\"UTF-8\",\"linkpar\":\"/story/\",\"desc\":\"Задолба!ли\"}],[{\"site\":\"anekdot.ru\",\"name\":\"new anekdot\",\"url\":\"http://www.anekdot.ru/last/j.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Свежие анекдоты\"},{\"site\":\"anekdot.ru\",\"name\":\"new story\",\"url\":\"http://www.anekdot.ru/last/o.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Новые истории\"},{\"site\":\"anekdot.ru\",\"name\":\"new aforizm\",\"url\":\"http://www.anekdot.ru/last/a.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Новые афоризмы и фразы\"},{\"site\":\"anekdot.ru\",\"name\":\"new stihi\",\"url\":\"http://www.anekdot.ru/last/c.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Стишки\"}],[{\"site\":\"ideer.ru\",\"name\":\"ideer\",\"url\":\"http://ideer.ru\",\"parsel\":\".shortContent\",\"encoding\":\"UTF-8\",\"linkpar\":\"/\",\"desc\":\"Подслушано\"}],[{\"site\":\"det.org.ru\",\"name\":\"Deti\",\"url\":\"http://det.org.ru\",\"parsel\":\".history\",\"encoding\":\"windows-1251\",\"linkpar\":\"/?quote=\",\"desc\":\"Говорят дети\"}],[{\"site\":\"xkcdb.com\",\"name\":\"XKCDB\",\"url\":\"http://www.xkcdb.com\",\"parsel\":\"span.quote\",\"encoding\":\"UTF-8\",\"linkpar\":\"/\",\"desc\":\"XKCDB\"}]]\n";
        parser.parseSources(response, logger);
    }
}