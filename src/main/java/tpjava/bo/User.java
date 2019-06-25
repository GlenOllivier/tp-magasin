package tpjava.bo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private LocalDate birthDate;

    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        Period p = Period.between(birthDate, now);
        return p.getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
