package uk.co.castlewater.myaccount.service.api;

public final class Constants {

    private Constants() {
        throw new AssertionError();
    }

    public static final class Swagger {
        public static final String OK_MESSAGE = "Success";
        public static final String BAD_REQUEST_MESSAGE = "Bad Request";
        public static final String NOT_AUTHORIZED_MESSAGE = "You are not authorized to view this resource";
        public static final String FORBIDDEN_MESSAGE = "Accessing the resource you were trying to reach is forbidden";
        public static final String NOT_FOUND_MESSAGE = "The resource you were trying to reach is not found";
        public static final String INTERNAL_ERROR_MESSAGE = "Unexpected exception";
    }

}
