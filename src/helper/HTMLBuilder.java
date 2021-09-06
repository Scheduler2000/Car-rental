package helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.repository.CarRepository;
import model.car.CarBuilder;

public class HTMLBuilder {

  public static String RenderMarketPlace(JSONArray cars) {
    var builder = new StringBuilder();

    for (int i = 0; i < cars.size(); i++) {
      var car = (JSONObject) cars.get(i);

      builder.append("<div class=\"card border-primary mb-3\">\n").append("<div class=\"bg-image hover-overlay\">\n")
          .append("<img height=300px src=\"data:image;base64," + car.get("thumbnail") + "\"/>\n")
          .append("<div class=\"card-header mask overlay\">\n")
          .append("<h4 class=\"title\">" + car.get("brand") + "-" + car.get("model") + "</h4>\n")
          .append("<h4 class=\"price\">" + car.get("rentalPrice") + "$</h4>").append("</div>\n").append("</div>")
          .append("<div class=\"card-body\">").append("<p>" + car.get("description") + "</p>\n")
          .append("<button class=\"btn btn-outline-primary btn-rounded\" data-mdb-ripple-color=\"dark\">Rent ID : "
              + car.get("id") + "</button>\n")
          .append("</div>\n").append("</div>\n");
    }

    return """
        <!DOCTYPE html>
        <html lang=\"en\">
        """ + get_head_html() + """
        <body>
          <div id=\"app\">
          <em><h1>MARKET PLACE</h1></em>
            <section class=\"main\">
              <div class=\"cards\"> """ + builder.toString() + """
                </div>
              </section>
            </div>
          </body>
        </html>

        """;
  }

  public static String RenderReservations(JSONArray reservations) throws Exception {

    var builder = new StringBuilder();
    var carRepository = ServiceLocator.GetContainer().GetInstance(CarRepository.class);
    for (int i = 0; i < reservations.size(); i++) {
      var booking = (JSONObject) reservations.get(i);
      var carId = (long) booking.get("carId");
      var formatter = new SimpleDateFormat("yyyy-mm-dd");
      Date pickUpDate = formatter.parse((String) booking.get("pickUpDate"));
      Date returnDate = formatter.parse((String) booking.get("returnDate"));
      int days = Math.abs((int) ((returnDate.getTime() - pickUpDate.getTime()) / (1000 * 60 * 60 * 24)));
      var car = carRepository.GetEntity(new CarBuilder().SetId((int) carId).Build());

      System.out.println(booking.toString() + " - " + car.toString());
      builder.append(("""
          <div class=\"card mt-5\">
          <div class=\"card-header\">
              <div class=\"row\">
                  <div class=\"col-md-4 mt-3 mt-md-0\">
                  <img height=300px src=\"data:image;base64,""" + car.GetThumbnail() + """
          \"/>
              <p class=\"text-uppercase text-muted my-0\">booking &nbsp;""" + days + " Day(s)" + """
          </p>
          <p class=\"text-uppercase text-muted my-0\">""" + car.GetBrand() + "-" + car.GetModel() + """
               </p>
          </div>

          <div class=\"col-md-4 mt-3 mt-md-0\">
              <p class=\"text-uppercase text-muted my-0\">price per hour</p>
              <p class=\"text-uppercase text-muted my-0\">""" + car.GetRentalPrice() + """
              <i class=\"fas fa-dollar-sign\"></i> <br/>
          </p>
          <p class=\"text-uppercase text-muted my-0\">total &nbsp;""" + formatter.format(pickUpDate) + "&nbsp; - &nbsp;"
          + formatter.format(returnDate) + """
                      </p>
              <p class=\"text-uppercase text-muted my-0\">""" + car.GetRentalPrice() * 24 * days + """
                              <i class=\"fas fa-dollar-sign\"></i>
                          </p>
                      </div>

                  </div>
              </div>
                   """));
    }

    return """
        <!DOCTYPE html>
            <html lang=\"en\">
            """ + get_head_html() + """
        <body>
          <div class=\"container mt-5 mb-5\">
          <nav aria-label=\"breadcrumb\">
              <ol class=\"breadcrumb bg-transparent ml-n3\">
                  <li class=\"breadcrumb-item\">Your Reservations</a></li>
                  <li class=\"breadcrumb-item active text-danger\" >Your Bookings</li>
              </ol>
          </nav>
        """ + builder.toString() + """

        </body>
        </html>


        """;
  }

  private static String get_head_html() {
    return """
        <head>
                <meta charset=\"UTF-8\" />
                <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />
                <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />
                <link
                  href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.3.0/mdb.min.css\"
                  rel=\"stylesheet\"
                />
                <link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css\" rel=\"stylesheet\" />
                <style>
                  .main {
                    margin: 20px;
                    margin-top: 80px;
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    align-items: center;
                  }

                  .cards {
                    display: flex;
                    flex-wrap: wrap;
                    justify-content: center;
                    margin-bottom: 10px;
                  }
                  .cards .card {
                    margin-right: 20px;
                    cursor: pointer;
                  }
                  .cards .card img {
                    width: 350px;
                  }
                  .cards .card .card-header {
                    display: flex;
                    flex-wrap: wrap;
                    justify-content: space-between;
                  }
                  .cards .card .card-body p {
                    margin-top: -10px;
                  }

                  .overlay {
                    background: linear-gradient(
                      45deg,
                      rgba(29, 236, 197, 0.5),
                      rgba(91, 14, 214, 0.5) 100%
                    );
                  }
                </style>
              </head>
        """;
  }
}
