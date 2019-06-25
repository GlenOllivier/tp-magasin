package tpjava.bo;

import java.time.LocalDate;

public class Vip extends User {
    public Vip(String name, String email, LocalDate birthDate) {
        super(name, email, birthDate);
    }

    @Override
    public boolean isVip() {
        return true;
    }
}
