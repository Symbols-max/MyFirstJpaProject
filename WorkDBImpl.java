package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

public class WorkDBImpl {
    static EntityManager em;
    static EntityManagerFactory emf;
    static JsonReader jsonReader;
    static Scanner sc = new Scanner(System.in);
    static Currency c;
    private static String url="https://api.privatbank.ua/p24api/" +
            "exchange_rates?json&date=";

public static void init(){
    emf = Persistence.createEntityManagerFactory("JPATest2");
    em = emf.createEntityManager();
}

    public static Currency parser(Currencies currencies) throws IllegalAccessException {
        Currency currency=new Currency();
        ExchangeRate exchangeRateUSD= currencies.getExchangeRateUSD();
        Class cls=currencies.getClass();
        Field fields[]=cls.getDeclaredFields();
        for (Field field :
                fields) {
            if(field.getName().equals("date")){
                field.setAccessible(true);
                currency.setDate((String) field.get(currencies));
            }
        }
        Class cls2=currencies.getExchangeRateUSD().getClass();
        Field fields2[]=cls2.getDeclaredFields();
        for (Field field2 :
                fields2) {
            Class cls3=currency.getClass();
            Field fields3[]=cls3.getDeclaredFields();
            for (Field field3 :
                    fields3){
            if(field2.getName().equals(field3.getName())){
                field2.setAccessible(true);
                field3.setAccessible(true);
                field3.set(currency,field2.get(exchangeRateUSD));
            }
            }
        }

        return currency;
    }


    public static void addClient() throws IOException, IllegalAccessException {
        System.out.println("Введите день: ");
        String sDay = sc.nextLine();
        int day = Integer.parseInt(sDay);
        System.out.println("Введите месяц: ");
        String sMonth = sc.nextLine();
        int month = Integer.parseInt(sMonth);
        System.out.println("Введите год(>2014): ");
        String sYear = sc.nextLine();
        int year = Integer.parseInt(sYear);
        StringBuilder stringBuilder=new StringBuilder();
        if(day<10){
            stringBuilder.append("0");
        }
        stringBuilder.append(day).append(".");
        if(month<10){
            stringBuilder.append("0");
        }
                stringBuilder.append(month).append(".").append(year);
        String str=stringBuilder.toString();
        Currencies currencies=jsonReader.readJsonFromUrl(url+str);
        WorkDBImpl workDB=new WorkDBImpl();
        c=workDB.parser(currencies);

        em.getTransaction().begin();
        try {
            em.persist(c);
            System.out.println(c);
            em.getTransaction().commit();
            System.out.println(c);
        } catch (Exception ex) {
            System.out.println('t');
            em.getTransaction().rollback();
        }
    }


    public static void deleteClient() {
        System.out.println("Введите день: ");
        String sDay = sc.nextLine();
        int day = Integer.parseInt(sDay);
        System.out.println("Введите месяц: ");
        String sMonth = sc.nextLine();
        int month = Integer.parseInt(sMonth);
        System.out.println("Введите год(>2014): ");
        String sYear = sc.nextLine();
        int year = Integer.parseInt(sYear);
        StringBuilder stringBuilder=new StringBuilder();
        if(day<10){
            stringBuilder.append("0");
        }
        stringBuilder.append(day).append(".");
        if(month<10){
            stringBuilder.append("0");
        }
        stringBuilder.append(month).append(".").append(year);
        String str=stringBuilder.toString();
        Currency c = em.getReference(Currency.class, str);
        if ((c.equals(null))) {
            System.out.println("Таких данных нет");
            return;
        }
        em.getTransaction().begin();
        try {
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public static void viewClients() {
            Query query = em.createQuery(
                    "SELECT c FROM Currency c", Currency.class);
            List<Currency> list = (List<Currency>) query.getResultList();

            for (Currency c : list)
                System.out.println(c);
        }
    }

