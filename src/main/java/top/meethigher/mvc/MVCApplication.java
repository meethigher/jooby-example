package top.meethigher.mvc;

import io.jooby.*;
import io.jooby.json.GsonModule;
import top.meethigher.mvc.controller.Controller;
import top.meethigher.mvc.utils.Resp;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

import static io.jooby.Jooby.runApp;

/**
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/17 23:30
 */
public class MVCApplication {
    public static void main(String[] args) {
        runApp(args, app -> {
            // 设置全局装饰器
            app.decorator(new GlobalDecorator());
            // 设置全局异常处理
            app.error(new GlobalErrorHandler());
            // 设置 JSON 支持
            app.install(new GsonModule());
            // 注册控制器
            app.mvc(new Controller());
        });
    }
}

/**
 * 全局装饰器
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/18 17:38
 */
class GlobalDecorator implements Route.Decorator {
    @Nonnull
    @Override
    public Route.Handler apply(@Nonnull Route.Handler next) {
        return ctx -> {
            final MediaType responseType = ctx.getResponseType();
            //设置编码为utf-8
            if (responseType.isTextual()) {
                ctx.setResponseType(responseType, StandardCharsets.UTF_8);
            }
            return next.apply(ctx);
        };
    }
}

/**
 * 全局异常处理器
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/18 17:36
 */
class GlobalErrorHandler extends DefaultErrorHandler {
    @Override
    public void apply(@Nonnull Context ctx, @Nonnull Throwable cause, @Nonnull StatusCode code) {
        // 处理异常并返回适当的响应
//        if (cause instanceof RuntimeException) {//会存在多态问题，如继承。而我需要的是严格相等
        if (cause.getClass().equals(RuntimeException.class)) {
            // 处理自定义异常
            ctx.setResponseType(MediaType.json, StandardCharsets.UTF_8);
            ctx.send(Resp.getFailureResp(cause.getMessage()).toString());
        } else {
//            // 处理自定义异常
//            ctx.setResponseType(MediaType.json, StandardCharsets.UTF_8);
//            ctx.send(Resp.getErrorResp().toString());
            super.apply(ctx, cause, code);
        }
    }
}
