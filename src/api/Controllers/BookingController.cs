using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using RentAPI.Database;
using RentAPI.Models;

namespace RentAPI.Controllers
{
    [ApiController]
    [Route("api/v1/[controller]")]
    public class BookingController
    {
        private readonly ILogger<CarController> _logger;
        private readonly IDbContextFactory<DatabaseContext> _dbContextFactory;

        public BookingController(ILogger<CarController> logger, IDbContextFactory<DatabaseContext> dbContextFactory)
        {
            _logger = logger;
            _dbContextFactory = dbContextFactory;
        }

        [HttpGet]
        [Route("get_bookings/{customerId:int}")]
        public async Task<ActionResult<IEnumerable<Booking>>> GetBookings(int customerId)
        {
            await using var context = _dbContextFactory.CreateDbContext();

            return await context.Booking.Where(x => x.CustomerId == customerId).ToListAsync();
        }
    }
}