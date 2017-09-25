package com.wsyzj.watchvideo.business;

import java.util.List;

/**
 * @author: wsyzj
 * @date: 2017-09-17 17:15
 * @comment:
 */
public class Test {

    /**
     * ret : 0
     * questions : [{"type":"input","question":"群里男生谁最帅？"},{"type":"input","question":"群里女生谁最美？"},{"type":"input","question":"群里男生谁最娘？"},{"type":"input","question":"群里女生谁最汉子？"},{"type":"input","question":"假如群里选暗恋对象你会选谁？"},{"type":"input","question":"假如群里选另一半你会选谁？"},{"type":"input","question":"群里谁最聪明？"},{"type":"input","question":"群里谁最善良？"},{"type":"input","question":"群里谁最有钱？"},{"type":"input","question":"群里谁最浪？"},{"type":"input","question":"群里谁最闷sao？"},{"type":"input","question":"群里谁最搞xiao？"},{"type":"input","question":"群里谁是段子手？"},{"type":"input","question":"群里谁最2？"},{"type":"input","question":"群里你最愿意和谁一起打农药？"},{"type":"input","question":"群里你最不愿意和谁一起打农药？"},{"type":"input","question":"群里谁最爱卖萌？"},{"type":"input","question":"群里谁唱歌最好听？"},{"type":"input","question":"群里谁是KTV麦霸？"},{"type":"input","question":"群里谁最性感？"},{"type":"input","question":"群里谁最妖艳？"},{"type":"input","question":"群里谁是最6的吃货？"},{"type":"input","question":"你最希望看到群里谁裸奔？"},{"type":"input","question":"群里谁的表情包最多？"},{"type":"input","question":"群里谁的糗事让你印象深刻？"},{"type":"input","question":"你最爱开群里谁的玩笑？"},{"type":"input","question":"群里谁的腿最长？"},{"type":"input","question":"群里谁的脸最大？"},{"type":"input","question":"群里谁的后宫比较多？"},{"type":"input","question":"群里出现的高频词是什么？"},{"type":"input","question":"群里谁是P图大神（表情包T4专家）？"},{"type":"input","question":"群里谁和谁是最佳CP？"}]
     */

    private int ret;
    private List<QuestionsEntity> questions;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<QuestionsEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsEntity> questions) {
        this.questions = questions;
    }

    public static class QuestionsEntity {
        /**
         * type : input
         * question : 群里男生谁最帅？
         */

        private String type;
        private String question;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }
}
