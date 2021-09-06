package model.car;

public class CarBuilder {
    private Integer _id;
    private String _brand;
    private String _model;
    private Float _rentalPrice;
    private String _description;
    private String _thumbnail;

    public CarBuilder SetId(Integer id) {
        this._id = id;
        return this;
    }

    public CarBuilder SetBrand(String brand) {
        this._brand = brand;
        return this;
    }

    public CarBuilder SetModel(String model) {
        this._model = model;
        return this;
    }

    public CarBuilder SetRentalPrice(Float rentalPrice) {
        this._rentalPrice = rentalPrice;
        return this;
    }

    public CarBuilder SetThumbnail(String thumbnail) {
        this._thumbnail = thumbnail;
        return this;
    }

    public CarBuilder SetDescription(String description) {
        this._description = description;
        return this;
    }

    public Car Build() {
        return new Car(_id, _brand, _model, _rentalPrice, _description, _thumbnail);
    }
}
