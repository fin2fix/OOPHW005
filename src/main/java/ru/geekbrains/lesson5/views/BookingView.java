package ru.geekbrains.lesson5.views;

import ru.geekbrains.lesson5.models.Table;
import ru.geekbrains.lesson5.presenters.View;
import ru.geekbrains.lesson5.presenters.ViewObserver;

import java.util.Collection;
import java.util.Date;

public class BookingView implements View {

    private ViewObserver observer;

    @Override
    public void setObserver(ViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void updateTablesView(Collection<Table> tables) {
        System.out.println("\nВот такие столики есть в нашем ресторане:   ");
        for (Table table : tables) {
            System.out.println(table);
        }
        System.out.println();
    }

    @Override
    public void updateReservationTableResult(int reservationNo, int tableNo) {
        System.out.printf("Столик #%d успешно забронирован. Номер вашей брони: #%d\n\n", tableNo, reservationNo);
    }

    @Override
    public void updateDeleteTableResult(boolean flag, int oldReservation, int tableNo) {
        if (flag) {
            System.out.printf("Бронь #%d на столике #%d отменена\n\n", oldReservation, tableNo);
        } else {
            System.out.printf(
                    "Удаление брони #%d невозможно - указанная бронь на столе #%d не найдена. Операция отменена.\n\n",
                    oldReservation, tableNo);
        }
    }

    /**
     * Действие клиента (пользователь нажал на кнопку бронирования), бронирование
     * столика
     * 
     * @param reservationDate дата бронирования
     * @param tableNo         номер столика
     * @param name            имя клиета
     */

    public void reservationTableEvent(Date reservationDate, int tableNo, String name) {
        observer.onReservationTable(reservationDate, tableNo, name);
    }

    /**
     * TODO: Доработать метод changeReservationTable в рамках домашнего задания
     * Действие клиента (пользователь нажал на кнопку изменения бронирования),
     * изменение бронирование столика
     * 
     * @param oldReservation
     * @param reservationDate
     * @param tableNo
     * @param name
     */
    public void changeReservationTable(int oldReservation, int oldTableNo, Date newReservationDate, int newTableNo,
            String name) {
        boolean flag = observer.onDeleteReservationTable(oldReservation, oldTableNo);
        if (flag)
            observer.onReservationTable(newReservationDate, newTableNo, name);
    }

}
