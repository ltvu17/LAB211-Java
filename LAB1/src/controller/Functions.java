package controller;

import model.Dealers;

public interface Functions {
    public void addNewDealer();
    public void SearchADealer(String name);
    public void removeADealer(String id);
    public void updateADealer(String id);
    public void printAll();
    public void saveToFile(String path);
}
