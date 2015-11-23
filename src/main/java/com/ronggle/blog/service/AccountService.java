package com.ronggle.blog.service;

import com.jfinal.kit.StrKit;
import com.ronggle.blog.model.Account;
import com.ronggle.blog.utils.UuidUtil;

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

    /**
     * get account a little info by id
     * @param accountId account id
     * @return account
     */
    Account findAccountById(String accountId);

    /**
     * save an account<br/>
     * if account id is null,insert<br/>
     * if account id not null,update
     * @param account account
     * @return boolean
     */
    boolean save(Account account);

    /**
     * find account by id and old password
     * @param accountId account id
     * @param old old password
     * @return account
     */
    Account findAccountByIdAndPassword(String accountId, String old);
}
