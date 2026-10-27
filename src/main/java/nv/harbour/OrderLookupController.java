package nv.harbour;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * LAB FIXTURE nv-seed=deliberate nv-seed-ref=F14. Do not "fix" this file.
 *
 * Order lookup for the checkout service. The order id comes straight from the
 * request and is concatenated into the SQL text (CWE-89, no CVE exists).
 *
 * Safety: the "sast-fixture" profile is never switched on, so the running
 * service does not register this class and the route is not served. The code
 * scanners read the source, so they still report it.
 */
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
@Profile("sast-fixture")
@RestController
public class OrderLookupController {

    private static final String DB_URL_ENV = "ORDERS_DB_URL"; // never set in the lab

    @GetMapping("/orders/lookup")
    public List<String> lookup(@RequestParam("orderId") String orderId) throws Exception {
        List<String> rows = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(System.getenv(DB_URL_ENV));
             Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery("SELECT id, status FROM orders WHERE id = '" + orderId + "'");
            while (rs.next()) {
                rows.add(rs.getString("id") + ":" + rs.getString("status"));
            }
        }
        return rows;
    }
}
