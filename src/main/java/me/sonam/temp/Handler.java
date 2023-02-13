package me.sonam.temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Handler
 */
@Component
public class Handler {
    private static final Logger LOG = LoggerFactory.getLogger(Handler.class);

    @Value("${jsonfolder}")
    private Resource resource;
    /**
     * outline only
     */
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        return ServerResponse.ok().build();
    }

    @PostConstruct
    public void processJsonInvoice() throws IOException {
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
