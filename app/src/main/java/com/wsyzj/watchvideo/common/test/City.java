package com.wsyzj.watchvideo.common.test;

import java.util.List;

/**
 * @name: City
 * @author: wsyzj
 * @company: 曙华科技
 * @date: 2016-11-21 12:25
 * @comment: 城市数据
 */
public class City {


    /**
     * code : 10000
     * data : [{"BIANMA":"beijing","NAME":"北京","subDict":[{"BIANMA":"beij","NAME":"北京","subDict":[]}]},{"BIANMA":"chongqing","NAME":"重庆","subDict":[{"BIANMA":"chongq","NAME":"重庆","subDict":[]}]},{"BIANMA":"tianjin","NAME":"天津","subDict":[{"BIANMA":"tianj","NAME":"天津","subDict":[]}]},{"BIANMA":"shanghai","NAME":"上海","subDict":[{"BIANMA":"shangh","NAME":"上海","subDict":[]}]},{"BIANMA":"guangdonsheng","NAME":"广东省","subDict":[{"BIANMA":"shenzhenshi","NAME":"深圳市","subDict":[]},{"BIANMA":"guangzhou","NAME":"广州","subDict":[]},{"BIANMA":"shaoguan","NAME":"韶关","subDict":[]},{"BIANMA":"huizhou","NAME":"惠州","subDict":[]},{"BIANMA":"fozhou","NAME":"佛州","subDict":[]}]},{"BIANMA":"heilongjiangsheng","NAME":"黑龙江","subDict":[{"BIANMA":"haerbin","NAME":"哈尔滨","subDict":[]},{"BIANMA":"qiqihaer","NAME":"齐齐哈尔","subDict":[]},{"BIANMA":"mudanjiang","NAME":"牡丹江","subDict":[]}]},{"BIANMA":"jilinsheng","NAME":"吉林省","subDict":[{"BIANMA":"changchun","NAME":"长春","subDict":[]},{"BIANMA":"jilin","NAME":"吉林","subDict":[]},{"BIANMA":"yanbian","NAME":"延边","subDict":[]}]},{"BIANMA":"liaoningsheng","NAME":"辽宁省","subDict":[{"BIANMA":"shenyang","NAME":"沈阳","subDict":[]},{"BIANMA":"dalian","NAME":"大连","subDict":[]},{"BIANMA":"anshan","NAME":"鞍山","subDict":[]}]},{"BIANMA":"neimenggu","NAME":"内蒙古","subDict":[{"BIANMA":"huhehaote","NAME":"呼和浩特","subDict":[]},{"BIANMA":"baotou","NAME":"包头","subDict":[]},{"BIANMA":"wuhai","NAME":"乌海","subDict":[]}]},{"BIANMA":"hebeisheng","NAME":"河北省","subDict":[{"BIANMA":"shijiazhuang","NAME":"石家庄","subDict":[]},{"BIANMA":"baoding","NAME":"保定","subDict":[]},{"BIANMA":"zhangjiakou","NAME":"张家口","subDict":[]}]},{"BIANMA":"shanxisheng","NAME":"山西省","subDict":[{"BIANMA":"taiyuan","NAME":"太原","subDict":[]},{"BIANMA":"datong","NAME":"大同","subDict":[]},{"BIANMA":"yangquan","NAME":"阳泉","subDict":[]}]},{"BIANMA":"shanxish","NAME":"陕西省","subDict":[{"BIANMA":"xian","NAME":"西安","subDict":[]},{"BIANMA":"xianyang","NAME":"咸阳","subDict":[]},{"BIANMA":"yanan","NAME":"延安","subDict":[]}]},{"BIANMA":"shandongsheng","NAME":"山东省","subDict":[{"BIANMA":"jinan","NAME":"济南","subDict":[]},{"BIANMA":"qindao","NAME":"青岛","subDict":[]},{"BIANMA":"yantai","NAME":"烟台","subDict":[]}]},{"BIANMA":"xinjiang","NAME":"新疆","subDict":[{"BIANMA":"wulumuqi","NAME":"乌鲁木齐","subDict":[]}]},{"BIANMA":"xizang","NAME":"西藏","subDict":[{"BIANMA":"lasa","NAME":"拉萨","subDict":[]}]},{"BIANMA":"ningxia","NAME":"宁夏","subDict":[{"BIANMA":"yinchuan","NAME":"银川","subDict":[]}]},{"BIANMA":"qinhaisheng","NAME":"青海省","subDict":[{"BIANMA":"xining","NAME":"西宁","subDict":[]}]},{"BIANMA":"henansheng","NAME":"河南省","subDict":[{"BIANMA":"zhengzhou","NAME":"郑州","subDict":[]},{"BIANMA":"kaifeng","NAME":"开封","subDict":[]},{"BIANMA":"jiaozuo","NAME":"焦作","subDict":[]}]},{"BIANMA":"jiangsusheng","NAME":"江苏省","subDict":[{"BIANMA":"nanjing","NAME":"南京","subDict":[]},{"BIANMA":"wuxi","NAME":"无锡","subDict":[]},{"BIANMA":"suzhou","NAME":"苏州","subDict":[]}]},{"BIANMA":"hubeisheng","NAME":"湖北省","subDict":[{"BIANMA":"wuhan","NAME":"武汉","subDict":[]},{"BIANMA":"yichang","NAME":"宜昌","subDict":[]},{"BIANMA":"xiangyang","NAME":"襄阳","subDict":[]}]},{"BIANMA":"zhejiangsheng","NAME":"浙江省","subDict":[{"BIANMA":"ningbo","NAME":"宁波","subDict":[]},{"BIANMA":"hangzhou","NAME":"杭州","subDict":[]},{"BIANMA":"jiaxin","NAME":"嘉兴","subDict":[]}]},{"BIANMA":"anhuisheng","NAME":"安徽省","subDict":[{"BIANMA":"hefei","NAME":"合肥","subDict":[]},{"BIANMA":"wuhu","NAME":"芜湖","subDict":[]},{"BIANMA":"huainan","NAME":"淮南","subDict":[]}]},{"BIANMA":"fujiansheng","NAME":"福建省","subDict":[{"BIANMA":"fuzhou","NAME":"福州","subDict":[]},{"BIANMA":"xiamen","NAME":"厦门","subDict":[]}]},{"BIANMA":"jiangxisheng","NAME":"江西省","subDict":[{"BIANMA":"nanchang","NAME":"南昌","subDict":[]},{"BIANMA":"jiujiang","NAME":"九江","subDict":[]}]},{"BIANMA":"gansusheng","NAME":"甘肃省","subDict":[{"BIANMA":"lanzhou","NAME":"兰州","subDict":[]},{"BIANMA":"dingxi","NAME":"定西","subDict":[]},{"BIANMA":"平凉","NAME":"平凉","subDict":[]}]},{"BIANMA":"hunansheng","NAME":"湖南省","subDict":[{"BIANMA":"zhuzhou","NAME":"株洲","subDict":[]},{"BIANMA":"changsha","NAME":"长沙","subDict":[]},{"BIANMA":"xiangtan","NAME":"湘潭","subDict":[]}]},{"BIANMA":"guizhousheng","NAME":"贵州省","subDict":[{"BIANMA":"guiyang","NAME":"贵阳","subDict":[]},{"BIANMA":"zunyi","NAME":"遵义","subDict":[]}]},{"BIANMA":"sichuangsheng","NAME":"四川省","subDict":[{"BIANMA":"chengdu","NAME":"成都","subDict":[]},{"BIANMA":"panzhihua","NAME":"攀枝花","subDict":[]}]},{"BIANMA":"guangxisheng","NAME":"广西省","subDict":[{"BIANMA":"南宁","NAME":"南宁","subDict":[]},{"BIANMA":"崇左","NAME":"崇左","subDict":[]}]},{"BIANMA":"yunnansheng","NAME":"云南省","subDict":[{"BIANMA":"kunming","NAME":"昆明","subDict":[]},{"BIANMA":"dali","NAME":"大理","subDict":[]}]},{"BIANMA":"hainansheng","NAME":"海南省","subDict":[{"BIANMA":"haikou","NAME":"海口","subDict":[]},{"BIANMA":"sanya","NAME":"三亚","subDict":[]}]},{"BIANMA":"taiwan","NAME":"台湾","subDict":[{"BIANMA":"taiw","NAME":"台湾","subDict":[]}]},{"BIANMA":"xianggang","NAME":"香港","subDict":[{"BIANMA":"xiangg","NAME":"香港","subDict":[]}]},{"BIANMA":"aomen","NAME":"澳门","subDict":[{"BIANMA":"aom","NAME":"澳门","subDict":[]}]}]
     */
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * BIANMA : beijing
         * NAME : 北京
         * subDict : [{"BIANMA":"beij","NAME":"北京","subDict":[]}]
         */

        private String BIANMA;
        private String NAME;
        private List<SubDictBean> subDict;

        public String getBIANMA() {
            return BIANMA;
        }

        public void setBIANMA(String BIANMA) {
            this.BIANMA = BIANMA;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public List<SubDictBean> getSubDict() {
            return subDict;
        }

        public void setSubDict(List<SubDictBean> subDict) {
            this.subDict = subDict;
        }

        public static class SubDictBean {
            /**
             * BIANMA : beij
             * NAME : 北京
             * subDict : []
             */

            private String BIANMA;
            private String NAME;
            private List<?> subDict;

            public String getBIANMA() {
                return BIANMA;
            }

            public void setBIANMA(String BIANMA) {
                this.BIANMA = BIANMA;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public List<?> getSubDict() {
                return subDict;
            }

            public void setSubDict(List<?> subDict) {
                this.subDict = subDict;
            }
        }
    }
}
