package com.ronggle.blog.service.impl;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.ronggle.blog.model.Account;
import com.ronggle.blog.service.AccountService;
import com.ronggle.blog.utils.UuidUtil;

/**
 * Created by soi on 15-11-4.
 */
public class AccountServiceImpl implements AccountService {

    @Override
    public Account token(Account account) {
        return Account.dao.findFirst("select ac.* from account as ac where 1=1 and ac.username = ? and ac.password = ?", account.getStr("username"), HashKit.md5(account.getStr("password")));
    }

    @Override
    public Account findAccountById(String accountId) {
        return Account.dao.findById(accountId);
    }

    @Override
    public boolean save(Account account) {
        if (StrKit.notBlank(account.getStr("id"))) {
            return account.update();
        } else {
            account.set("id", UuidUtil.getUuid32());
            return account.save();
        }
    }

    @Override
    public Account findAccountByIdAndPassword(String accountId, String old) {
        return Account.dao.findFirst("select a.id from account as a where 1 = 1 and a.id = ? and a.password = ?", accountId, old);
    }
}
