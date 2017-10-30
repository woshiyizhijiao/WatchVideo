package com.wsyzj.watchvideo.common.test;

import com.wsyzj.watchvideo.common.http.BaseEntity;

import java.util.List;

/**
 * @name: City
 * @author: wsyzj
 * @company: 曙华科技
 * @date: 2016-11-21 12:25
 * @comment: 城市数据
 */
public class City<T> extends BaseEntity {

    /**
     * indexshow : [{"protable":"","weburl":"https://www.taobao.com/","click_type":"3","protype":"","id":"18ec93fe7f5849b585f57d973fc21451","app_imgurl":"20160803/5bfe4c2294ac4b9e9d581ff16d7b0d36.jpg","proid":""},{"protable":"fuzhuang","weburl":"","click_type":"1","protype":"","id":"a88e3776b59741a8a18335b2aa00c45a","app_imgurl":"20160802/fa2e9ad9463c43ac9beb9ebfe0a98c65.jpg","proid":"f7b51c1163d64fdbbea632a82e25a9ca"},{"protable":"","weburl":"","click_type":"3","protype":"","id":"da73ecbdf50041a389210fe67c95d436","app_imgurl":"20160927/c5a0225b11c9409e8c477007fd08c1dd.png","proid":""},{"protable":"","weburl":"","click_type":"3","protype":"","id":"e7a4844a512f467ca2a3a4d89d93b20c","app_imgurl":"20160927/07829efef3c04b06b8ae6dd601e7a1c8.png","proid":""}]
     * meetpic : [{"protable":"fuzhuang","weburl":"","click_type":"2","protype":"nvzhuang","id":"88134b76468e4227aa87059e60f8ffa1","app_imgurl":"20160730/3c9c2165372446f89e42807574f56505.jpg","proid":""},{"protable":"jiaju","weburl":"","click_type":"2","protype":"shenghuoyongpin","id":"e4997bec2ab548d89d11dbc49f8b7814","app_imgurl":"20160730/3681011e86e5455d8feba92f54b54228.jpg","proid":""},{"protable":"shouji","weburl":"","click_type":"2","protype":"tongxun","id":"d3c28d7616f44dcf9d32bf1d8177f05e","app_imgurl":"20160730/8d1a4724156c42a29da160ce783c4cdd.jpg","proid":""},{"protable":"diannan","weburl":"","click_type":"2","protype":"diannaozhengji","id":"2f2b901bf8404d998fdf57aa7caab734","app_imgurl":"20160730/78c660f8f0944b8c87f2140c11db6d33.jpg","proid":""},{"protable":"shipin","weburl":"","click_type":"2","protype":"lengdongshiyin","id":"de37acf5daee43df9aff4cfd2e56de3e","app_imgurl":"20160730/fe2d1dcc041b45de90232c852b878cac.jpg","proid":""}]
     * hotProducts : [{"id":"998cc1ba90f041c8abc9de3e0ffb153e","app_proimgurl":"20160727/bb33e39ac5624141adaadcab26812173.jpg","protable":"dianqi","proid":"c64725f89e07408db449267d9d7c90fa","pro_alias":"麻布沙发"},{"id":"9c353fd214b94d488281683de276e391","app_proimgurl":"20160727/69315d3fe025488ea5943aa225e8203f.jpg","protable":"dianqi","proid":"99ac775021954cbfb8c289152699bebe","pro_alias":"麻布沙发"},{"id":"eba73b54a63d4143a988829369c5dd48","app_proimgurl":"20160727/023926aa4f254a999102696f3e67f908.jpg","protable":"dianqi","proid":"31716cd4e3e84358bbb66f138536be87","pro_alias":"麻布沙发"},{"id":"75459b01c7df47959693ed9779c5c57e","app_proimgurl":"20160727/d772bd9c0c6341b59813f7a7dd6c659f.jpg","protable":"dianqi","proid":"c64725f89e07408db449267d9d7c90fa","pro_alias":"麻布沙发"},{"id":"f745b99c1610492c8ed2cc54361fa96d","app_proimgurl":"20160727/058a518eb6c646a0824ab17a478ef678.jpg","protable":"dianqi","proid":"99ac775021954cbfb8c289152699bebe","pro_alias":"麻布沙发"},{"id":"8358f2a9a3eb49cf8410eeadd83942c3","app_proimgurl":"20160727/3a00ead24716498d9f2b7e890586011e.jpg","protable":"dianqi","proid":"31716cd4e3e84358bbb66f138536be87","pro_alias":"麻布沙发"},{"id":"2f3cb1ab2beb47b6a9a8f81f4f7b70de","app_proimgurl":"20160730/0560b69ea97f46bb9cefb23df5238ba2.jpg","protable":"jiaju","proid":"b5a18a8f8d024fa9a63ec4e5d944ae5f","pro_alias":"唐朝饰界"}]
     * recommend : [{"proid":"284c345256f74fcb81053b7c2a968351","protable":"shouji","app_proimgurl":"20160729/de5dd547d48c44dfa25587be07d0faeb.jpg","pro_alias":"群体出游","description":"看家看店 家庭摄像头 高清夜视 语音唤醒","proname":"中兴（ZTE）小兴看看Memo 360°智能网络摄像机 wifi无线监控摄像头","price":0.01,"originalprice":259},{"proid":"e2b959fef3bf4bc28f738ac2d845b771","protable":"shouji","app_proimgurl":"20160728/5b9181594ce6495c93ebd7f6baef0a75.jpg","pro_alias":"太阳镜会场","description":"5.5英寸In Cell屏幕，金属一体化机身，后1600 前800万像素！","proname":"乐视（Le）乐2（X620）32GB 金色 移动联通电信4G手机 双卡双待","price":0.01,"originalprice":1},{"proid":"fc2e60ece4f04a8b86302cc89b5ced65","protable":"jiaju","app_proimgurl":"20160728/b822355ad32744bf88c6121ce3cb1d5d.jpg","pro_alias":"天堂有伞","description":"","proname":"天堂伞 （UPF50 ）全遮光黑胶碳纤骨三折超轻太阳伞晴雨伞31809E粉色","price":99,"originalprice":119},{"proid":"f7b51c1163d64fdbbea632a82e25a9ca","protable":"fuzhuang","app_proimgurl":"20160728/e19fa8d222b04a23b483f8f9b2d4a335.jpg","pro_alias":"群体出游","description":"秋季新品每周二发布 好评返20元无门槛优惠券！","proname":"季候风2016夏装新品甜美时尚修身印花雪纺透视连衣裙2212LD123 花橙MO3 S/155","price":300,"originalprice":352},{"proid":"07e40171a38748e0b115508f4f2b8437","protable":"xiexue","app_proimgurl":"20160728/e7352c147c7a4cc89d6b1f47408e3d18.jpg","pro_alias":"男鞋女鞋","description":"戈美其夏季专场，全场满188元减10元 满258元减30元 满358元减50元","proname":"戈美其2016新款粗跟凉鞋女夏简约绑带优雅高跟超纤一字带青年女鞋 红色 38","price":99,"originalprice":129},{"proid":"bf7698bba43542fcb7d12546f35144bc","protable":"fuzhuang","app_proimgurl":"20160728/b8f4bd97536c44738788f67848dd73ff.jpg","pro_alias":"裙体出游","description":"百搭连衣裙,打出青春","proname":"百搭连衣裙","price":75,"originalprice":95},{"proid":"f04178c58349420ca76ef203e22efb31","protable":"shouji","app_proimgurl":"20160728/0a95ef5dbdb04f3cb81e719da8c258f8.jpg","pro_alias":"群体出游","description":"","proname":"尼康(Nikon)D7200中端数码单反相机 搭配尼康18-140VR镜头套装","price":6000,"originalprice":6500},{"proid":"07e40171a38748e0b115508f4f2b8437","protable":"xiexue","app_proimgurl":"20160728/04211dc9e8524056b7c2e56d41f034d3.jpg","pro_alias":"男鞋女鞋","description":"戈美其夏季专场，全场满188元减10元 满258元减30元 满358元减50元","proname":"戈美其2016新款粗跟凉鞋女夏简约绑带优雅高跟超纤一字带青年女鞋 红色 38","price":99,"originalprice":129},{"proid":"fc2e60ece4f04a8b86302cc89b5ced65","protable":"jiaju","app_proimgurl":"20160728/296c7a6c10304d15b2f53391d0bfdc7e.jpg","pro_alias":"天堂有伞","description":"","proname":"天堂伞 （UPF50 ）全遮光黑胶碳纤骨三折超轻太阳伞晴雨伞31809E粉色","price":99,"originalprice":119},{"proid":"f04178c58349420ca76ef203e22efb31","protable":"shouji","app_proimgurl":"20160728/99f8478f9c44451a9532fdcabce68571.jpg","pro_alias":"群体出游","description":"","proname":"尼康(Nikon)D7200中端数码单反相机 搭配尼康18-140VR镜头套装","price":6000,"originalprice":6500},{"proid":"e2b959fef3bf4bc28f738ac2d845b771","protable":"shouji","app_proimgurl":"20160728/9ddbd5005ea34d6ea800c5ac45c4dcda.jpg","pro_alias":"太阳镜会场","description":"5.5英寸In Cell屏幕，金属一体化机身，后1600 前800万像素！","proname":"乐视（Le）乐2（X620）32GB 金色 移动联通电信4G手机 双卡双待","price":0.01,"originalprice":1},{"proid":"bf7698bba43542fcb7d12546f35144bc","protable":"fuzhuang","app_proimgurl":"20160728/b8f4bd97536c44738788f67848dd73ff.jpg","pro_alias":"裙体出游","description":"百搭连衣裙,打出青春","proname":"百搭连衣裙","price":75,"originalprice":95},{"proid":"07e40171a38748e0b115508f4f2b8437","protable":"xiexue","app_proimgurl":"20160728/2349b05ab68c4e7fbcc819b5c52d0f38.jpg","pro_alias":"男鞋女鞋","description":"戈美其夏季专场，全场满188元减10元 满258元减30元 满358元减50元","proname":"戈美其2016新款粗跟凉鞋女夏简约绑带优雅高跟超纤一字带青年女鞋 红色 38","price":99,"originalprice":129},{"proid":"aba120db0a5340bfbf4004a32cddd24c","protable":"fuzhuang","app_proimgurl":"20160728/95eb762704e648f7a0bd0669f3bf3686.jpg","pro_alias":"群体出游","description":"夏季清仓大促,低至79；10元无门槛优惠券，满699-1000","proname":"骆驼（CAMEL)男装 2016夏季新款微弹纯色圆领修身短袖T恤衫简约休闲上衣男 灰蓝 XXL","price":99,"originalprice":119},{"proid":"f7b51c1163d64fdbbea632a82e25a9ca","protable":"fuzhuang","app_proimgurl":"20160728/47d49fc1f5264817b2d122f03b254c9e.jpg","pro_alias":"群体出游","description":"秋季新品每周二发布 好评返20元无门槛优惠券！","proname":"季候风2016夏装新品甜美时尚修身印花雪纺透视连衣裙2212LD123 花橙MO3 S/155","price":300,"originalprice":352},{"proid":"07e40171a38748e0b115508f4f2b8437","protable":"xiexue","app_proimgurl":"20160728/2349b05ab68c4e7fbcc819b5c52d0f38.jpg","pro_alias":"男鞋女鞋","description":"戈美其夏季专场，全场满188元减10元 满258元减30元 满358元减50元","proname":"戈美其2016新款粗跟凉鞋女夏简约绑带优雅高跟超纤一字带青年女鞋 红色 38","price":99,"originalprice":129},{"proid":"284c345256f74fcb81053b7c2a968351","protable":"shouji","app_proimgurl":"20160728/be1cb4a713824dbb993690dd09aaf516.jpg","pro_alias":"群体出游","description":"看家看店 家庭摄像头 高清夜视 语音唤醒","proname":"中兴（ZTE）小兴看看Memo 360°智能网络摄像机 wifi无线监控摄像头","price":0.01,"originalprice":259},{"proid":"f04178c58349420ca76ef203e22efb31","protable":"shouji","app_proimgurl":"20160728/99f8478f9c44451a9532fdcabce68571.jpg","pro_alias":"群体出游","description":"","proname":"尼康(Nikon)D7200中端数码单反相机 搭配尼康18-140VR镜头套装","price":6000,"originalprice":6500},{"proid":"d2626d42985846ecb2154f77d5d7f689","protable":"zhubao","app_proimgurl":"20160728/b11783e642444f66bb434d5b57d22322.jpg","pro_alias":"太阳镜会场","description":"修脸神器 偏光太阳镜 佩戴舒适 扮靓防晒镜","proname":"普莱斯太阳镜女潮2016近视太阳镜女开车偏光墨镜女明星款可配近视","price":139,"originalprice":159},{"proid":"fc2e60ece4f04a8b86302cc89b5ced65","protable":"jiaju","app_proimgurl":"20160728/296c7a6c10304d15b2f53391d0bfdc7e.jpg","pro_alias":"天堂有伞","description":"","proname":"天堂伞 （UPF50 ）全遮光黑胶碳纤骨三折超轻太阳伞晴雨伞31809E粉色","price":99,"originalprice":119}]
     * code : 10000
     * msg : 获取成功
     */
    private List<IndexshowBean> indexshow;
    private List<MeetpicBean> meetpic;
    private List<HotProductsBean> hotProducts;
    private List<RecommendBean> recommend;


    public List<IndexshowBean> getIndexshow() {
        return indexshow;
    }

    public void setIndexshow(List<IndexshowBean> indexshow) {
        this.indexshow = indexshow;
    }

    public List<MeetpicBean> getMeetpic() {
        return meetpic;
    }

    public void setMeetpic(List<MeetpicBean> meetpic) {
        this.meetpic = meetpic;
    }

    public List<HotProductsBean> getHotProducts() {
        return hotProducts;
    }

    public void setHotProducts(List<HotProductsBean> hotProducts) {
        this.hotProducts = hotProducts;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class IndexshowBean<T> {
        /**
         * protable :
         * weburl : https://www.taobao.com/
         * click_type : 3
         * protype :
         * id : 18ec93fe7f5849b585f57d973fc21451
         * app_imgurl : 20160803/5bfe4c2294ac4b9e9d581ff16d7b0d36.jpg
         * proid :
         */

        private String protable;
        private String weburl;
        private String click_type;
        private String protype;
        private String id;
        private String app_imgurl;
        private String proid;

        public String getProtable() {
            return protable;
        }

        public void setProtable(String protable) {
            this.protable = protable;
        }

        public String getWeburl() {
            return weburl;
        }

        public void setWeburl(String weburl) {
            this.weburl = weburl;
        }

        public String getClick_type() {
            return click_type;
        }

        public void setClick_type(String click_type) {
            this.click_type = click_type;
        }

        public String getProtype() {
            return protype;
        }

        public void setProtype(String protype) {
            this.protype = protype;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApp_imgurl() {
            return app_imgurl;
        }

        public void setApp_imgurl(String app_imgurl) {
            this.app_imgurl = app_imgurl;
        }

        public String getProid() {
            return proid;
        }

        public void setProid(String proid) {
            this.proid = proid;
        }
    }

    public static class MeetpicBean {
        /**
         * protable : fuzhuang
         * weburl :
         * click_type : 2
         * protype : nvzhuang
         * id : 88134b76468e4227aa87059e60f8ffa1
         * app_imgurl : 20160730/3c9c2165372446f89e42807574f56505.jpg
         * proid :
         */

        private String protable;
        private String weburl;
        private String click_type;
        private String protype;
        private String id;
        private String app_imgurl;
        private String proid;

        public String getProtable() {
            return protable;
        }

        public void setProtable(String protable) {
            this.protable = protable;
        }

        public String getWeburl() {
            return weburl;
        }

        public void setWeburl(String weburl) {
            this.weburl = weburl;
        }

        public String getClick_type() {
            return click_type;
        }

        public void setClick_type(String click_type) {
            this.click_type = click_type;
        }

        public String getProtype() {
            return protype;
        }

        public void setProtype(String protype) {
            this.protype = protype;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApp_imgurl() {
            return app_imgurl;
        }

        public void setApp_imgurl(String app_imgurl) {
            this.app_imgurl = app_imgurl;
        }

        public String getProid() {
            return proid;
        }

        public void setProid(String proid) {
            this.proid = proid;
        }
    }

    public static class HotProductsBean {
        /**
         * id : 998cc1ba90f041c8abc9de3e0ffb153e
         * app_proimgurl : 20160727/bb33e39ac5624141adaadcab26812173.jpg
         * protable : dianqi
         * proid : c64725f89e07408db449267d9d7c90fa
         * pro_alias : 麻布沙发
         */

        private String id;
        private String app_proimgurl;
        private String protable;
        private String proid;
        private String pro_alias;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApp_proimgurl() {
            return app_proimgurl;
        }

        public void setApp_proimgurl(String app_proimgurl) {
            this.app_proimgurl = app_proimgurl;
        }

        public String getProtable() {
            return protable;
        }

        public void setProtable(String protable) {
            this.protable = protable;
        }

        public String getProid() {
            return proid;
        }

        public void setProid(String proid) {
            this.proid = proid;
        }

        public String getPro_alias() {
            return pro_alias;
        }

        public void setPro_alias(String pro_alias) {
            this.pro_alias = pro_alias;
        }
    }

    public static class RecommendBean {
        /**
         * proid : 284c345256f74fcb81053b7c2a968351
         * protable : shouji
         * app_proimgurl : 20160729/de5dd547d48c44dfa25587be07d0faeb.jpg
         * pro_alias : 群体出游
         * description : 看家看店 家庭摄像头 高清夜视 语音唤醒
         * proname : 中兴（ZTE）小兴看看Memo 360°智能网络摄像机 wifi无线监控摄像头
         * price : 0.01
         * originalprice : 259.0
         */

        private String proid;
        private String protable;
        private String app_proimgurl;
        private String pro_alias;
        private String description;
        private String proname;
        private double price;
        private double originalprice;

        public String getProid() {
            return proid;
        }

        public void setProid(String proid) {
            this.proid = proid;
        }

        public String getProtable() {
            return protable;
        }

        public void setProtable(String protable) {
            this.protable = protable;
        }

        public String getApp_proimgurl() {
            return app_proimgurl;
        }

        public void setApp_proimgurl(String app_proimgurl) {
            this.app_proimgurl = app_proimgurl;
        }

        public String getPro_alias() {
            return pro_alias;
        }

        public void setPro_alias(String pro_alias) {
            this.pro_alias = pro_alias;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProname() {
            return proname;
        }

        public void setProname(String proname) {
            this.proname = proname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getOriginalprice() {
            return originalprice;
        }

        public void setOriginalprice(double originalprice) {
            this.originalprice = originalprice;
        }
    }
}
