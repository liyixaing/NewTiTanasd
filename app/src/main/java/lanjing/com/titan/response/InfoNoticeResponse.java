package lanjing.com.titan.response;

import java.util.List;

/**
 * Created by chenxi on 2019/5/14.
 */

public class InfoNoticeResponse {


    /**
     * msg : ok
     * bannerCH : ["http://114.55.165.42:8081/titan/banner1.png","http://114.55.165.42:8081/titan/banner2.png","http://114.55.165.42:8081/titan/banner3.png"]
     * code : 200
     * dataEH : [{"fid":3,"comtent":"Hello, Titan Project Global Consensus Builder! TITAN DAPP officially opened its node today. TITAN Consensus has established a huge decentralized, free, autonomous and mutual ecological environment with the help of the Chain Bar, EZB Exchange, Inter-Chain and major financial and economic institutions.\n\nTitan plans to go online step by step!\n\n2019/5/26: Turn on node matching!\n\n2019/5/27: Titan Starts EZB.COM Exchange\n\n2019/5/29: titandapp launches daily airdrop plan.\n\n2019/5/30: titandapp opens trade to activate mining.\n\nTitan aims at liberating those block chains and financial projects that lack management and cannot be sustained, and activating the user groups in trouble. Let the project users who join the program carry out autonomous, decentralized self-help and mutual assistance activities so as to avoid losses and gain profits. Titan global users will have the right to carry out global project integration and publicize the concept of formula.\n\nTitan plan welcomes you to join! All participants in Titan Plan are highly consensus. Titan Committee members will join hands with all Titan consensus users to create the world's largest decentralized mutual aid system!\n\n2019.05.26","titleimg":"http://192.168.1.130/imgs/2857e0859ffd437fae36e98cb47bf804.jpg","createtime":"2019-05-14 10:58:46","uname":"admin","edition":"0","title":"TITAN DAPP opens the node!","type":1,"updatetime":"2019-05-14 10:58:49"}]
     * bannerEH : ["http://114.55.165.42:8081/titan/banner4.png","http://114.55.165.42:8081/titan/banner5.png","http://114.55.165.42:8081/titan/banner6.png"]
     * dataCH : [{"fid":1,"comtent":"泰坦计划全球共识建设者您好！TITAN DAPP今日正式开启节点启动，TITAN共识已携手链吧、EZB交易所、互链、各大财经等建立一个庞大的去中心化自由、自主、互助生态！\n\n泰坦计划逐步上线计划！\n\n2019/5/26：开启节点配比！\n\n2019/5/27：TITAN首发EZB.COM交易所\n\n2019/5/29：TITAN DAPP开启每日空投计划。\n\n2019/5/30：TITAN DAPP开启交易激活挖矿。\n\n泰坦旨在解放那些缺乏管理，无以为继的区块链、金融项目，激活困局中的用户群体。让加入该计划的项目用户，可以进行自主的，去中心化的自助互助活动从而避免损失，获得收益，泰坦全球用户将有权进行全球项目整合，宣传公式理念。\n\n泰坦计划欢迎您的加入！泰坦计划所有加入者都是高度共识，泰坦委员将携手所有泰坦共识用户共创全球最大去中心化互助系统！ \n\n2019.05.26","titleimg":"http://192.168.1.130/imgs/2857e0859ffd437fae36e98cb47bf804.jpg","createtime":"2019-05-13 17:46:15","uname":"admin","edition":"1","title":"TITAN DAPP开启节点！","type":1,"updatetime":"2019-05-13 17:46:17"}]
     */

    private String msg;
    private int code;
    public Data data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        public List<String> bannerlist;
        public List<Informationlist> informationlist;

        public List<String> getBannerlist() {
            return bannerlist;
        }

        public void setBannerlist(List<String> bannerlist) {
            this.bannerlist = bannerlist;
        }

        public List<Informationlist> getInformationlist() {
            return informationlist;
        }

        public void setInformationlist(List<Informationlist> informationlist) {
            this.informationlist = informationlist;
        }

        public static class Informationlist {
            private String fid;
            private String comtent;
            private String titleimg;
            private String createtime;
            private String uname;
            private String edition;
            private String title;
            private String type;
            private String updatetime;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getComtent() {
                return comtent;
            }

            public void setComtent(String comtent) {
                this.comtent = comtent;
            }

            public String getTitleimg() {
                return titleimg;
            }

            public void setTitleimg(String titleimg) {
                this.titleimg = titleimg;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getEdition() {
                return edition;
            }

            public void setEdition(String edition) {
                this.edition = edition;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
