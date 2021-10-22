package main.model;

public interface PrintLine {

    void printLine (int id);

    default String yesOrNo (boolean b){
        String s;
        if (b){
            s = "Sprawne";
        } else {
            s = "Uszkodzone";
        }
        return s;
    }
}
