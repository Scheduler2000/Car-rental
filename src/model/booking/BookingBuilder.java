package model.booking;

import java.util.Date;

public class BookingBuilder {
    private Integer _id;
    private Integer _carId;
    private Integer _customerId;
    private Date _pickUpDate;
    private Date _returnDate;

    public BookingBuilder SetId(Integer id) {
        this._id = id;
        return this;
    }

    public BookingBuilder SetCarId(Integer carId) {
        this._carId = carId;
        return this;
    }

    public BookingBuilder SetCustomerId(Integer customerId) {
        this._customerId = customerId;
        return this;
    }

    public BookingBuilder SetPickUpDate(Date pickUpDate) {
        this._pickUpDate = pickUpDate;
        return this;
    }

    public BookingBuilder SetReturnDate(Date returnDate) {
        this._returnDate = returnDate;
        return this;
    }

    public Booking Build() {
        return new Booking(_id, _carId, _customerId, _pickUpDate, _returnDate);
    }
}
