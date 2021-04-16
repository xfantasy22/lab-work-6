package commands;

import server.CollectionsKeeper;

/**
 * Команда завершает программу
 */
public class Exit {

    private CollectionsKeeper dc;
    /**
     * Конструктор - создание нового объекта
     *
     * @param dc - обработчик команд
     */
    public Exit(CollectionsKeeper dc) {
        this.dc=dc;
    }

    /**
     * Главный метод класса, запускает команду
     *
     * @return true/false Успешно ли завершилась команда
     */
    public boolean execute() {
            System.out.println("Программа завершена.");
            SpecialSave s = new SpecialSave(dc);
            s.execute();
            System.exit(0);
            return true;
    }
}
