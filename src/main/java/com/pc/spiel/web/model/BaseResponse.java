package com.pc.spiel.web.model;

public class BaseResponse <E>{

private Status status;
private E data;

    public BaseResponse() {
    }

    public BaseResponse(Status status, E data) {
        this.status = status;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public static class Builder<E>{
        private Status status;
        private E data;

        public Builder(){
            status = new Status(0, "Success");
        }

        public Builder setCode(Integer code){
            this.status.setCode(code);
            return this;
        }

        public Builder addDescription(String description){
            this.status.setDescription(description);
            return this;
        }

        public Builder addData(E data){
            this.data = data;
            return this;
        }

        public BaseResponse<E> build(){
            BaseResponse<E> baseResponse = new BaseResponse<>(this.status,this.data);
            return baseResponse;
        }
    }

}
