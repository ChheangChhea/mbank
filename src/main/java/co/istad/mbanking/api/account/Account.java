package co.istad.mbanking.api.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Account {
    private Integer id;
    private Integer accountNo;
    private String accountName;
    private String profile;
    private String pin;
    private String password;
    private String phoneNumber;
    private String transferLimit;
    private String accountType;
}
