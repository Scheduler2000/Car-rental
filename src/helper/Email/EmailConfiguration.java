package helper.Email;

public class EmailConfiguration {
    private final String _senderEmail;
    private final String _senderPassword;
    private final String _smtpEmailHost;

    public String getSenderEmail() {
        return _senderEmail;
    }

    public String GetSenderPassword() {
        return _senderPassword;
    }

    public String GetSMTPEmailHost() {
        return _smtpEmailHost;
    }

    public EmailConfiguration(String _senderEmail, String _senderPassword, String _smtpEmailHost) {
        this._senderEmail = _senderEmail;
        this._senderPassword = _senderPassword;
        this._smtpEmailHost = _smtpEmailHost;
    }

}
