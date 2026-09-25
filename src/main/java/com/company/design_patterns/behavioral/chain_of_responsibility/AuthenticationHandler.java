package com.company.design_patterns.behavioral.chain_of_responsibility;

public class AuthenticationHandler extends Handler {
    @Override
    public boolean handle(Request request) {
        if (request.token() == null || request.token().isBlank()) {
            System.out.println("  401 Unauthorized: missing token");
            return false;
        }
        System.out.println("  auth OK");
        return handleNext(request);
    }
}
