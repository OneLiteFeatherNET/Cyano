package net.minestom.testing.extension;

import net.minestom.server.MinecraftServer;
import net.minestom.testing.EnvImpl;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.support.TypeBasedParameterResolver;
import net.minestom.testing.Env;

/**
 * The {@link MicrotusExtension} class extends {@link TypeBasedParameterResolver<Env>} and implements {@link InvocationInterceptor}.
 * This extension is used to resolve parameters of type {@link Env} and to intercept test method invocations.
 *
 * @since 1.5.0
 * @version 1.1.0
 */
public class MicrotusExtension extends TypeBasedParameterResolver<Env> implements BeforeEachCallback, AfterEachCallback {

    private static final String ENV_KEY = "minestom.env";

    /**
     * Resolves the parameter of type {@link Env}.
     *
     * @param parameterContext the context for the parameter for which an argument should be resolved
     * @param extensionContext the extension context for the {@code Executable} about to be invoked
     * @return an instance of {@link Env}
     * @throws ParameterResolutionException if an error occurs during parameter resolution
     */
    @Override
    public Env resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return extensionContext.getStore(ExtensionContext.Namespace.create(getClass()))
                .getOrComputeIfAbsent(ENV_KEY,
                        key -> new EnvImpl(MinecraftServer.updateProcess()),
                        EnvImpl.class);
    }

    /**
     * This method is called before each test method execution to set up the environment.
     * It sets the system property "minestom.viewable-packet" to "false".
     *
     * @param context the extension context for the test method about to be executed
     */
    @Override
    public void beforeEach(ExtensionContext context) {
        System.setProperty("minestom.viewable-packet", "false");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(getClass()));
        EnvImpl env = store.remove(ENV_KEY, EnvImpl.class);
        if (env != null) env.cleanup();
    }
}
