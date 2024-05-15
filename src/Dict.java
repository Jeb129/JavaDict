import java.util.ArrayList;
import java.util.Objects;

public class Dict {
    private class Element{
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
    }
    String keyRegex;
    String valueRegex;
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
    //Добавление
    public boolean addElement(String key,String value){
        if(!key.matches(keyRegex)) return false;
        Element el = findElement(key);
        if(el==null){
            values.add(new Element(key,value));
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
