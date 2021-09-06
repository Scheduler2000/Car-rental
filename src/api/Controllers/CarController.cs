using System.Collections.Generic;
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
    public class CarController
    {
        private readonly ILogger<CarController> _logger;
        private readonly IDbContextFactory<DatabaseContext> _dbContextFactory;

        public CarController(ILogger<CarController> logger, IDbContextFactory<DatabaseContext> dbContextFactory)
        {
            _logger = logger;
            _dbContextFactory = dbContextFactory;
        }

        [HttpGet]
        [Route("get_all_cars")]
        public async Task<ActionResult<IEnumerable<Car>>> GetCars()
        {
            await using var context = _dbContextFactory.CreateDbContext();
            return await context.Car.ToListAsync();
        }

        [HttpGet]
        [Route("get_car/{id:int}")]
        public async Task<ActionResult<Car>> GetCar(int id)
        {
            await using var context = _dbContextFactory.CreateDbContext();

            return await context.Car.FirstAsync(x => x.Id == id);
        }
    }
}