package com.sistemabancario.infra;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ICSV<T> {
    public ByteArrayInputStream load(final List<T> data);
}
