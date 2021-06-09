package erp.client.javafx.common;

import java.util.ArrayList;

public class ListValue<T> {

    private ArrayList<T> list;

    public ListValue() {
        list = new ArrayList<>();
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}
