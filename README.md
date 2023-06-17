# Script API-仅支持简单路由

Script API 通常用于编写小型的、单一用途的路由处理器。

支持占位符路由规则，但是像拼参形式的就不支持。

```java
import static io.jooby.Jooby.runApp;

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
```

# MVC API-支持复杂路由

添加依赖

```xml
<!-- MVC API 核心模块 -->
<dependency>
    <groupId>io.jooby</groupId>
    <artifactId>jooby-apt</artifactId>
    <version>2.16.1</version>
</dependency>
<!-- json模块 -->
<dependency>
    <groupId>io.jooby</groupId>
    <artifactId>jooby-gson</artifactId>
    <version>2.16.1</version>
</dependency>
```

创建controller

```java
import io.jooby.MediaType;
import io.jooby.Multipart;
import io.jooby.annotations.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Path("/mvc")
public class Controller {

    @GET("/get")
    public String get() {
        return "Hello World";
    }

    @GET("/pathParam/{id}")
    public String pathParam(@PathParam String id) {
        return id;
    }

    @GET("/queryParam")
    public String queryParam(@QueryParam String id, @QueryParam String name) {
        return name + "--->" + id;
    }

    /**
     * form表单,支持两种形式
     * application/x-www-form-urlencoded，不支持文件
     * multipart/form-data，支持文件
     */
    @POST("/formParam")
    public String formParam(@FormParam String name, @FormParam String age, @FormParam Multipart file) {
        if (file != null) {
            try (InputStream is = file.file("file").stream(); FileOutputStream fos = new FileOutputStream("test.txt")) {
                int b;
                while ((b = is.read()) != -1) {
                    fos.write(b);
                }
                fos.flush();
            } catch (IOException e) {
            }
        }
        return name + "--->" + age;
    }


    @POST("/bodyParam")
    @Consumes(MediaType.JSON)//可省略
    @Produces(MediaType.JSON)//可省略
    public Object bodyParam(Object body) {
        return body;
    }

    @POST("/pageQuery")
    public String pageQuery(PageRequest req) {
        return req.toString();
    }
}

class PageRequest {
    private Integer pageIndex;
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
```

注册controller

```java
import io.jooby.json.GsonModule;
import top.meethigher.mvc.controller.Controller;

import static io.jooby.Jooby.runApp;

public class MVCApplication {
    public static void main(String[] args) {
        runApp(args, app -> {
            // 设置 JSON 支持
            app.install(new GsonModule());
            //注册控制器
            app.mvc(new Controller());
        });
    }
}
```

