package model.invoice;

import model.DatabaseRecord;

public class Invoice extends DatabaseRecord {
    private final Integer _accountId;
    private final Integer _bookingId;

    public Integer GetAccountId() {
        return _accountId;
    }

    public Integer GetBookingId() {
        return _bookingId;
    }

    public Invoice(Integer id, Integer accountId, Integer bookingId) {
        super(id);
        this._accountId = accountId;
        this._bookingId = bookingId;
    }

}
