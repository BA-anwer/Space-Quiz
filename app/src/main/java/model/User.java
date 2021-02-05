package model;

public class User {
    String name , age , email , motPasse ,score;

    public User(String name, String age, String email, String motPasse,String score) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.motPasse = motPasse;
        this.score=score;

    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }
}

