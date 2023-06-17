package top.meethigher.mvc;

import io.jooby.OpenAPIModule;
import io.jooby.json.GsonModule;
import top.meethigher.mvc.controller.Controller;

import static io.jooby.Jooby.runApp;

/**
 * 
 * 
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/17 23:30
 */
public class MVCApplication {
    public static void main(String[] args) {
        runApp(args, app -> {
//            // 设置接口文档支持
//            app.install(new OpenAPIModule());
            // 设置 JSON 支持
            app.install(new GsonModule());
            //注册控制器
            app.mvc(new Controller());
        });
    }
}
