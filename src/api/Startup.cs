using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.OpenApi.Models;
using RentAPI.Database;

namespace RentAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        public void ConfigureServices(IServiceCollection services)
        {
            var databaseConf = new DatabaseConfiguration(
                Configuration["Database:Address"],
                Configuration["Database:Port"],
                Configuration["Database:Username"],
                Configuration["Database:Password"],
                Configuration["Database:DatabaseName"],
                Configuration["Database:EnableSSL"]
            );
            services.AddDbContextFactory<DatabaseContext>(
                options => options.UseMySQL(databaseConf.ToString()));
            
            services.AddControllers();
            services.AddSwaggerGen(c => { c.SwaggerDoc("v1", new OpenApiInfo {Title = "RentAPI", Version = "v1"}); });
            
            services.AddCors(o => o.AddPolicy("CorsPolicy", builder =>
            {
                builder.AllowAnyOrigin()
                    .AllowAnyMethod()
                    .AllowAnyHeader();
            }));
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseSwagger();
                app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "RentAPI v1"));
            }

            app.UseCors("CorsPolicy");

           // app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            
            app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
        }
    }
}