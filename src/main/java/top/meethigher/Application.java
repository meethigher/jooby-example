package top.meethigher;

import io.jooby.Jooby;
import io.jooby.MediaType;

import java.nio.charset.StandardCharsets;

/**
 *
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/17 23:30
 */
public class Application extends Jooby {

    //构造函数快
    {
        // use Decorator.
        decorator(next -> ctx -> {
            // get response content-type.
            final MediaType responseType = ctx.getResponseType();
            // if content-type is text-specific
            if(responseType.isTextual())
                // override response type with current media type and always use UTF-8 charset!
                ctx.setResponseType(responseType, StandardCharsets.UTF_8);
            // pipe next response procedure.
            return next.apply(ctx);
        });
        get("/", ctx -> {
            String appName = getConfig().getString("app.name");
            return "Welcome to " + appName;
        });

        get("/database", ctx -> {
            String dbUrl = getConfig().getString("database.url");
            return "Database URL: " + dbUrl;
        });
    }

    public static void main(String[] args) {
        runApp(args, Application.class);
    }
}