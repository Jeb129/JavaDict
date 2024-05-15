import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Dict implements Serializable {
    private static class Element implements Serializable{
        private Element(String k, ArrayList<String> val){
            key = k;
            values = new ArrayList<String>(val);
        }
        private Element(String k, String val){
            key = k;
            values = new ArrayList<String>();
            values.add(val);
        }
        String key;
        ArrayList<String> values;
        private boolean addValue(String val){
            if(!values.contains(val)){
                values.add(val);
                return true;
            }
            return false;
        }
        @Override
        public String toString(){
            String s = key + ": ";
            for (String el: values) s += el + "; ";
            return s;
        }
    }
    public Dict(String keyRx){
        keyRegex = keyRx;
    }
    String keyRegex;
    private ArrayList<Element> values = new ArrayList<Element>(0);
    //Поиск
    private Element findElement(String key){
        for (Element elem: values)
            if (Objects.equals(elem.key, key)) return elem;
        return null;
    }
    public ArrayList<String> getElement(String key){
        Element el = findElement(key);
        if (el == null) return null;
        return el.values;
    }
    public void showElements(){
        for(Element el: values){
            System.out.print(el);
        }
        System.out.println();
    }
    //Добавление
    public boolean addElement(String key,String value){
        if(!key.matches(keyRegex)) return false;
        Element el = findElement(key);
        if(el==null){
            values.add(new Element(key, value));
            return true;
        }
        else return el.addValue(value);
    }
    //Удаление
    public boolean removeElement(String key){
        Element el = findElement(key);
        if (el == null) return false;
        values.remove(el);
        return true;
    }
    public boolean removeElement(String key,String val){
        Element el = findElement(key);
        if (el == null || !el.values.contains(val)) return false;
        el.values.remove(val);
        return true;
    }
    public void clearDict(){
        values.clear();
    }
    public void getFromFile(String path){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            ArrayList<Element> fileDict = (ArrayList<Element>) ois.readObject();
            for(Element el: fileDict){
                for(String val: el.values)
                    if(!addElement(el.key,val))
                        System.out.println("Не удалось добавить элемент " + el.key+": "+val);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void loadToFile(String path){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(values);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
