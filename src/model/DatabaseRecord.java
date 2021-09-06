package model;

public abstract class DatabaseRecord {
    private final Integer _id;

    public int GetId() {
        return _id != null ? _id.intValue() : -1;
    }

    protected DatabaseRecord(Integer id) {
        this._id = id;
    }
}
