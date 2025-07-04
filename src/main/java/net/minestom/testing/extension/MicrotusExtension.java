package net.minestom.testing.extension;

import net.minestom.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.support.TypeBasedParameterResolver;
import net.minestom.testing.Env;

import java.lang.reflect.Method;
import java.util.List;

/**
 * The {@link MicrotusExtension} class extends {@link TypeBasedParameterResolver<Env>} and implements {@link InvocationInterceptor}.
 * This extension is used to resolve parameters of type {@link Env} and to intercept test method invocations.
 *
 * @since 1.5.0
 * @version 1.1.0
 */
public class MicrotusExtension extends TypeBasedParameterResolver<Env> implements InvocationInterceptor, BeforeEachCallback {

    /**
     * Resolves the parameter of type {@link Env}.
     *
     * @param parameterContext the context for the parameter for which an argument should be resolved; never {@code null}
     * @param extensionContext the extension context for the {@code Executable} about to be invoked; never {@code null}
     * @return an instance of {@link Env}
     * @throws ParameterResolutionException if an error occurs during parameter resolution
     */
    @Override
    public Env resolveParameter(@NotNull ParameterContext parameterContext, @NotNull ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return Env.createInstance(MinecraftServer.updateProcess());
    }

    /**
     * Intercepts the test method invocation to perform additional actions before or after the test method execution.
     *
     * @param invocation        the invocation to be intercepted; never {@code null}
     * @param invocationContext the context for the reflective invocation of the test method; never {@code null}
     * @param extensionContext  the context for the extension; never {@code null}
     * @throws Throwable if an error occurs during the interception
     */
    @Override
    public void interceptTestMethod(@NotNull Invocation<Void> invocation, @NotNull ReflectiveInvocationContext<Method> invocationContext, @NotNull ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
        List<Object> arguments = invocationContext.getArguments();
        arguments.stream().filter(Env.class::isInstance).findFirst().ifPresent(o -> {
            Env env = (Env) o;
            env.cleanup();
        });
    }

    /**
     * This method is called before each test method execution to set up the environment.
     * It sets the system property "minestom.viewable-packet" to "false".
     *
     * @param context the extension context for the test method about to be executed; never {@code null}
     */
    @Override
    public void beforeEach(ExtensionContext context) {
        System.setProperty("minestom.viewable-packet", "false");
    }
}
