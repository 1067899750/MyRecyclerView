package com.json.itemdecoration.slidedelete;

import java.util.List;

/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/5/17 11:33
 */
public class SlideModel {


    /**
     * data : {"billRecordList":[{"id":"241249564558966784","createTime":"20190517102426","amount":"1000000","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241246934348677120","createTime":"20190517101359","amount":"3","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241246832607444992","createTime":"20190517101335","amount":"5880","voucherType":1,"categoryId":"6690792919514085646","categoryName":"补贴收入"},{"id":"241246782850416640","createTime":"20190517101323","amount":"555","voucherType":1,"categoryId":"6690792709060688140","categoryName":"种植收入"},{"id":"241246754719219712","createTime":"20190517101316","amount":"333","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241236592960815104","createTime":"20190517093253","amount":"111","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241233301002600448","createTime":"20190517091949","amount":"11","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"}],"totalAmount":"1006893"}
     * success : true
     */

    private DataBean data;
    private String success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * billRecordList : [{"id":"241249564558966784","createTime":"20190517102426","amount":"1000000","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241246934348677120","createTime":"20190517101359","amount":"3","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241246832607444992","createTime":"20190517101335","amount":"5880","voucherType":1,"categoryId":"6690792919514085646","categoryName":"补贴收入"},{"id":"241246782850416640","createTime":"20190517101323","amount":"555","voucherType":1,"categoryId":"6690792709060688140","categoryName":"种植收入"},{"id":"241246754719219712","createTime":"20190517101316","amount":"333","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241236592960815104","createTime":"20190517093253","amount":"111","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"},{"id":"241233301002600448","createTime":"20190517091949","amount":"11","voucherType":1,"categoryId":"6690792395528075530","categoryName":"工资收入"}]
         * totalAmount : 1006893
         */

        private String totalAmount;
        private List<BillRecordListBean> billRecordList;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<BillRecordListBean> getBillRecordList() {
            return billRecordList;
        }

        public void setBillRecordList(List<BillRecordListBean> billRecordList) {
            this.billRecordList = billRecordList;
        }

        public static class BillRecordListBean {
            /**
             * id : 241249564558966784
             * createTime : 20190517102426
             * amount : 1000000
             * voucherType : 1
             * categoryId : 6690792395528075530
             * categoryName : 工资收入
             */

            private String id;
            private String createTime;
            private String amount;
            private int voucherType;
            private String categoryId;
            private String categoryName;
            private String remark;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getVoucherType() {
                return voucherType;
            }

            public void setVoucherType(int voucherType) {
                this.voucherType = voucherType;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }
        }
    }
}
