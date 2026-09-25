package com.company.design_patterns.behavioral.chain_of_responsibility;

/**
 * Handler: sorğunu ya özü emal edib zənciri dayandırır, ya da növbəti handler-ə ötürür.
 * Göndərən tərəf (client) sorğunu kimin emal edəcəyini bilmir.
 */
public abstract class Handler {

    private Handler next;

    /** Zənciri qurmaq üçün: a.linkWith(b).linkWith(c). Sonuncu handler-i qaytarır. */
    public Handler linkWith(Handler next) {
        this.next = next;
        return next;
    }

    public abstract boolean handle(Request request);

    protected boolean handleNext(Request request) {
        return next == null || next.handle(request);
    }
}
