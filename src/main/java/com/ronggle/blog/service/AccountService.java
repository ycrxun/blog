package com.ronggle.blog.service;

import com.ronggle.blog.model.Account;

/**
 * Created by soi on 15-11-4.
 */
public interface AccountService {

    /**
     * token user account and password-user login
     * @param account user account model
     * @return account
     */
    Account token(Account account);
}
