package com.wsyzj.watchvideo;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/28
 *     desc   :
 * </pre>
 */
public class JiaYiToken {


    /**
     * code : 0000
     * data : {"user":{"gmtModified":"2018-05-28 07:30:03","userPic":"http://img.szaiaitie.com/APPFiles/AppHeadImage/2018/05/15258469936757476.png","loginNum":1,"earningsAmount":0,"thisLoginTime":"2018-05-28 07:30:03","isClose":0,"totalCredit":0,"nickName":"我是一坨","mobile":"15989896993","gmtCreate":"2018-05-28 07:30:03","userId":399980,"laiaiNumber":"431063"},"token":"eyJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE1Mjc0OTUzMzEsInVzZXJJZCI6IjM5OTk4MCIsIm5hbWUiOiI0MzEwNjMiLCJleHRyYSI6IntcImVhcm5pbmdzQW1vdW50XCI6MC4wMCxcImdtdENyZWF0ZVwiOjE1Mjc0OTI2MDMwMDAsXCJnbXRNb2RpZmllZFwiOjE1Mjc0OTI2MDMwMDAsXCJpc0Nsb3NlXCI6MCxcImxhaWFpTnVtYmVyXCI6XCI0MzEwNjNcIixcImxvZ2luTnVtXCI6MSxcIm1vYmlsZVwiOlwiMTU5ODk4OTY5OTNcIixcIm5pY2tOYW1lXCI6XCLmiJHmmK_kuIDlnahcIixcInRoaXNMb2dpblRpbWVcIjoxNTI3NDkyNjAzMDAwLFwidG90YWxDcmVkaXRcIjowLFwidXNlcklkXCI6Mzk5OTgwLFwidXNlclBpY1wiOlwiaHR0cDovL2ltZy5zemFpYWl0aWUuY29tL0FQUEZpbGVzL0FwcEhlYWRJbWFnZS8yMDE4LzA1LzE1MjU4NDY5OTM2NzU3NDc2LnBuZ1wifSJ9.E64Z-_rSb3NHBNioijVP5BJSHYnm9BAKR7MteYIlnoorJVD84-ZYR4Zt1jHVmhL0Y8dlr3lj2FmqLikXAu9D41Lpvq9Gn8m9q5r1LxWR_JcPd3DuJqWCbG3nh-6hmzew4JWjKShl2GF-1Fpb8Vk8gj_KgwubH8yN4KIgvVdas-c"}
     * msg : SUCCESS
     */

    private String code;
    private DataBean data;
    private String msg;

    @Override
    public String toString() {
        return "JiaYiToken{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "user=" + user +
                    ", token='" + token + '\'' +
                    '}';
        }

        /**
         * user : {"gmtModified":"2018-05-28 07:30:03","userPic":"http://img.szaiaitie.com/APPFiles/AppHeadImage/2018/05/15258469936757476.png","loginNum":1,"earningsAmount":0,"thisLoginTime":"2018-05-28 07:30:03","isClose":0,"totalCredit":0,"nickName":"我是一坨","mobile":"15989896993","gmtCreate":"2018-05-28 07:30:03","userId":399980,"laiaiNumber":"431063"}
         * token : eyJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE1Mjc0OTUzMzEsInVzZXJJZCI6IjM5OTk4MCIsIm5hbWUiOiI0MzEwNjMiLCJleHRyYSI6IntcImVhcm5pbmdzQW1vdW50XCI6MC4wMCxcImdtdENyZWF0ZVwiOjE1Mjc0OTI2MDMwMDAsXCJnbXRNb2RpZmllZFwiOjE1Mjc0OTI2MDMwMDAsXCJpc0Nsb3NlXCI6MCxcImxhaWFpTnVtYmVyXCI6XCI0MzEwNjNcIixcImxvZ2luTnVtXCI6MSxcIm1vYmlsZVwiOlwiMTU5ODk4OTY5OTNcIixcIm5pY2tOYW1lXCI6XCLmiJHmmK_kuIDlnahcIixcInRoaXNMb2dpblRpbWVcIjoxNTI3NDkyNjAzMDAwLFwidG90YWxDcmVkaXRcIjowLFwidXNlcklkXCI6Mzk5OTgwLFwidXNlclBpY1wiOlwiaHR0cDovL2ltZy5zemFpYWl0aWUuY29tL0FQUEZpbGVzL0FwcEhlYWRJbWFnZS8yMDE4LzA1LzE1MjU4NDY5OTM2NzU3NDc2LnBuZ1wifSJ9.E64Z-_rSb3NHBNioijVP5BJSHYnm9BAKR7MteYIlnoorJVD84-ZYR4Zt1jHVmhL0Y8dlr3lj2FmqLikXAu9D41Lpvq9Gn8m9q5r1LxWR_JcPd3DuJqWCbG3nh-6hmzew4JWjKShl2GF-1Fpb8Vk8gj_KgwubH8yN4KIgvVdas-c
         */



        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            @Override
            public String toString() {
                return "UserBean{" +
                        "gmtModified='" + gmtModified + '\'' +
                        ", userPic='" + userPic + '\'' +
                        ", loginNum=" + loginNum +
                        ", earningsAmount=" + earningsAmount +
                        ", thisLoginTime='" + thisLoginTime + '\'' +
                        ", isClose=" + isClose +
                        ", totalCredit=" + totalCredit +
                        ", nickName='" + nickName + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", gmtCreate='" + gmtCreate + '\'' +
                        ", userId=" + userId +
                        ", laiaiNumber='" + laiaiNumber + '\'' +
                        '}';
            }

            /**
             * gmtModified : 2018-05-28 07:30:03
             * userPic : http://img.szaiaitie.com/APPFiles/AppHeadImage/2018/05/15258469936757476.png
             * loginNum : 1
             * earningsAmount : 0.0
             * thisLoginTime : 2018-05-28 07:30:03
             * isClose : 0
             * totalCredit : 0
             * nickName : 我是一坨
             * mobile : 15989896993
             * gmtCreate : 2018-05-28 07:30:03
             * userId : 399980
             * laiaiNumber : 431063
             */



            private String gmtModified;
            private String userPic;
            private int loginNum;
            private double earningsAmount;
            private String thisLoginTime;
            private int isClose;
            private int totalCredit;
            private String nickName;
            private String mobile;
            private String gmtCreate;
            private int userId;
            private String laiaiNumber;

            public String getGmtModified() {
                return gmtModified;
            }

            public void setGmtModified(String gmtModified) {
                this.gmtModified = gmtModified;
            }

            public String getUserPic() {
                return userPic;
            }

            public void setUserPic(String userPic) {
                this.userPic = userPic;
            }

            public int getLoginNum() {
                return loginNum;
            }

            public void setLoginNum(int loginNum) {
                this.loginNum = loginNum;
            }

            public double getEarningsAmount() {
                return earningsAmount;
            }

            public void setEarningsAmount(double earningsAmount) {
                this.earningsAmount = earningsAmount;
            }

            public String getThisLoginTime() {
                return thisLoginTime;
            }

            public void setThisLoginTime(String thisLoginTime) {
                this.thisLoginTime = thisLoginTime;
            }

            public int getIsClose() {
                return isClose;
            }

            public void setIsClose(int isClose) {
                this.isClose = isClose;
            }

            public int getTotalCredit() {
                return totalCredit;
            }

            public void setTotalCredit(int totalCredit) {
                this.totalCredit = totalCredit;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(String gmtCreate) {
                this.gmtCreate = gmtCreate;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getLaiaiNumber() {
                return laiaiNumber;
            }

            public void setLaiaiNumber(String laiaiNumber) {
                this.laiaiNumber = laiaiNumber;
            }
        }
    }
}
