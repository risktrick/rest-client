package com.example.restclient.main_screen;

import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class MainActivityTest {

    IMainActivity iMainActivity = Mockito.mock(IMainActivity.class);


    /*
    public void testOpenAndSave()
    {
        IView view = createMock(IView.class);	//создаем имитацию для представления
        IDataBase dataBase = CreateMock(IDataBase.class);//создаем имитацию для БД

        expect(view.getId()).andReturn(1); //Когда presenter спросит что прочитать из базы вернем id 1
        expect(dataBase.LoadFromDB(1)).andReturn("source name"); //Когда presenter попытается загрузить название, вернем "source name"
        expect(view.setName("source name")); //ожидаем что правильная строка попадает на форму

        expect(view.getName()).andReturn("destination name"); //Когда presenter  при сохранении спросит, что ввел пользователь, вернем "destination name".
        expect(dataBase.SaveToDB(1, "destination name")); //Ожидаем, что в БД уйдет именно это название.

        Presenter presenter = new Presenter(view, dataBase); //Передаем в presenter имитации для представления и базы данных
        presenter.Save();//Имитируем нажатие кнопки "Сохранить";

        //проверим, что все нужные методы были вызваны с нужными аргументами
        verify(view);
        verify(dataBase);
    }
    */
}