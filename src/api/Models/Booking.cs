using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace RentAPI.Models
{
    public class Booking
    {
        [Key] public int Id { get; set; }
        [Column("car_id")]
        public int CarId { get; set; }
        [Column("customer_id")]
        public int CustomerId { get; set; }
        [Column("pick_up_date")]
        public DateTime PickUpDate { get; set; }
        [Column("return_date")]
        public DateTime ReturnDate { get; set; }
    }
}