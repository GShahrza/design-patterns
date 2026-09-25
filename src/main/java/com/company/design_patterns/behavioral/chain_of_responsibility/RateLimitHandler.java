package com.company.design_patterns.behavioral.chain_of_responsibility;

import java.util.HashMap;
import java.util.Map;

public class RateLimitHandler extends Handler {

    private final int maxRequests;
    private final Map<String, Integer> counters = new HashMap<>();

    public RateLimitHandler(int maxRequests) {
        this.maxRequests = maxRequests;
    }

    @Override
    public boolean handle(Request request) {
        int count = counters.merge(request.clientIp(), 1, Integer::sum);
        if (count > maxRequests) {
            System.out.println("  429 Too Many Requests");
            return false;
        }
        System.out.println("  rate limit OK (" + count + "/" + maxRequests + ")");
        return handleNext(request);
    }
}
