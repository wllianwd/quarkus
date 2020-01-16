package io.quarkus.it.vault;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.vault.test.VaultTestLifecycleManager;
import io.restassured.RestAssured;

@QuarkusTest
@QuarkusTestResource(VaultTestLifecycleManager.class)
@DisabledOnOs(OS.WINDOWS)
public class VaultTest {

    @Test
    public void test() throws Exception {
        RestAssured.when().get("/vault").then().body(is("OK"));
    }

}
