//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        WordsDict w = new WordsDict();
        System.out.println(w.addElement("abcd","1"));
        System.out.println(w.addElement("abcda","1"));
        System.out.println(w.addElement("йцук","1"));
        System.out.println(w.getElements());
    }
}