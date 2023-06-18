package top.meethigher.lambdatest;

public interface Decorator {
    /**
     * Chain the decorator within next handler.
     *
     * @param next Next handler.
     * @return A new handler.
     */
    Handler apply(Handler next);

    /**
     * Chain this decorator with another and produces a new decorator.
     *
     * @param next Next decorator.
     * @return A new decorator.
     */
    default Decorator then(Decorator next) {
        return h -> apply(next.apply(h));
    }

    /**
     * Chain this decorator with a handler and produces a new handler.
     *
     * @param next Next handler.
     * @return A new handler.
     */
    default Handler then(Handler next) {
        return ctx -> apply(next).apply(ctx);
    }
}