package uk.co.castlewater.myaccount.integration.service.identity;

/**
 * @author Anatol Sialitski
 */
public final class IdentityContextHolder {

    private static final ThreadLocal<IdentityContext> CONTEXT_DATA = new ThreadLocal<>();

    private IdentityContextHolder() {
    }

    public static void set(IdentityContext identityContext) {
        IdentityContextHolder.CONTEXT_DATA.set(identityContext);
    }

    public static IdentityContext get() {
        return IdentityContextHolder.CONTEXT_DATA.get();
    }

    public static void clear() {
        IdentityContextHolder.CONTEXT_DATA.remove();
    }

}
