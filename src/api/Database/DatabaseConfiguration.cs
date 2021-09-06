namespace RentAPI.Database
{
    public class DatabaseConfiguration
    {
        public string Address { get; set; }

        public string Port { get; set; }

        public string Username { get; set; }

        public string Password { get; set; }

        public string DatabaseName { get; set; }

        public string SSLMode { get; set; }

        public DatabaseConfiguration(string address, string port, string username, string password, string databaseName, string enableSsl)
        {
            Address = address;
            Port = port;
            Username = username;
            Password = password;
            DatabaseName = databaseName;
            SSLMode = enableSsl;
        }

        public override string ToString()
        {
            return $"server={Address};port={Port};user={Username};password={Password};database={DatabaseName};SslMode={SSLMode}";
        }
    }
}