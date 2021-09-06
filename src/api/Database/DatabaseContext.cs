using Microsoft.EntityFrameworkCore;
using RentAPI.Models;

namespace RentAPI.Database
{
    public class DatabaseContext : DbContext
    {
        public DbSet<Car> Car { get; set; }
        
        public DbSet<Booking> Booking { get; set; }

        public DatabaseContext(DbContextOptions<DatabaseContext> options) : base(options)
        { }
        
    }
}