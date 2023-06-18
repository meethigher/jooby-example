package top.meethigher.lambdatest;

import io.jooby.Context;
import io.jooby.Route;



/**
   * Route handler here is where the application logic lives.
   *
   * @author edgar
   * @since 2.0.0
   */
  public interface Handler {

    /**
     * Execute application code.
     *
     * @param ctx Web context.
     * @return Route response.
     * @throws Exception If something goes wrong.
     */
    Object apply(Context ctx) throws Exception;

    /**
     * Chain this after decorator with next and produces a new decorator.
     *
     * @param next Next decorator.
     * @return A new handler.
     */
    default Handler then(Route.After next) {
      System.out.println("Handler接口的默认then方法");
      return null;
    }
  }