package ru.nechaeva.phonebook;

import java.util.*;

public class PhoneBook {

    private Map<String, String> nameToPhone = new HashMap<>();
    private Map<String, String> phoneToName = new HashMap<>();

    // Инициализация без аргументов
    public PhoneBook() {}

    // Метод добавления контакта
    public String add(String phone, String name) {
        String oldPhone = nameToPhone.put(name, phone);

        if (oldPhone != null) {
            phoneToName.remove(oldPhone);
        }

        phoneToName.put(phone, name);
        return oldPhone;
    }

    // Удаление по имени
    public void remove(String name) {
        String phone = nameToPhone.remove(name);
        if (phone != null) {
            phoneToName.remove(phone);
        }
    }

    // Получение телефона по имени
    public String getPhone(String name) {
        return nameToPhone.get(name);
    }

    // Проверка наличия имени
    public boolean containsName(String name) {
        return nameToPhone.containsKey(name);
    }

    // Проверка наличия телефона
    public boolean containsPhone(String phone) {
        return phoneToName.containsKey(phone);
    }

    // Количество контактов
    public int size() {
        return nameToPhone.size();
    }

    // Массив всех пар, преобразую ключи в массив строк
    public String[] getAllPairs() {
        String[] arr = new String[nameToPhone.size()];
        int i = 0;
        for (var entry : nameToPhone.entrySet()) {
            arr[i++] = entry.getKey() + " - " + entry.getValue();
        }
        return arr;
    }


    // Массив телефонов
    public String[] getPhones() {
        return phoneToName.keySet().toArray(new String[0]);
    }

    // Массив имён
    public String[] getNames() {
        return nameToPhone.keySet().toArray(new String[0]);
    }

    // Имена, начинающиеся с подстроки, типо поиск
    public String[] getNamesByPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        for (String name : nameToPhone.keySet()) {
            if (name.startsWith(prefix)) {
                list.add(name);
            }
        }
        return list.toArray(new String[0]);
    }

    @Override
    public String toString() {
        return String.join("\n", getAllPairs());
    }
}
