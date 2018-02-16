package com.estafet.fis.sales.aggregator.restful.sales.consumer.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.estafet.fis.sales.aggregator.restful.sales.consumer.model.ProductSalesBatch;

@Component
public class ProductSalesConsumerRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		 // @formatter:off
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .apiContextPath("/api-doc")
                .apiProperty("api.title", "User API")
                .apiProperty("api.version", "1.0.0")
                .apiProperty("cors", "true");
        

        rest("/batch").post().type(ProductSalesBatch.class)
        				     .to("direct:send")
        				     .responseMessage().code(200).message("Success").endResponseMessage();

        from("direct:send")
        	.marshal().json(JsonLibrary.Jackson)
        	.log("${body}")
        	.setExchangePattern(ExchangePattern.InOnly)
        	.to("amqp:queue:incoming.sales");
        
        // @formatter:on
	}

}
