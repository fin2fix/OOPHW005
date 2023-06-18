package ru.geekbrains.lesson5.presenters;

import ru.geekbrains.lesson5.models.Table;

import java.util.Collection;
import java.util.Date;

public interface Model {

    /**
     * Получение списка всех столиков
     * 
     * @return
     */
    Collection<Table> loadTables();

    /**
     * Бронирование столика
     * 
     * @param reservationDate Дата бронирования
     * @param tableNo         Номер столика
     * @param name            Имя клиента
     * @return Номер брони
     */
    int reservationTable(Date reservationDate, int tableNo, String name);

    /**
     * Удаление бронирования столика
     * 
     * @param reservId номер брони
     * @param tableNo  номер столика
     */
    boolean deleteReservationTable(int reservId, int tableNo);
}
