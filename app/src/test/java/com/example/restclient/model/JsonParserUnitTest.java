package com.example.restclient.model;

import android.util.Log;

import com.example.restclient.ILogger;
import com.example.restclient.LoggerConsole;
import com.example.restclient.model.JsonParser;
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

    @Before
    public void setup() {
        PowerMockito.mockStatic(Log.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                System.out.println(invocation);
                return null;
            }
        });
    }

    @Test
    public void parseOneTestModel() {
        JsonParser parser = new JsonParser();
        String response = "{name:\"John\", age:\"32\"}";
        ILogger logger = new LoggerConsole();
        parser.parseOneTestModel(response, logger);
    }

    @Test
    public void parseArrayTestModelNewMethod() {
        JsonParser parser = new JsonParser();
        String response = "[{name:\"John\", age:\"32\"}, {name:\"Mike\", age:\"45\"}]";
        ILogger logger = new LoggerConsole();
        parser.parseArrayTestModelNewMethod(response, logger);
    }

    @Test
    public void parseArrayOfArrayTestModel() {
        JsonParser parser = new JsonParser();
        String response = "[[{name:\"John\", age:\"32\"}, {name:\"Mike\", age:\"45\"}], [{name:\"Marian\", age:\"18\"}]]";
        ILogger logger = new LoggerConsole();
        parser.parseArrayOfArrayTestModel(response, logger);
    }

    @Test
    public void parseSources() {
        JsonParser parser = new JsonParser();
        String response = "[[{\"site\":\"bash.im\",\"name\":\"bash\",\"url\":\"http://bash.im\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/quote/\",\"desc\":\"Цитатник Рунета\"},{\"site\":\"bash.im\",\"name\":\"abyss\",\"url\":\"http://bash.im/abyss\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"none\",\"desc\":\"Цитатник Рунета - Бездна\"}],[{\"site\":\"ithappens.me\",\"name\":\"ithappens\",\"url\":\"http://ithappens.me\",\"parsel\":\".text\",\"encoding\":\"UTF-8\",\"linkpar\":\"/story/\",\"desc\":\"IT Happens\"}],[{\"site\":\"zadolba.li\",\"name\":\"zadolbali\",\"url\":\"http://zadolba.li\",\"parsel\":\".text\",\"encoding\":\"UTF-8\",\"linkpar\":\"/story/\",\"desc\":\"Задолба!ли\"}],[{\"site\":\"anekdot.ru\",\"name\":\"new anekdot\",\"url\":\"http://www.anekdot.ru/last/j.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Свежие анекдоты\"},{\"site\":\"anekdot.ru\",\"name\":\"new story\",\"url\":\"http://www.anekdot.ru/last/o.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Новые истории\"},{\"site\":\"anekdot.ru\",\"name\":\"new aforizm\",\"url\":\"http://www.anekdot.ru/last/a.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Новые афоризмы и фразы\"},{\"site\":\"anekdot.ru\",\"name\":\"new stihi\",\"url\":\"http://www.anekdot.ru/last/c.html\",\"parsel\":\".text\",\"encoding\":\"windows-1251\",\"linkpar\":\"/id/\",\"desc\":\"Стишки\"}],[{\"site\":\"ideer.ru\",\"name\":\"ideer\",\"url\":\"http://ideer.ru\",\"parsel\":\".shortContent\",\"encoding\":\"UTF-8\",\"linkpar\":\"/\",\"desc\":\"Подслушано\"}],[{\"site\":\"det.org.ru\",\"name\":\"Deti\",\"url\":\"http://det.org.ru\",\"parsel\":\".history\",\"encoding\":\"windows-1251\",\"linkpar\":\"/?quote=\",\"desc\":\"Говорят дети\"}],[{\"site\":\"xkcdb.com\",\"name\":\"XKCDB\",\"url\":\"http://www.xkcdb.com\",\"parsel\":\"span.quote\",\"encoding\":\"UTF-8\",\"linkpar\":\"/\",\"desc\":\"XKCDB\"}]]\n";
        ILogger logger = new LoggerConsole();
        parser.parseSources(response, logger);
    }
}