package pl.sdaacademy.reservationapi.conference_room;

public class Envelop<T> {
    private long pageNumber;
    private long dataCount;
    private T data;

    public Envelop() {

    }

    public Envelop(long pageNumber, long dataCount, T data) {
        this.pageNumber = pageNumber;
        this.dataCount = dataCount;
        this.data = data;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getDataCount() {
        return dataCount;
    }

    public void setDataCount(long dataCount) {
        this.dataCount = dataCount;
    }
}
