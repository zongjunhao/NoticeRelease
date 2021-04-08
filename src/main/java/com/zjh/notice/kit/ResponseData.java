package com.zjh.notice.kit;

/**
 * @author zongjunhao
 * 向前端返回数据的载体
 * data：数据
 * code：返回码
 * desc：返回信息描述
 */
@SuppressWarnings("unused")
public class ResponseData {
    private Object data;
    private String code;
    private String desc;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

    public void setResult(ResultCodeEnum code) {
        this.code = code.getCode();
        this.desc = code.getDesc();
    }

	@Override
	public String toString() {
		return "ResponseData{" +
				"data=" + data +
				", code='" + code + '\'' +
				", desc='" + desc + '\'' +
				'}';
	}
}