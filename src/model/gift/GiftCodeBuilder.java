package model.gift;

import java.util.Date;

public class GiftCodeBuilder {
    private Integer _id;
    private String _brand;
    private String _model;
    private Float _percentage;
    private Date _endDate;

    public GiftCodeBuilder SetId(Integer id) {
        this._id = id;
        return this;
    }

    public GiftCodeBuilder SetBrand(String _brand) {
        this._brand = _brand;
        return this;
    }

    public GiftCodeBuilder SetModel(String _model) {
        this._model = _model;
        return this;
    }

    public GiftCodeBuilder SetPercentage(Float _percentage) {
        this._percentage = _percentage;
        return this;
    }

    public GiftCodeBuilder SetEndDate(Date _endDate) {
        this._endDate = _endDate;
        return this;
    }

    public GiftCode Build() {
        return new GiftCode(_id, _brand, _model, _percentage, _endDate);
    }
}
