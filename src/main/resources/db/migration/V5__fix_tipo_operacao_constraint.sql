ALTER TABLE transacao DROP CONSTRAINT transacao_tipo_operacao_check;

ALTER TABLE transacao ADD CONSTRAINT transacao_tipo_operacao_check
    CHECK (tipo_operacao = ANY (ARRAY[1, 2, 3]));