package com.wsyzj.watchvideo.business.bean;

import org.litepal.crud.DataSupport;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/17
 *     desc   :
 * </pre>
 */
public class ChannelDb extends DataSupport {
    public String channelId;
    public String name;
    public boolean isChannel;   // 是否选择了频道
}
