package com.fabiankevin.springsecurity7_webauthn.passkey;

public class PasskeyException extends RuntimeException {
    public PasskeyException(String message) {
        super(message);
    }

    public PasskeyException(String message, Throwable cause) {
        super(message, cause);
    }
}