package com.liuyetech.myapplication.entity;

public class OrderResult {

    private Integer orderId;
    private String tradeNo;
    private String alipayTradeNo;
    private Double totalAmount;
    private String createTime;
    private String updateTime;
    private Integer userId;
    private Integer status;
    private Object user;
    private OrderDetailDTO orderDetail;

    public OrderResult() {
    }

    public static class OrderDetailDTO {
        private Integer orderDetailId;
        private Integer orderId;
        private Integer movieId;
        private Double price;
        private Integer num;
        private Object order;
        private Movie movie;

        public OrderDetailDTO() {
        }

        public Integer getOrderDetailId() {
            return orderDetailId;
        }

        public void setOrderDetailId(Integer orderDetailId) {
            this.orderDetailId = orderDetailId;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getMovieId() {
            return movieId;
        }

        public void setMovieId(Integer movieId) {
            this.movieId = movieId;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
        }

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        @Override
        public String toString() {
            return "OrderDetailDTO{" +
                    "orderDetailId=" + orderDetailId +
                    ", orderId=" + orderId +
                    ", movieId=" + movieId +
                    ", price=" + price +
                    ", num=" + num +
                    ", order=" + order +
                    ", movie=" + movie +
                    '}';
        }
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public OrderDetailDTO getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailDTO orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "OrderResult{" +
                "orderId=" + orderId +
                ", tradeNo='" + tradeNo + '\'' +
                ", alipayTradeNo='" + alipayTradeNo + '\'' +
                ", totalAmount=" + totalAmount +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId=" + userId +
                ", status=" + status +
                ", user=" + user +
                ", orderDetail=" + orderDetail +
                '}';
    }
}