package ru.pel.rrs.models;

/**
 * По задумке жилье в самом общем смысле: номер, комната, квартира, дом, бунгало, палатко-место и т.д.
 */
//TODO изучить порождающие паттерны и подумать над их применением в процессе создания жилищ. Думаю, должен подойти
// паттерн "фабричный метод" или "абстрактная фабрика"
public abstract class Stays {
    protected String type;
    //разумно тут вписать свойства:
    // наличие wi-fi, сплита, ТВ и прочие подобные.
}
