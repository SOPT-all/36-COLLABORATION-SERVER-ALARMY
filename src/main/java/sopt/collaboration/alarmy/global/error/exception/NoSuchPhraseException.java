package sopt.collaboration.alarmy.global.error.exception;

import sopt.collaboration.alarmy.global.error.ErrorCode;

public class NoSuchPhraseException extends BusinessException {
    public NoSuchPhraseException() {
        super(ErrorCode.NOT_FOUND_PHRASE);
    }
}
