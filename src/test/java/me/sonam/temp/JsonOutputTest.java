package me.sonam.temp;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@AutoConfigureWebTestClient
@Log
@RunWith(SpringRunner.class)
public class JsonOutputTest {
    private static final Logger LOG = LoggerFactory.getLogger(JsonOutputTest.class);

    @Value("${jsonfolder}")
    private Resource resource;

    @Test
    public void runTest() throws IOException {
        LOG.info("folder: {}", resource.getFilename());
        JsonToVendorType json = new JsonToVendorType();

        if (resource.getFile().isDirectory()) {
            File[] files = resource.getFile().listFiles();

            for(File file: files) {
                LOG.info("process json file: {}", file.getName());
                LOG.info("vendor json contains: {}", json.buildVendorData(file));
            }
        }
    }
}