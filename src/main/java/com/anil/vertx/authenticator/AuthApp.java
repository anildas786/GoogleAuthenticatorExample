package com.anil.vertx.authenticator;

import io.vertx.core.Vertx;
import sun.security.provider.certpath.Vertex;

public class AuthApp {

        public static void main ( String args []) {

            Vertx authService = Vertx.vertx();
            authService.deployVerticle(new AuthService());

        }
    }
