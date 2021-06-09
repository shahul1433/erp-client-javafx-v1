package erp.client.javafx.gst;

import java.util.Objects;

public class GstStateCodeDTO {

    private Integer code;
    private String state;

    public GstStateCodeDTO() {
    }

    public GstStateCodeDTO(Integer code, String state) {
        this.code = code;
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "GstStateCodeDTO{" +
                "code=" + code +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GstStateCodeDTO)) return false;
        GstStateCodeDTO that = (GstStateCodeDTO) o;
        return getCode().equals(that.getCode()) && getState().equals(that.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getState());
    }
}
