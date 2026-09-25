package com.company.design_patterns.behavioral.chain_of_responsibility;

public class Main {
    public static void main(String[] args) {
        Handler chain = new RateLimitHandler(2);
        chain.linkWith(new AuthenticationHandler())
             .linkWith(new AuthorizationHandler());

        Request[] requests = {
                new Request("/orders", "abc", "USER", "10.0.0.1"),
                new Request("/admin/users", "abc", "USER", "10.0.0.2"),
                new Request("/orders", null, "USER", "10.0.0.3"),
                new Request("/admin/users", "xyz", "ADMIN", "10.0.0.1"),
                new Request("/orders", "abc", "USER", "10.0.0.1"),
        };
        for (Request r : requests) {
            System.out.println(r.clientIp() + " " + r.path());
            boolean ok = chain.handle(r);
            System.out.println("  => " + (ok ? "200 OK" : "rejected"));
        }
    }
}
