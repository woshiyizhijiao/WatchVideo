package com.wsyzj.watchvideo.business.utils;

import com.wsyzj.watchvideo.business.bean.NewsChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/01
 *     desc   : 保存一些数据的工具类
 * </pre>
 */
public class DataUtils {

    /**
     * 获取侧滑上面的背景图片
     *
     * @return
     */
    public static String getNavHeaderBgStr() {
        List<String> list = new ArrayList<>();
        list.add("https://images.pexels.com/photos/112352/pexels-photo-112352.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/903028/pexels-photo-903028.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/904432/pexels-photo-904432.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/295821/pexels-photo-295821.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/297824/pexels-photo-297824.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/892093/pexels-photo-892093.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/890035/pexels-photo-890035.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/611328/pexels-photo-611328.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/324030/pexels-photo-324030.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/879832/pexels-photo-879832.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://cdn.magdeleine.co/wp-content/uploads/2016/06/photo-1439894671367-1904e126d8f1-1400x1050.jpeg");
        list.add("https://images.pexels.com/photos/880867/pexels-photo-880867.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/42154/pexels-photo-42154.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/31459/pexels-photo.jpg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.unsplash.com/photo-1507667522877-ad03f0c7b0e0?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=d9740ec30e869b5e79682658b348e82c&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1493809161914-477bd28bfb68?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=1d6a113e734c52e0c643c5d1d5e5b0c4&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1489684141651-7e45b4e77b3a?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=71a99129088137016cc0926837e7dce8&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1518065336951-d16c043900d6?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=05f800ba4e6c18a40a8f7cf12cdd2c35&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1518409274682-1cb2fe2955a8?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b6ce2d94a6218709710dd8f604c62425&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1503022932596-500eb8cca2d8?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=cd5a17ef76aad9fd3ad9483d3841f61f&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1512545477678-8a4e76ad5150?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=c70975f624a4cf817079098e88dd3f2e&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.pexels.com/photos/872739/pexels-photo-872739.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.unsplash.com/photo-1504271863819-d279190bf871?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=7d55268417d17b959f5e9eee2e198972&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.pexels.com/photos/98051/pexels-photo-98051.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/23149/pexels-photo.jpg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.pexels.com/photos/849810/pexels-photo-849810.jpeg?h=350&auto=compress&cs=tinysrgb");
        list.add("https://images.unsplash.com/photo-1506545781647-b898b17fdbdc?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=219aae468620fc0a9241441a7fbcece6&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1509720412848-e552165849be?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b37b01c7a5c03afe53f7b446ea068773&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1490125312197-624d2c718048?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=547737748f32dbf3d63fa876a82f8664&auto=format&fit=crop&w=500&q=60");
        list.add("https://images.unsplash.com/photo-1516563158987-7488b120b58c?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=69a8a5f06476ab385dd75cae54966445&auto=format&fit=crop&w=500&q=60");
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.get(i);
    }

    /**
     * 侧滑上面的背景图片的标语
     *
     * @return
     */
    public static String getNavHeaderBgText() {
        List<String> list = new ArrayList<>();
        list.add("做你害怕的事情\n你会发现不过如此");
        list.add("向往着平淡\n却不甘于平凡");
        list.add("你可以什么都听\n但不能什么都信");
        list.add("打雷时你要记得微笑\n那是上天在给你拍照");
        list.add("如果快乐太难\n那么祝你平安");
        list.add("花自己的钱 会更有底气一些\n这就是应该努力的原因");
        list.add("喜欢的东西就该努力去争取 \n只因自己能喜欢的人和事 本来就不多");
        list.add("愿多年以后 你我仍是旧友\n共饮老酒一醉方休 唱一句青春不朽");
        list.add("吵吵闹闹 皆是心语相交\n眼泪欢笑 亦是情真意切");
        list.add("生活可以将就\n生活也可以讲究");
        list.add("上帝不会为难头脑简单的孩子");
        list.add("做与不做的最大区别是\n后者拥有对前者的评论权");
        list.add("知识就像内裤\n看不见但很重要");
        list.add("真坏人并不可怕\n可怕的是假好人");
        list.add("越是想知道自己是不是忘记的时候\n反而记得越清楚");
        list.add("应该有更好的方式开始新的一天\n而不是千篇一律地在每个早上都醒来");
        list.add("我是心眼小 但是不缺\n我是脾气好 但不是没有");
        list.add("我们只有一个地球 所以你要爱护地球\n地球上只有一个我 所以你也要爱护我");
        list.add("我可以选择放弃\n但是我不能选择放弃");
        list.add("是金子总要发光的\n但当满地都是金子的时候 我就不知道自己是哪颗了");
        list.add("试金可以用火\n试女人可以用金\n试男人可以用女人");
        list.add("世界上只有想不通的人\n没有走不通的路");
        list.add("时间就像一张网 你撒在哪里\n你的收获就在哪里");
        list.add("人生最精彩的不是实现梦想的瞬间\n而是坚持梦想的过程");
        list.add("人生有时就像电脑\n说死机就死机 没得商量");
        list.add("人生有两大悲剧：\n一个是得不到想要的东西\n另一个是得到了不想要的东西");
        list.add("人 长得漂亮不如活的漂亮");
        list.add("让未来到来\n让过去过去");
        list.add("情侣间最矛盾的地方就是幻想彼此的未来\n却惦记着对方的过去");
        list.add("漂亮只能为别人提供眼福\n却不一定换到幸福");
        list.add("女为悦己者容/n男为悦己者穷");
        list.add("你永远看不见我眼里的泪\n因为你不在时我才会哭泣");
        list.add("路见不平一声吼\n吼完继续往前走");
        list.add("理想和现实总是有差距的\n幸好还有差距 不然 谁还稀罕理想?");
        list.add("浪漫是一袭美丽的晚礼服\n但你不能一天到晚都穿着它");
        list.add("开心了就笑 不开心了就过会儿再笑\n高兴了就乐 不高兴了就使劲乐");
        list.add("绝口不提不是因为忘记\n而是因为铭记");
        list.add("就算不快乐也不要皱眉\n因为你永远不知道谁会爱上你的笑容");
        list.add("结婚就是给自由穿件棉衣\n活动起来不方便 但会很温暖");
        list.add("贱人永远都是贱人\n就算经济危机了 你也贵不了");
        list.add("哗众 可以取宠 也可以失宠");
        list.add("和人接触的时间越长 我就越喜欢狗\n狗永远是狗 人有时候不是人");
        list.add("海阔凭鱼跃 破鼓任人捶");
        list.add("还没来得及区沾花惹草\n就被人拔光了");
        list.add("地球是运动的\n一个人不会永远处在倒霉的位置");
        list.add("当我们搬开别人脚下的绊脚石时\n也许恰恰是在为自己铺路");
        list.add("当男人遇见女人 从此只有纪念日 没有独立日");
        list.add("当裤子失去皮带\n才懂得什麽叫做依赖");
        list.add("成熟不是心变老\n而是眼泪在眼里打转却还保持微笑");
        list.add("成功有个副作用\n就是以为过去的做法同样适应于将来");
        list.add("不要说别人脑子有病\n脑子有病的前提是必须有个脑子");
        list.add("不是人人都能活的低调\n可以低调的基础是随时都能高调");
        list.add("夜晚的钟声\n唤醒我童年幻想的美梦");
        list.add("过去不等于现在\n现在不等于未来");
        list.add("我会的你不需要会\n我不会的你必须会 就像生孩子似的");
        list.add("当眼泪滑落的是句点\n耳边言语还在回旋");
        list.add("有些烦恼 丢掉了 才有云淡风轻的机会");
        list.add("我们就只是朋友 没有附加语言");
        list.add("站在属于自己的角落\n假装自己是个过客");
        list.add("时光也带不走\n我爱你的痕迹");
        list.add("生活就是一些人\n和一些狗的相遇");
        list.add("不是我伤感\n只是经历了太多而已");
        list.add("有些事不是我不在意\n而是我在意了又能怎样");
        list.add("别人说你变了\n是因为你没有按照他的想法活罢了");
        list.add("只要你心够狠\n这世界上就没有什么事会让你伤心难受");
        list.add("总有那么一天你会发现\n我不是谁都可以替代");
        list.add("有些东西是与生俱来的\n不可磨灭的");
        list.add("人生太漫长\n你只不过是一道风景");
        list.add("给你最大的报复\n就是活的比你幸福");
        list.add("把你捧上天堂\n也可以亲手把你摔向地狱");
        list.add("你当我透明的\n我也不会当你是有色的");
        list.add("别对我说永远\n我活不到那个点");
        list.add("都是背了太多的心愿 流星会跌的那么重\n都是藏了太多的谎言 我们分手才会那么伤");
        list.add("咖啡苦与甜 不在于怎么搅拌 而在于是否放糖\n一段伤痛 不在于怎么忘记 而在于是否有勇气重新开始");
        list.add("喜欢你的时候你说什么就是什么\n不喜欢你的时候 你说你是什么");
        list.add("所谓情话 就是你说了一些自己都不相信的话\n却希望对方相信");
        list.add("女人用友情来拒绝爱情\n男人用友情来换取爱情");
        list.add("在事实面前 我们的想象力越发达\n后果就越不堪设想");
        list.add("问候不一定要郑重其事 但一定要真诚感人\n精辟到毒死人的句子");
        list.add("常常告诫自己不要在一棵树上吊死\n结果 …… 在树林里迷路了");
        list.add("当别人开始说你是疯子的时候\n你离成功就不远了 ……");
        list.add("幸福是可以通过学习来获得的\n尽管它不是我们的母语");
        list.add("你以为最酸的感觉是吃醋吗?\n不是 最酸的感觉是没权吃醋");
        list.add("美丽让男人停下\n智慧让男人留下");
        list.add("当你到达山顶 请注意\n接下来要走的都是下坡路");
        list.add("天快要塌的事\n过后也觉得没什么大不了");
        list.add("实在想不明白\n事情要从多个角度看");
        list.add("什么时候可以像你这样优秀");
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.get(i);
    }

    /**
     * 获取用户已经选择的新闻标题集合
     *
     * @return
     */
    public static List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> getSelectNewsChannel(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> selectChannel = new ArrayList<>();
        for (int i = 0; i < channelList.size(); i++) {
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = channelList.get(i);
            if (channelListBean.isMyChannel) {
                selectChannel.add(channelListBean);
            }
        }
        return selectChannel;
    }
}
