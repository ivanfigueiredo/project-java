package com.sistemabancario.application;

import com.sistemabancario.application.dto.UpdateAccountLimitDto;
import com.sistemabancario.application.dto.UpdateAccountLimitOutputDto;

public interface IUpdateAccountLimit {
    public UpdateAccountLimitOutputDto execute(UpdateAccountLimitDto dto);
}
