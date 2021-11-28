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
                                .nest(path("/product"), route(POST("create"), productHandler::add)
                                                .andRoute(GET("read/{id}").and(contentType(APPLICATION_JSON))
                                                                .or(GET("read/{id}")), productHandler::findById)
                                                .andRoute(PUT("update/{id}"), productHandler::update)
                                                .andRoute(DELETE("delete/{id}"), productHandler::delete))

                                .andNest(path("/ticket"), route(POST("create"), ticketHandler::add)
                                                .andRoute(GET("create/{id}").and(contentType(APPLICATION_JSON))
                                                                .or(GET("read/{id}")), ticketHandler::findById)
                                                .andRoute(DELETE("delete/{id}"), ticketHandler::delete)
                                                .andRoute(GET("analytics"), ticketHandler::analytics)
                                                .andRoute(GET("findall"), ticketHandler::getAll));
        }
}
