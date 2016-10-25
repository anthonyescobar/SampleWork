;---------------------------------------------------------------------------------
; madlibs/madlibs.asm
;
; This program will take arguments and insert them into the given statement at its index number.
;
; nasm -felf64 madlibs.asm && gcc madlibs.o madlib-by-numbers.c -std=c99 && ./a.out "Statement" [Arguments]
;----------------------------------------------------------------------------------

        global  main

        extern  puts
        extern  printf
        extern  atoi

        extern  madlib_by_numbers

        section .text
main:
        cmp rdi, 2              ;
        jl error                ; jumps to error if rax < 2

        push    rbx
        sub     rdi, 2
        push    rdi

        mov     rdi, [rsi+8]
        mov     rdx, rsi
        add     rdx, 16
        pop     rsi

        call    madlib_by_numbers

        mov     rsi, rax
        mov     rdi, temp

        xor     rax, rax
        sub     rsp, 8

        call    printf
        add     rsp, 8
        pop     rbx
        ret

error:
        mov rdi, errorMessage   ; places message in rdi
        call puts               ; print rdi
        ret

errorMessage:
        db "No argument supplied.", 0

temp:
        db "%s", 10, 0
