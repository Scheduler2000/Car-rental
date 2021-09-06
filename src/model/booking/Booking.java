package model.booking;

import java.util.Date;

import model.DatabaseRecord;

public class Booking extends DatabaseRecord {
    private final Integer _carId;
    private final Integer _customerId;
    private final Date _pickUpDate;
    private final Date _returnDate;

    public Integer GetCarId() {
        return _carId;
    }

    public Integer GetCustomerId() {
        return _customerId;
    }

    public Date GetPickUpDate() {
        return _pickUpDate;
    }

    public Date GetReturnDate() {
        return _returnDate;
    }

    public Booking(Integer id, Integer carId, Integer customerId, Date pickUpDate, Date returnDate) {
        super(id);
        this._carId = carId;
        this._customerId = customerId;
        this._pickUpDate = pickUpDate;
        this._returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Booking : {\n\tid=" + GetId() + "," + "\n\tcar_id=" + _carId + "," + "\n\tcustomer_id=" + _customerId
                + "," + "\n\tpick_up=" + _pickUpDate.toString() + "\n\treturn=" + _returnDate.toString() + "\n}";
    }

}
