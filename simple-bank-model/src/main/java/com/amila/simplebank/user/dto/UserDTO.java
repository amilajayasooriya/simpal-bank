package com.amila.simplebank.user.dto;

import com.amila.simplebank.account.dto.AccountDTO;
import com.amila.simplebank.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO extends BaseDTO {
    private String name;
    private String email;
    private String nin;
    private List<AccountDTO> accountEntityList;
}
