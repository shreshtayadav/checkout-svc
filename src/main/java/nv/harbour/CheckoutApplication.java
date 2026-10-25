package nv.harbour;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
public class CheckoutApplication {
public static void main(String[] args) {
SpringApplication.run(CheckoutApplication.class, args);
}
// Never log anything taken from a request. This keeps the lab safe.
@GetMapping("/")
public String home() {
return "Harbour Retail checkout 2.3.1";
}
  @GetMapping("/health")
public ResponseEntity<String> health() {
if ("true".equals(System.getenv("FAIL_HEALTH"))) {
return ResponseEntity.status(500).body("DEGRADED");
}
return ResponseEntity.ok("OK");
}
}
