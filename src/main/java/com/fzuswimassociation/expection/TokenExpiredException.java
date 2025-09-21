package com.fzuswimassociation.expection;

/**
 * {@link com.fzuswimassociation.aspect.TokenAdvice}中抛出的{@link TokenExpiredException}
 *
 * @author 李泽聿
 * @since 2025-09-02 16:42
 */


public class TokenExpiredException extends Exception {

    public TokenExpiredException() {
        super("token无效");
    }
}
