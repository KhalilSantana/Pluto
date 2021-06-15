package br.univali.comp.virtualmachine;

public enum VMStatus {
    NOT_STARTED,
    RUNNING,
    SYSCALL_IO_WRITE,
    SYSCALL_IO_READ,
    HALTED
}
