package com.odde.bbuddy.account;

import com.odde.bbuddy.account.model.Accounts;
import com.odde.bbuddy.account.viewmodel.Account;
import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.common.JsonBackend;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteAccountTest {

    private static final int ID = 1;
    JsonBackend mockJsonBackend = mock(JsonBackend.class);
    Accounts accounts = new Accounts(mockJsonBackend);
    Runnable mockRunnable = mock(Runnable.class);

    @Test
    public void delete_account_with_id() throws JSONException {
        accounts.deleteAccount(account(ID, "name", 1000), mockRunnable);

        ArgumentCaptor<JSONObject> captor = ArgumentCaptor.forClass(JSONObject.class);
        verify(mockJsonBackend).deleteRequestForJson(eq("/accounts/" + ID), any(JSONObject.class), any(Consumer.class), any(Runnable.class));
    }

    private Account account(int id, String name, int balanceBroughtForward) {
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setBalanceBroughtForward(balanceBroughtForward);
        return account;
    }

    @Test
    public void delete_account_successfully() {
        given_backend_will_success();

        accounts.deleteAccount(account(ID, "name", 1000), mockRunnable);

        verify(mockRunnable).run();
    }

    private void given_backend_will_success() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Consumer consumer = invocation.getArgument(2);
                consumer.accept(new JSONObject());
                return null;
            }
        }).when(mockJsonBackend).deleteRequestForJson(anyString(), any(JSONObject.class), any(Consumer.class), any(Runnable.class));
    }
}
