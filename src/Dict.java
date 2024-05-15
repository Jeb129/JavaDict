import javax.lang.model.element.Element;
import java.util.ArrayList;

public class Dict {
    private class Element{
        String key;
        ArrayList<String> values = new ArrayList<String>(0);
    }
    String keyRegex;
    String valueRegex;
    private ArrayList<Element> values = new ArrayList<Element>(0);
    public void addElement(){}
}
