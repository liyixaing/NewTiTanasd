package lanjing.com.titan.response;

import java.util.List;

public class InformationResponse {
    private int code;
    private String msg;
    public List<BannerCH> bannerCH;
    public List<DataEH> dataEH;
    public List<BannerEH> bannerEH;
    public List<DataCH> dataCH;



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

    public List<BannerCH> getBannerCH() {
        return bannerCH;
    }

    public void setBannerCH(List<BannerCH> bannerCH) {
        this.bannerCH = bannerCH;
    }

    public List<DataEH> getDataEH() {
        return dataEH;
    }

    public void setDataEH(List<DataEH> dataEH) {
        this.dataEH = dataEH;
    }

    public List<BannerEH> getBannerEH() {
        return bannerEH;
    }

    public void setBannerEH(List<BannerEH> bannerEH) {
        this.bannerEH = bannerEH;
    }

    public List<DataCH> getDataCH() {
        return dataCH;
    }

    public void setDataCH(List<DataCH> dataCH) {
        this.dataCH = dataCH;
    }

    public static class BannerCH {

    }

    public static class BannerEH {

    }

    public static class DataEH {
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

    public static class DataCH {
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
