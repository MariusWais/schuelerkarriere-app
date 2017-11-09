package com.example.bufo.f4tappv100;

/**
 * Created by Bufo on 17.05.2017.
 */

public class Vita {
    private String name;
    private String age;
    private String language;
    private String graduation;
    private String phone;
    private String mail;
    private String internships;
    private String itknowledge;
    private String other;


    public Vita(String name,String age,String language,String graduation,String phone,String mail,String internships,String itknowledge, String other){
        this.setName(name);
        this.setAge(age);
        this.setLanguage(language);
        this.setGraduation(graduation);
        this.setPhone(phone);
        this.setMail(mail);
        this.setInternships(internships);
        this.setItKnowledge(itknowledge);
        this.setOther(other);
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getAge(){return age;}
    public void setAge(String age){this.age = age;}

    public String getInternships(){return internships;}
    public void setInternships(String internships){this.internships = internships;}

    public String getLanguage(){return language;}
    public void setLanguage(String language){this.language = language;}

    public String getGraduation(){return graduation;}
    public void setGraduation(String graduation){this.graduation = graduation;}

    public String getItKnowledge(){return itknowledge;}
    public void setItKnowledge(String itknowledge){this.itknowledge = itknowledge;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}

    public String getMail(){return mail;}
    public void setMail(String mail){this.mail = mail;}

    public String getOther(){return other;}
    public void setOther(String other){this.other = other;}

}


