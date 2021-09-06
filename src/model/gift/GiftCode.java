package model.gift;

import java.util.Date;

import model.DatabaseRecord;

public class GiftCode extends DatabaseRecord {
    private final String _brand;
    private final String _model;
    private final Float _percentage;
    private final Date _endDate;

    public String GetBrand() {
        return _brand;
    }

    public String GetModel() {
        return _model;
    }

    public Float GetPercentage() {
        return _percentage;
    }

    public Date GetEndDate() {
        return _endDate;
    }

    public GiftCode(Integer id, String brand, String model, Float percentage, Date endDate) {
        super(id);
        this._brand = brand;
        this._model = model;
        this._percentage = percentage;
        this._endDate = endDate;
    }

    @Override
    public String toString() {
        return "GiftCode : {\n\tid=" + GetId() + "," + "\n\tbrand=" + _brand + "," + "\n\tmodel=" + _model + ","
                + "\n\tpercentage=" + _percentage + "\n\tend_date=" + _endDate.toString() + "\n}";
    }

}
