package sopt.collaboration.alarmy.global.error.exception;

import sopt.collaboration.alarmy.global.error.ErrorCode;

public class PhraseNotAssignedException extends BusinessException {
    public PhraseNotAssignedException() {
      super(ErrorCode.NOT_FOUND_USER_PHRASE);
    }
}
