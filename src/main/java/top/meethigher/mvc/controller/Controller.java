package top.meethigher.mvc.controller;

import io.jooby.MediaType;
import io.jooby.Multipart;
import io.jooby.annotations.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 
 * @author chenchuancheng github.com/meethigher
 * @since 2023/6/17 23:31
 */
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
