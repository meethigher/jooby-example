package top.meethigher.lambdatest;

import io.jooby.Context;

public class LambdaTest {


    /**
     * 原生写法
     */
    public static void origin() {
        Decorator decorator = new Decorator() {
            @Override
            public Handler apply(Handler next) {
                System.out.println("Decorator--apply");
                Handler handler = ctx -> {
                    System.out.println("Handler--apply");
                    return next.apply(ctx);
                };
                try {
                    handler.apply(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return handler;
            }
        };

        decorator.apply(new Handler() {
            @Override
            public Object apply(Context ctx) throws Exception {
                return "你好";
            }
        });
    }


    /**
     * lambda写法
     */
    public static void lambda() {
        Decorator decorator=next -> ctx -> {
            System.out.println("Handler--apply");
            return next.apply(ctx);
        };
        decorator.apply(new Handler() {
            @Override
            public Object apply(Context ctx) throws Exception {

                return "你好";
            }
        });
    }

    public static void main(String[] args) {
        lambda();
    }
}
