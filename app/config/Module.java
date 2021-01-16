package config;

import com.google.inject.AbstractModule;
import services.MunroCSVFile;
import services.MunroSearchService;

public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(MunroCSVFile.class).asEagerSingleton();
        bind(MunroSearchService.class).asEagerSingleton();
    }

}