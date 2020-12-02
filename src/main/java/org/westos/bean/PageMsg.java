package org.westos.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PageMsg implements Serializable {
    //状态码：比如：100 代表成功，200 代表失败
    private int code;
    //状态码对应的文字提式
    private String msg;
    //把pageInfo对象可以放到这个集合中
    private Map<String,Object> pageInfoMap=new HashMap<>();
    //提供请求成功的方法 写成静态的方便调用
    public static PageMsg success(){
        PageMsg pageMsg = new PageMsg();
        pageMsg.setCode(100);
        pageMsg.setMsg("处理成功");
        return pageMsg;
    }
    //提供请求失败的方法
    public static PageMsg fail() {
        PageMsg pageMsg = new PageMsg();
        pageMsg.setCode(200);
        pageMsg.setMsg("处理失败");
        return pageMsg;
    }
    //再提供一个添加pageInfo的添加方法
    public PageMsg add(String key,Object obj){
        this.pageInfoMap.put(key,obj);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getPageInfoMap() {
        return pageInfoMap;
    }

    public void setPageInfoMap(Map<String, Object> pageInfoMap) {
        this.pageInfoMap = pageInfoMap;
    }
}
