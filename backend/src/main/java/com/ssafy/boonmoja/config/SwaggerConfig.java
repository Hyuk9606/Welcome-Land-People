package com.ssafy.boonmoja.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "API 명세서",
                description = "API 명세서 테스트 입니다.",
                version = "v1"))
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi customAllOpenAPi() {
        String[] paths = {"/**"};

        return GroupedOpenApi
                .builder()
                .group("API")
                .pathsToMatch(paths)
                .addOpenApiCustomiser(buildSecurityOpenApi()).build();
    }

    public OpenApiCustomiser buildSecurityOpenApi() {
        // jwt token 을 한번 설정하면 header 에 값을 넣어주는 코드, 자세한건 아래에 추가적으로 설명할 예정

        return OpenApi -> OpenApi.addSecurityItem(new SecurityRequirement().addList("jwt token").addList("refresh token"))
                .getComponents().addSecuritySchemes("jwt token", new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT")
                        .scheme("bearer"))
                .addSecuritySchemes("refresh token", new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT")
                        .scheme("bearer"));
    }

}