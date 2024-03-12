package com.store.dominguez.util.validations;

public class Exceptions {

    public static class ValidacionException extends RuntimeException {
        public ValidacionException(String message) {
            super(message);
        }
    }

    public static class TipoDocumentoInvalidoException extends ValidacionException {
        public TipoDocumentoInvalidoException(String message) {
            super(message);
        }
    }

    public static class EmailInvalidoException extends ValidacionException {
        public EmailInvalidoException(String message) {
            super(message);
        }
    }

    public static class ValidarSexoException extends ValidacionException {
        public ValidarSexoException(String message) {
            super(message);
        }
    }

    public static class ValidarRolException extends ValidacionException {
        public ValidarRolException(String message) {
            super(message);
        }
    }

    public static class ValidarTelefonoException extends ValidacionException {
        public ValidarTelefonoException(String message) {
            super(message);
        }
    }

    public static class ValidarPasswordException extends ValidacionException {
        public ValidarPasswordException(String message) {
            super(message);
        }
    }

    public static class ValidarNombreApellidosException extends ValidacionException {
        public ValidarNombreApellidosException(String message) {
            super(message);
        }
    }
}
