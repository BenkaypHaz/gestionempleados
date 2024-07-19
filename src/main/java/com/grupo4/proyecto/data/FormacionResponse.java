package com.grupo4.proyecto.data;

import java.util.List;

public class FormacionResponse {
    private List<Formacion> items;
    private int count;
    
    public List<Formacion> getItems(){
        return items;
    }
    
    public void setItems(List<Formacion> items) {
        this.items = items;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
