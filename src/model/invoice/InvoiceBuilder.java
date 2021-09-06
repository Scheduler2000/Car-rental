package model.invoice;

public class InvoiceBuilder {
    private Integer _id;
    private Integer _accountId;
    private Integer _bookingId;

    public InvoiceBuilder SetId(Integer id) {
        this._id = id;
        return this;
    }

    public InvoiceBuilder SetAccountId(Integer accountId) {
        this._accountId = accountId;
        return this;
    }

    public InvoiceBuilder SetBookingId(Integer bookingId) {
        this._bookingId = bookingId;
        return this;
    }

    public Invoice Build() {
        return new Invoice(_id, _accountId, _bookingId);
    }
}
