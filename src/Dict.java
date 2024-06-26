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
            return s.replaceFirst("; *$",";");
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
            System.out.println(el);
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
        try{
            if (!path.matches(".*txt$"))
                throw new RuntimeException("Неподдерживаемый формат файла\nПоддерживаемые форматы: txt");
            BufferedReader reader = new BufferedReader(new FileReader(path));
            values.clear();
            String line = reader.readLine();
            int i = 0;
            while (line != null){
                i += 1;
                if(!line.matches("^"+keyRegex+": .*; *$")){
                    System.out.println("Не удалось преобразовать строку " + i);
                }
                else{
                    String[] parts = line.split(": ");
                    String key = parts[0];
                    String[] vals = parts[1].replaceFirst("; *$","").split("; ");
                    for (String val: vals) {
                        if (!addElement(key, val))
                            System.out.println("Не удалось добавить элемент " + key + ": " + val);
                    }
                line = reader.readLine();
                }
            }
                reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void loadToFile(String path){
        try{
            if (!path.matches(".*txt$"))
                throw new RuntimeException("Неподдерживаемый формат файла\nПоддерживаемые форматы: txt");
            FileWriter fw = new FileWriter(path);
            for (Element el: values)
                fw.write(el+"\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
