import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Dict implements Serializable {
    public static class Element implements Serializable{
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
            for (String el: values) s+= el + " ";
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
    public ArrayList<Element> getElements(){
        return new ArrayList<Element>(values);
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
}
