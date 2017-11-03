package com.wsyzj.watchvideo.common.business.bean;

import java.util.List;

/**
 * author : 焦洋
 * time   : 2017/11/2  12:23
 * desc   : KaiYan
 */
public class KaiYan {

    public String resultCode;
    public String resultMsg;
    public String reqId;
    public String systemTime;
    public List<AreaListBean> areaList;
    public List<DataListBean> dataList;

    public static class AreaListBean {
        /**
         * area_id : 100001
         * expInfo : {"algorighm_exp_id":"","front_exp_id":"0","s_value":"b79c9436-4d59-4452-a7ad-20836d15b8ab_2153673381279278"}
         */

        public String area_id;
        public ExpInfoBean expInfo;

        public static class ExpInfoBean {
            /**
             * algorighm_exp_id :
             * front_exp_id : 0
             * s_value : b79c9436-4d59-4452-a7ad-20836d15b8ab_2153673381279278
             */

            public String algorighm_exp_id;
            public String front_exp_id;
            public String s_value;
        }
    }

    public static class DataListBean {
        /**
         * nodeType : 1
         * nodeName : 头条
         * isOrder :
         * nodeLogo :
         * nodeDesc :
         * moreId :
         * contList : [{"contId":"1064396","name":"周末推片：速8与西游的王牌之战！","pic":"http://image.pearvideo.com/cont/20170414/cont-1064396-10256904.jpg","nodeInfo":{"nodeId":"18","name":"文娱小队长","logoImg":"http://image1.pearvideo.com/node/18-10027897-logo.jpg"},"link":"http://","linkType":"0","cornerLabel":"5","cornerLabelDesc":"推荐","forwordType":"1","videoType":"1","duration":"02'03\"","liveStatus":"","liveStartTime":"","praiseTimes":"228","adExpMonitorUrl":"","coverVideo":"http://video.pearvideo.com/head/20170414/cont-1064396-10371532.mp4"},{"contId":"1064563","name":"当轮滑遇上中国风，小姐姐完美演绎","pic":"http://image.pearvideo.com/cont/20170414/cont-1064563-10257243.jpg","nodeInfo":{"nodeId":"25","name":"一手Video","logoImg":"http://image.pearvideo.com/node/25-10027890-logo.jpg"},"link":"http://","linkType":"0","cornerLabel":"5","cornerLabelDesc":"推荐","forwordType":"1","videoType":"1","duration":"01'13\"","liveStatus":"","liveStartTime":"","praiseTimes":"351","adExpMonitorUrl":"","coverVideo":"http://video.pearvideo.com/head/20170414/cont-1064563-10372062.mp4"},{"contId":"1064519","name":"《人民的名义》中他们都是健身达人","pic":"http://image2.pearvideo.com/cont/20170414/cont-1064519-10257416.jpg","nodeInfo":{"nodeId":"174","name":"看球","logoImg":"http://image.pearvideo.com/node/174-10065489-logo.png"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"02'20\"","liveStatus":"","liveStartTime":"","praiseTimes":"191","adExpMonitorUrl":"","coverVideo":"http://video.pearvideo.com/head/20170414/cont-1064519-10372273.mp4"},{"contId":"1064434","name":"来看！朝鲜国宝杂技团表演空中飞人","pic":"http://image.pearvideo.com/cont/20170414/cont-1064434-10257006.jpg","nodeInfo":{"nodeId":"22","name":"DIGGER","logoImg":"http://image.pearvideo.com/node/22-10027893-logo.jpg"},"link":"http://","linkType":"0","cornerLabel":"3","cornerLabelDesc":"独播","forwordType":"1","videoType":"1","duration":"01'22\"","liveStatus":"","liveStartTime":"","praiseTimes":"237","adExpMonitorUrl":"","coverVideo":"http://video.pearvideo.com/head/20170414/cont-1064434-10371684.mp4"},{"contId":"1063755","name":"宋小宝春晚灵感，来自撸串吃饺子","pic":"http://image2.pearvideo.com/cont/20170414/cont-1063755-10256684.jpg","nodeInfo":{"nodeId":"25","name":"一手Video","logoImg":"http://image.pearvideo.com/node/25-10027890-logo.jpg"},"link":"http://","linkType":"0","cornerLabel":"3","cornerLabelDesc":"独播","forwordType":"1","videoType":"1","duration":"01'10\"","liveStatus":"","liveStartTime":"","praiseTimes":"2309","adExpMonitorUrl":"","coverVideo":"http://video.pearvideo.com/head/20170414/cont-1063755-10371239.mp4"}]
         * activityList : [{"activityId":"12","name":"晒出身边神奇的孩子","runStatus":"0","backgroundImg":"http://imageugc2.pearvideo.com/activity/20170413/12-bg-161653.png","beginTime":"2017.4.14","endTime":"2017.4.24"},{"activityId":"10","name":"以读攻读！朗读集赞换大奖","runStatus":"1","backgroundImg":"http://imageugc1.pearvideo.com/activity/20170331/10-bg-165708.png","beginTime":"2017.4.5","endTime":"2017.4.14"}]
         */

        public String nodeType;
        public String nodeName;
        public String isOrder;
        public String nodeLogo;
        public String nodeDesc;
        public String moreId;
        public List<ContListBean> contList;
        public List<ActivityListBean> activityList;

        public static class ContListBean {
            /**
             * contId : 1064396
             * name : 周末推片：速8与西游的王牌之战！
             * pic : http://image.pearvideo.com/cont/20170414/cont-1064396-10256904.jpg
             * nodeInfo : {"nodeId":"18","name":"文娱小队长","logoImg":"http://image1.pearvideo.com/node/18-10027897-logo.jpg"}
             * link : http://
             * linkType : 0
             * cornerLabel : 5
             * cornerLabelDesc : 推荐
             * forwordType : 1
             * videoType : 1
             * duration : 02'03"
             * liveStatus :
             * liveStartTime :
             * praiseTimes : 228
             * adExpMonitorUrl :
             * coverVideo : http://video.pearvideo.com/head/20170414/cont-1064396-10371532.mp4
             */

            public String contId;
            public String name;
            public String pic;
            public NodeInfoBean nodeInfo;
            public String link;
            public String linkType;
            public String cornerLabel;
            public String cornerLabelDesc;
            public String forwordType;
            public String videoType;
            public String duration;
            public String liveStatus;
            public String liveStartTime;
            public String praiseTimes;
            public String adExpMonitorUrl;
            public String coverVideo;

            public static class NodeInfoBean {
                /**
                 * nodeId : 18
                 * name : 文娱小队长
                 * logoImg : http://image1.pearvideo.com/node/18-10027897-logo.jpg
                 */

                public String nodeId;
                public String name;
                public String logoImg;

            }
        }

        public static class ActivityListBean {
            /**
             * activityId : 12
             * name : 晒出身边神奇的孩子
             * runStatus : 0
             * backgroundImg : http://imageugc2.pearvideo.com/activity/20170413/12-bg-161653.png
             * beginTime : 2017.4.14
             * endTime : 2017.4.24
             */

            public String activityId;
            public String name;
            public String runStatus;
            public String backgroundImg;
            public String beginTime;
            public String endTime;
        }
    }
}
