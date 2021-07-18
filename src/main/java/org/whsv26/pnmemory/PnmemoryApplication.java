package org.whsv26.pnmemory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PnmemoryApplication {
  public static void main(String[] args) {
    SpringApplication.run(PnmemoryApplication.class, args);
  }
}
