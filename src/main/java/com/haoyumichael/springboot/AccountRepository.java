package main.java.com.haoyumichael.springboot;

import main.java.com.haoyumichael.springboot.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
