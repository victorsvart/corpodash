package com.project.corpodash.infrastructure.persistence.seeding;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {
  private final JdbcTemplate jdbcTemplate;
  private final Resource seedFile;
  private final String plainDevPassword;

  public DatabaseSeeder(
      JdbcTemplate jdbcTemplate,
      @Value("${spring.sql.init.data-locations}") Resource seedFile,
      @Value("${user.dev.password}") String plainDevPassword) {
    this.jdbcTemplate = jdbcTemplate;
    this.seedFile = seedFile;
    this.plainDevPassword = plainDevPassword;
  }

  @Override
  public void run(String... args) throws IOException {
    if (!List.of(args).contains("--seed")) {
      return;
    }

    System.out.println("Seeding database from: " + seedFile.getFilename());

    String sql = new String(seedFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    for (String statement : sql.split(";")) {
      if (!statement.trim().isEmpty()) {
        jdbcTemplate.execute(statement.trim());
      }
    }

    assignRandomPasswordsToUsers();
  }

  private void assignRandomPasswordsToUsers() {
    List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT id, email FROM users");

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    for (Map<String, Object> user : users) {
      Long userId = ((Number) user.get("id")).longValue();
      String email = (String) user.get("email");

      String encrypted = encoder.encode(plainDevPassword);

      jdbcTemplate.update("UPDATE users SET password = ? WHERE id = ?", encrypted, userId);

      System.out.printf("User: %s, Plain Password: %s%n", email, plainDevPassword);
    }
  }
}
