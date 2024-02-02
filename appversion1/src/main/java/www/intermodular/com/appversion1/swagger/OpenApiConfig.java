package edu.alumno.ismael.apr_rest_mysql_futbol.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "ismael",
             email = "ismaelxtv@gmail.com",
              url = "https://ieslluissimarro.org/DWES/"
              ),
               description = "OpenApi documentation fos String Security",
                title = "OpenApi specification - ismael", version = "1.0",
                 license = @License(
                    name = "License name",
                     url = "https://some-url.com"),
                      termsOfService = "https://ieslluisimarro.org/DWES/TermOfService"),
                       servers = {
                                @Server(
                                    description = "Local ENV",
                                     url = "http://localhost:8090"
                                     ),
                                @Server(
                                    description = "PROD ENV",
                                     url = "https://ieslluisimarro.org/DWES/Production_API/")
                    }, 
                    security = {
                                @SecurityRequirement(
                                            name = "bearerAuth"
                                            )

                    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)

public class OpenApiConfig {

}