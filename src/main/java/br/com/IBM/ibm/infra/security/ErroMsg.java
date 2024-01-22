package br.com.IBM.ibm.infra.security;

import jakarta.validation.ValidationException;

public class ErroMsg extends ValidationException {
    public  ErroMsg(String msg){
        super(msg);
    }
}
