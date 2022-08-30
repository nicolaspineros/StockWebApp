package co.edu.escuelaing.stockwebapp;

import static spark.Spark.*;

public class StockWebApp {
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/hello", (req, res) -> "Hello " + req.queryParams("name"));
        get("/stock", (req, res) -> {
            res.type("application/json");
            System.out.println(req.queryParams());
            System.out.println(req.queryParams("name")+req.queryParams("type"));
            return ApiReader.getStock(req.queryParams("name"),req.queryParams("type"));
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
