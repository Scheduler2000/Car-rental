using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace RentAPI.Models
{
    public class Car
    {
        [Key]
        public int Id { get; set; }
        public string Brand { get; set; }
        
        public string Model { get; set; }
        
        [Column("rental_price")]
        public float RentalPrice { get; set; }
        
        public string Description { get; set; }
        
        public string Thumbnail { get; set; }


        public Car(string brand, string model, float rentalPrice, string description, string thumbnail)
        {
            Brand = brand;
            Model = model;
            RentalPrice = rentalPrice;
            Description = description;
            Thumbnail = thumbnail;
        }
        
        
    }
}