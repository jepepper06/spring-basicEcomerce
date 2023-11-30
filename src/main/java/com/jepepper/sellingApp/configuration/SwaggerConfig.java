<<<<<<< Updated upstream
package com.jepepper.sellingApp.configuration;public class SwaggerConfig {
=======
package com.jepepper.sellingApp.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration

// this is all broken broo
public class SwaggerConfig {
  // This is unnecesary I think
//  @Bean
//  public OperationCustomizer customize() {
//    return (operation, handlerMethod) -> operation.addParametersItem(
//            new Parameter()
//                    .in("header")
//                    .required(true)
//                    .description("myCustomHeader")
//                    .name("Authorization"));
//  }
  @Bean
  public OpenAPI customOpenAPI(@Value("${openapi.service.title}") String serviceTitle, @Value("${openapi.service.version}") String serviceVersion) {
    final String securitySchemeName = "bearerAuth";
    return new OpenAPI()
            .components(
                    new Components()
                            .addSecuritySchemes(securitySchemeName,
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")
                            )
            )
            .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
            .info(new Info().title(serviceTitle).version(serviceVersion));
  }
>>>>>>> Stashed changes
}
