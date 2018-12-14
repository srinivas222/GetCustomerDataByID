package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.example.domain.Customer;

@Component
public class CustomerRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration().component("restlet").host("localhost").port("8080").bindingMode(RestBindingMode.auto);

		GsonDataFormat json = new GsonDataFormat(Customer.class);

		// ----------------Add the Customer----------------

		/*rest("/customerPost").post().type(Customer.class).to("direct:customerPost");
		from("direct:customerPost").log("Customer id is ${body}").unmarshal(json).process(new InsertProcessor())
				.log("the message in the headers is:${headers.id},${headers.name},${headers.email},${headers.location}")
				.setBody(simple(
						"insert into customer (cid,cname,email,location) values(${headers.id},'${headers.name}','${headers.email}','${headers.location}')"))
				.to("jdbc:datasource").setBody(simple("select * from customer")).to("jdbc:datasource").marshal(json)
				.to("log:?level=INFO&showBody=true");*/
		
		/*rest("/customerPost").post().type(Customer.class).to("direct:customerPost");
		from("direct:customerPost").log("Customer id is ${body}").unmarshal(json).process(new InsertProcessor())
				.log("the message in the headers is:${headers.id},${headers.name},${headers.email},${headers.location}")
				.setBody(simple(
						"insert into customer (cid,cname,email,location) values(${headers.id},'${headers.name}','${headers.email}','${headers.location}')"))
				.to("jdbc:datasource")
				.setBody(simple("select * from customer where cname ='${headers.id}'")).to("jdbc:datasource").log("Response ${body}").marshal(json)
				
				.to("log:?level=INFO&showBody=true");
		// --------------------Update Customer------------------------

		rest("/customerUpdateById").put("{id}").type(Customer.class).to("direct:putId");
		from("direct:putId").log("Customer id is ${header.id}")
				.setBody(simple(
						"update customer set cname='${header.name}',email='${header.email}'  where cid=${header.id}"))
				.to("jdbc:datasource");

		// ------------------Deleting the customer------------------------

		rest("/customerDelById").delete("{id}").to("direct:deleteId");
		from("direct:deleteId").log("Customer id is ${header.id}")
				.setBody(simple("delete from customer where cid=${header.id}")).to("jdbc:datasource")
				// .log("Customer deleted")
				.to("log:?level=INFO&showBody=true");*/

		// -----------Get Customer by id----------
		/*onException(JsonParseException.class,Exception.class,SQLException.class)
	    .bean(new ErrorProcessor()).handled(true)
	    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
	    .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
	    .setBody().constant("Invalid json data");
*/
		/*rest("/customer")

				.post().type(Customer.class).to("direct:getName");
		from("direct:getName").unmarshal(json).process(new InsertProcessor())

				.log("Customer id is ${header.id}")
				.transform(simple("${in.body[0]}"))
				.log("the query is ${body}")
				.to("sqlComponent:{{select.sql}}")
				//.setBody(simple("select * from customer where cname='${headers.id}'"))
				.log("the exchange object is ${body}")
				
				.marshal(json)

				.to("log:?level=INFO&showBody=true");*/

		// ----------- All Customers------------

		rest("/allCustomer").post().to("direct:getAllCustomer");
		from("direct:getAllCustomer").log("Customer id is ${body}").setBody(simple("select * from customer"))
				.to("jdbc:datasource")
				.marshal(json);
	}
}
