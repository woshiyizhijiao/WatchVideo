package com.wsyzj.watchvideo.common.tools;

import com.wsyzj.watchvideo.common.base.BaseEventBus;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 焦洋
 * @date 2017/12/7 9:47
 * @Description: 事件订阅通知封装
 */
public class EventBusUtils {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(BaseEventBus event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(BaseEventBus event) {
        EventBus.getDefault().postSticky(event);
    }
}
