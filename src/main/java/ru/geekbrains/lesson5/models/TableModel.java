package ru.geekbrains.lesson5.models;

import ru.geekbrains.lesson5.presenters.Model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TableModel implements Model {

    private Collection<Table> tables;

    /**
     * Получение списка всех столиков
     * 
     * @return
     */
    public Collection<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }
        return tables;
    }

    /**
     * Бронирование столика
     * 
     * @param reservationDate Дата бронирования
     * @param tableNo         Номер столика
     * @param name            Имя клиента
     * @return Номер брони
     */
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                Reservation reservation = new Reservation(reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        return -1;
    }

    /**
     * Удаление существующего бронирования
     * 
     * @param reservId Номер бронирования
     * @param tableNo  Номер столика
     * 
     */
    public boolean deleteReservationTable(int reservId, int tableNo) {
        // Optional<Table> table = tables.stream().filter(t -> t.getNo() == tableNo).findFirst();
        // if(table.isPresent()){
        // Optional<Reservation> reservation = table.get().getReservations().stream().filter(r ->r.getId() ==
        // reservId).findFirst();
        // if(reservation.isPresent()){
        // table.get().getReservations().remove(reservation.get());}}

        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                Reservation r = null;
                for (Reservation reservation : table.getReservations()) {
                    if (reservation.getId() == reservId) {
                        r = reservation;
                        break;
                    }
                }
                if (r != null) {
                    table.getReservations().remove(r);
                    return true;
                }
            }
        }
        return false;
    }
}
