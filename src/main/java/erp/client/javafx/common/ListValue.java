package erp.client.javafx.common;

import java.util.ArrayList;
import java.util.List;

public class ListValue<T> {

    private ArrayList<T> list;

    public ListValue() {
        list = new ArrayList<>();
    }

    public ListValue(List<T> list) {
        this.list = (ArrayList<T>) list;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}
