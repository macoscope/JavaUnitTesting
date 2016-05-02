package rx.plugins;

//Hacking RxJava, RxJavaPlugins.reset() is package private
public class RxJavaPluginsHelper {

    public static void reset(RxJavaPlugins rxJavaPlugins) {
        rxJavaPlugins.reset();
    }
}
