import java.io.File;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){

        NumDict nd = new NumDict();
        WordsDict wd  =new WordsDict();

        String currentKey = "";
        while (!currentKey.equals("3")) {
            System.out.println(
                    """
                            1. Словарь цифр
                            2. Словарь букв
                            3. Выход"""
            );
            currentKey = sc.nextLine();
            switch (currentKey){
                case "1": {
                    dictMenu(nd);
                    break;
                }
                case "2": {
                    dictMenu(wd);
                    break;
                }
                default: break;
            }
        }
    }
    public static void dictMenu(Dict dict){
        String filepath;
        String currentKey = "";
        while (!currentKey.equals("7")) {
            System.out.println(
                    """
                            1.Добавить значение по ключу
                            2.Удалить занчение
                            3. Вывести все значения
                            4. Загрузить из файла
                            5. Выгрузить в файл
                            6. Очистить словарь
                            7. Назад"""
            );
            currentKey = sc.nextLine();
            switch (currentKey){
                case "1": {
                    System.out.println("Введите ключ:");
                    String key = sc.nextLine();
                    System.out.println("Введите значение:");
                    String val = sc.nextLine();
                    if (!dict.addElement(key,val))
                        System.out.println("Не удалось добавить значение");
                    else
                        System.out.println("Успешно добавлено");
                    break;
                }
                case "2": {
                    System.out.println("Введите ключ:");
                    String key = sc.nextLine();
                    System.out.println("Введите значение (Оставьте пустым для удаления всего ключа):");
                    String val = sc.nextLine();
                    if (!val.isEmpty())
                        if (!dict.removeElement(key,val))
                            System.out.println("Этого значения в ключе не существует");
                        else
                            System.out.println("Значение из ключа удалено");
                    else
                        if (!dict.removeElement(key))
                            System.out.println("Ключ не найден");
                        else
                            System.out.println("Ключ удален");
                    break;
                }
                case "3": {
                    dict.showElements();
                    break;
                }
                case "4": {
                    filepath = setupPath(true);
                    if(!filepath.isEmpty())
                        dict.getFromFile(filepath);
                    break;
                }
                case "5": {
                    filepath = setupPath(false);
                    if(!filepath.isEmpty())
                        dict.loadToFile(filepath);
                    else
                        System.out.println("Файл не найден");
                    break;
                }
                case "6": {
                    dict.clearDict();
                    break;
                }
                default: break;
            }
        }
    }
    public static String setupPath(Boolean input){
        System.out.println("Введите путь до файла со словарем:");
        String fp = sc.nextLine();
        File f = new File(fp);
        if(f.exists() != input || f.isDirectory()){
            return ""; }
        return fp;
    }
}