package kkk.dainyong.tale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("kkk.dainyong.tale.repository")
public class DainyongApplication {

	public static void main(String[] args) {
		SpringApplication.run(DainyongApplication.class, args);
	}

}