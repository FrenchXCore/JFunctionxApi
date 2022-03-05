package eu.frenchxcore.tools;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public final class XLogger {

    private final static XLogger INSTANCE;

    private final Logger _logger;
    private final static Level LEVEL = Level.ALL;

    static {
        //if (DataStoreController.getEngineStatus() == DataStoreEngineStatus.ONLINE) {
            System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        //}
        System.setProperty("log4j2.skipJansi", "false");
        INSTANCE = new XLogger();
    }
    
    private XLogger() {
        ConfigurationBuilder<BuiltConfiguration> configurationBuilder = ConfigurationBuilderFactory.newConfigurationBuilder();
        configurationBuilder.setStatusLevel(LEVEL);
        configurationBuilder.setConfigurationName("RollingBuilder");
        // create a layout builder
        LayoutComponentBuilder layoutBuilder = configurationBuilder.newLayout("PatternLayout").addAttribute("pattern", "%d{DEFAULT} %-5p %m{ansi} %throwable{full} %n").addAttribute("charset", "UTF-8");

        // create a console appender
        AppenderComponentBuilder appenderBuilder =
                configurationBuilder
                .newAppender("StdoutAppender", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(layoutBuilder);
        configurationBuilder.add(appenderBuilder);

        configurationBuilder.add(
                configurationBuilder.newAsyncLogger("General", LEVEL)
                        .add(configurationBuilder.newAppenderRef("StdoutAppender"))
                .addAttribute("additivity", false)
        );

        LoggerContext ctx = Configurator.initialize(configurationBuilder.build());
        ctx.updateLoggers();
        ctx.start();
        this._logger = LogManager.getLogger("General");
    }

    public static Logger getMainLogger() {
        return INSTANCE._logger;
    }

    public void logTitle(String title) {
        this.logTitle(Level.INFO, title, null);
    }

    public void logTitle(String title, Throwable t) {
        this.logTitle(Level.FATAL, title, t);
    }

    public void logTitle(Level level, String title, Throwable t) {
        int length = title.length();
        StringBuilder header = new StringBuilder();
        header.append("=======\r\n=====> ");
        header.append(title);
        header.append(" <=====\n=======");
        header.append("=".repeat(length));
        this._logger.log(level, header, t);
    }

}
