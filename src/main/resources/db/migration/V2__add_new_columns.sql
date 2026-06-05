ALTER TABLE public.conta ADD COLUMN razao_social VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN cnpj VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN agencia VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN numero_conta VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN limite_cheque_especial NUMERIC(10,2);
ALTER TABLE public.conta ADD COLUMN data_abertura DATE;
ALTER TABLE public.conta ADD COLUMN ativa BOOLEAN;
ALTER TABLE public.conta ADD COLUMN data_aniversario DATE;
UPDATE public.transacao SET tipo_operacao = '1' WHERE tipo_operacao = 'OPERACAO_DEPOSITO';
UPDATE public.transacao SET tipo_operacao = '2' WHERE tipo_operacao = 'OPERACAO_SAQUE';
UPDATE public.transacao SET tipo_operacao = '3' WHERE tipo_operacao = 'OPERACAO_TRANSFERENCIA';
ALTER TABLE public.transacao
    ALTER COLUMN tipo_operacao TYPE INTEGER USING tipo_operacao::integer;

ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS razao_social VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS cnpj VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS agencia VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS numero_conta VARCHAR(255);
ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS limite_cheque_especial NUMERIC(10,2);
ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS data_abertura DATE;
ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS ativa BOOLEAN;
ALTER TABLE public.conta ADD COLUMN IF NOT EXISTS data_aniversario DATE;