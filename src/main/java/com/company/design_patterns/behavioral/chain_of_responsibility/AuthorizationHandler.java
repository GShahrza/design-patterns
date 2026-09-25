package com.company.design_patterns.behavioral.chain_of_responsibility;

public class AuthorizationHandler extends Handler {
    @Override
    public boolean handle(Request request) {
        if (request.path().startsWith("/admin") && !"ADMIN".equals(request.role())) {
            System.out.println("  403 Forbidden: admin role required");
            return false;
        }
        System.out.println("  role OK");
        return handleNext(request);
    }
}
