package top.meethigher.mvc.utils;

/**
 * web响应
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2022/12/8 22:03
 */
public class Resp<T> {

    private Integer code;

    private String desc;

    private T data;

    private Resp(Integer code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static <T> Resp<T> getSuccessResp(T data) {
        return new Resp<>(RespStatus.SUCCESS.code,
                RespStatus.SUCCESS.desc,
                data);
    }

    public static Resp getSuccessResp() {
        return new Resp<>(RespStatus.SUCCESS.code,
                RespStatus.SUCCESS.desc, null);
    }

    public static Resp getFailureResp(String desc) {
        return new Resp<>(RespStatus.FAILURE.code,
                desc,
                null);
    }


    public static Resp getErrorResp() {
        return new Resp<>(RespStatus.ERROR.code,
                RespStatus.ERROR.desc,
                null);
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    public String toJSONString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":")
                .append(code);
        sb.append(",\"desc\":\"")
                .append(desc).append('\"');
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }
}
