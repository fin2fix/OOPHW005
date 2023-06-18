package ru.geekbrains.lesson5.presenters;

import ru.geekbrains.lesson5.models.Table;

import java.util.Collection;
import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private final View bookingView;
    private final Model tableModel;

    public BookingPresenter(View bookingView, Model tableModel) {
        this.bookingView = bookingView;
        this.tableModel = tableModel;
        bookingView.setObserver(this);
    }

    private Collection<Table> loadTables() {
        return tableModel.loadTables();
    }

    public void showTables() {
        bookingView.updateTablesView(loadTables());
    }

    private void showReservationTableResult(int reservationNo, int tableNo) {
        bookingView.updateReservationTableResult(reservationNo, tableNo);
    }

    private void showDeletionTableResult(boolean flag, int oldReservation, int tableNo) {
        bookingView.updateDeleteTableResult(flag, oldReservation, tableNo);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        int reservationNo = tableModel.reservationTable(orderDate, tableNo, name);
        showReservationTableResult(reservationNo, tableNo);
    }

    @Override
    public boolean onDeleteReservationTable(int oldReservation, int tableNo) {
       boolean flag = tableModel.deleteReservationTable(oldReservation, tableNo);
       showDeletionTableResult(flag, oldReservation, tableNo); 
       return flag;
    }
}
