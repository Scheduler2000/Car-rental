package model.car;

import model.DatabaseRecord;

public class Car extends DatabaseRecord {
    private final String _brand;
    private final String _model;
    private final Float _rentalPrice;
    private final String _description;
    private final String _thumbnail;

    public String GetBrand() {
        return _brand;
    }

    public String GetModel() {
        return _model;
    }

    public Float GetRentalPrice() {
        return _rentalPrice;
    }

    public String GetDescription() {
        return _description;
    }

    public String GetThumbnail() {
        return _thumbnail;
    }

    public Car(Integer id, String brand, String model, Float rentalPrice, String description, String thumbnail) {
        super(id);
        this._brand = brand;
        this._model = model;
        this._rentalPrice = rentalPrice;
        this._description = description;
        this._thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Car : {\n\tid=" + GetId() + "," + "\n\tbrand=" + _brand + "," + "\n\tmodel=" + _model + ","
                + "\n\trental_price=" + _rentalPrice + "\n\tdescription=" + _description + "\n}";
    }
}
