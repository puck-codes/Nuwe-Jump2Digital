package tokyo.boblennon.nuwe.jump2digital;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import tokyo.boblennon.nuwe.jump2digital.application.handler.ProductHandler;
import tokyo.boblennon.nuwe.jump2digital.application.handler.TicketHandler;
import tokyo.boblennon.nuwe.jump2digital.infrastructure.product.ProductRepositoryImp;

@Configuration
public class RouterFunctionConfig {

        @RouterOperations({
                        @RouterOperation(path = "/product", beanClass = ProductRepositoryImp.class, beanMethod = "add", method = POST),
                        @RouterOperation(path = "/product/{id}", beanClass = ProductRepositoryImp.class, beanMethod = "findById", method = GET),
                        @RouterOperation(path = "/product/{id}", beanClass = ProductRepositoryImp.class, beanMethod = "update", method = PUT),
                        @RouterOperation(path = "/product/{id}", beanClass = ProductRepositoryImp.class, beanMethod = "delete", method = DELETE)
        })
        @Bean
        public RouterFunction<ServerResponse> routes(ProductHandler productHandler, TicketHandler ticketHandler) {
                return RouterFunctions
                                .nest(path("/product"), route(POST(""), productHandler::add)
                                                .andRoute(GET("/{id}").and(contentType(APPLICATION_JSON))
                                                                .or(GET("/{id}")), productHandler::findById)
                                                .andRoute(PUT("/{id}"), productHandler::update)
                                                .andRoute(DELETE("{id}"), productHandler::delete))
                                .andNest(path("/ticket"), route(POST(""), ticketHandler::add)
                                                .andRoute(GET("/{id}").and(contentType(APPLICATION_JSON))
                                                                .or(GET("/{id}")), ticketHandler::findById)
                                                .andRoute(DELETE("/{id}"), ticketHandler::delete));

        }
}
