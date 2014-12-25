package ru.yandex.qatools.camelot.example;

import org.springframework.stereotype.Component;
import ru.yandex.qatools.camelot.api.EventProducer;
import ru.yandex.qatools.camelot.api.PluginInterop;
import ru.yandex.qatools.camelot.api.annotations.Input;
import ru.yandex.qatools.camelot.api.annotations.Plugin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Path("/test")
@Component
public class TestResource {
    @Input(TestProcessor.class)
    EventProducer input;

    @Plugin(TestAggregator.class)
    PluginInterop<TestState> plugin;

    @GET
    @Path("post")
    @Produces({MediaType.TEXT_HTML})
    public String postString(@QueryParam("msg") String msg, @QueryParam("uuid") String uuid) {
        input.produce(msg, "uuid", uuid);
        return format("'%s' has been sent", msg);
    }

    @GET
    @Path("get")
    @Produces({MediaType.APPLICATION_JSON})
    public Map<String, TestState> getStates() {
        Map<String, TestState> browsers = new HashMap<>();
        for (String key : plugin.repo().keys()) {
            browsers.put(key, plugin.repo().get(key));
        }
        return browsers;
    }
}
