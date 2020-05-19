package com.myspace.model;

public class MyFact {
    private boolean field1;

    @Override
    public String toString() {
        return String.format("MyFact {field1: %s }", field1);
    }

    /**
     * @return the field1
     */
    public boolean isField1() {
        return field1;
    }

    /**
     * @param field1 the field1 to set
     */
    public void setField1(boolean field1) {
        this.field1 = field1;
    }

}