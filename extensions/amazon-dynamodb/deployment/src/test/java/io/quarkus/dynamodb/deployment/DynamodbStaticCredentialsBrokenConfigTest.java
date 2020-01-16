package io.quarkus.dynamodb.deployment;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.dynamodb.runtime.RuntimeConfigurationError;
import io.quarkus.test.QuarkusUnitTest;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamodbStaticCredentialsBrokenConfigTest {

    @Inject
    DynamoDbAsyncClient async;

    @Inject
    DynamoDbClient sync;

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setExpectedException(RuntimeConfigurationError.class)
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addAsResource("static-credentials-broken-config.properties", "application.properties"));

    @Test
    public void test() {
        // should not be called, deployment exception should happen first:
        // it's illegal to use STATIC credentials resolver without access key and secret key
        Assertions.fail();
    }
}
