package com.anil.vertx.authenticator;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.handler.BodyHandler;

public class AuthService extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        super.start();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/api/*").handler(BodyHandler.create());
        router.post("/api/signup").handler(this::doSignup);
        server.requestHandler(router).listen(4567, res->{
            if (res.succeeded()){
                System.out.println("Server listens to port 4567");
            } else {
                System.out.println("Something went wrong");
                System.out.println(res.cause().getLocalizedMessage());
            }
        });
    }

    //access it using localhost:4567/api/signup
    private void doSignup(RoutingContext ctx){
        // Get Data from view
        JsonObject req = ctx.getBodyAsJson();
        String username = req.getString("username");
        String password = req.getString("password");

        GoogleAuthenticator authenticator = new GoogleAuthenticator();

        //Get Generate Key
        System.out.println(" Calling Google Authenticator to generate AuthKey");
        GoogleAuthenticatorKey authKey = authenticator.createCredentials();
        String key = authKey.getKey();
        System.out.println(" Google Authenticator generated AuthKey");

        //Store Data from Repository
        User user = new User(username,password,key);

        //send response to the user
        JsonObject res = new JsonObject().put("key",key);
        ctx.response().setStatusCode(200).end(res.encode());
    }

}
