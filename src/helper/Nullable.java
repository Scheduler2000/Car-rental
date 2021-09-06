package helper;

public class Nullable<TTYpe> {
    private TTYpe _value;
    private final boolean _hasValue;

    public Nullable() {
        this._hasValue = false;
    }

    public Nullable(TTYpe value) {
        this._value = value;
        this._hasValue = true;
    }

    public boolean HasValue() {
        return _hasValue;
    }

    public TTYpe Value() {
        return _hasValue ? _value : null;
    }
}
