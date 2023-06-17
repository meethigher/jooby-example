package top.meethigher.script;

import static io.jooby.Jooby.runApp;

/**
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/17 23:31
 */
public class ScriptAPIApplication {

    public static void main(String[] args) {
        //consumer函数式接口简写
        runApp(args, app -> {
            app.get("/param/{id}", ctx -> {
                String id = ctx.path("id").value();
                return id;
            });
            app.post("/param/{id}", ctx -> {
                return ctx.path("id").value();
            });
        });
    }
}
