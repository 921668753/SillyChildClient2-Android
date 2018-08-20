package com.sillykid.app.entity.homepage.airporttransportation.airportpickup;

import com.contrarywind.interfaces.IPickerViewData;

public class PeopleBean implements IPickerViewData {

    private String name;
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
